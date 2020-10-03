package com.example.chessstats.parser

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import kotlin.text.StringBuilder

class Stats {
    @JsonSerialize(using = PercentageSerializer::class)
    private val percentageByPiece: Array<MutableMap<Piece?, Double>> = Array(64) { mutableMapOf() }
    @JsonIgnore
    private val rawData: Array<MutableMap<Piece?, Long>> = Array(64) { mutableMapOf() }
    @JsonIgnore
    private var totalCount: Long = 0

    private fun incrementRawDataFromBoard(board: Board) {
        ++totalCount
        board.data.forEachIndexed { idx: Int, piece: Piece? ->
            val pieceCount = rawData[idx].getOrDefault(piece, 0)
            rawData[idx][piece] = pieceCount + 1L
        }
    }

    private fun calculateStatsByRawData() {
        if (totalCount == 0L) return
        rawData.forEachIndexed { idx, rawCellInfo ->
            percentageByPiece[idx] = rawCellInfo.mapValuesTo(mutableMapOf()) { it.value.toDouble() * 100 / totalCount }
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 7 downTo 0) {
            for (j in 'a'..'h') {
                val ci = this.percentageByPiece[Board.fieldIndex(i, j)]
                sb.append(ci.toString())
                sb.append('\t')
            }
            if (i != 0) sb.append('\n')
        }
        return sb.toString()
    }

    companion object {
        fun generateStatsForGames(games: Sequence<ParsedGame>): Stats {
            val result = Stats()
            games.forEach { game ->
                game.states.forEach {
                    result.incrementRawDataFromBoard(it)
                }
            }
            result.calculateStatsByRawData()
            return result
        }
    }
}

class PercentageSerializer: JsonSerializer<Array<MutableMap<Piece?, Double>>>() {
    override fun serialize(value: Array<MutableMap<Piece?, Double>>?, gen: JsonGenerator?, provider: SerializerProvider?) {
        if (gen == null || value == null) return
        gen.writeStartObject()
        for (i in 7 downTo 0) {
            for (j in 'a'..'h') {
                val cell = value[Board.fieldIndex(i, j)]
                gen.writeObjectFieldStart(Board.fieldName(i, j))
                cell.forEach { (key, value) ->
                    gen.writeObjectField(key?.symbol?.toString() ?: "s", value)
                }
                gen.writeEndObject()
            }
        }
        gen.writeEndObject()
    }
}
