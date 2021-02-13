package com.example.chessstats.parser

import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

object Parser {
    private val logger = LoggerFactory.getLogger(javaClass)

    private val moveRegex = Regex("\\d+\\.\\s*")
    private fun parseLine(line: String): List<Move> {
        return line.split(moveRegex).flatMap {
            it.split(" ").mapIndexedNotNull { index, move ->
                if (move.trim().isBlank()) null else {
                    val color = if (index == 0) Color.White else Color.Black
                    parseMove(move, color)
                }
            }
        }
    }

    fun parsePgn(pgn: String) = parseGame(emptyMap(), pgn, pgn.lines().flatMap { parseLine(it) })

    fun parsePgnFile(file: InputStream): List<ParsedGame> {
        val result = mutableListOf<ParsedGame>()
        val reader = BufferedReader(InputStreamReader(file))
        var line = reader.readLine()
        var meta = mutableMapOf<String, String>()
        val tagPattern = Regex("\\[(\\w+)\\s\"(.+)\"]")
        val movesPattern = Regex("\\d+\\..+")
        var moves = mutableListOf<Move>()
        val pgn = StringBuilder()
        var isParsingMoves = false
        while (line != null) {
            if (line.startsWith('[')) {
                val tags = tagPattern.matchEntire(line)?.groupValues
                if (tags?.size == 3) {
                    meta[tags[1]] = tags[2]
                }
            } else if (line.matches(movesPattern)) {
                pgn.appendLine(line)
                isParsingMoves = true
                moves.addAll(parseLine(line))
            } else if (isParsingMoves) {
                parseGame(meta, pgn.toString(), moves)?.also { result += it }
                pgn.clear()
                meta = mutableMapOf()
                moves = mutableListOf()
                isParsingMoves = false
            }
            line = reader.readLine()
        }
        if (isParsingMoves) {
            parseGame(meta, pgn.toString(), moves)?.also { result += it }
        }
        file.close()
        return result
    }

    private fun parseGame(meta: Map<String, String>, pgn: String, moves: List<Move>): ParsedGame? {
        var currentState = ParsedGame.getInitialState()
        val states = mutableListOf(currentState)
        moves.forEach { move ->
            val newState = Board(currentState.data.copyOf(), currentState.inPassingSquareIdx)
            try {
                move.updateState(newState)
            } catch (e: Exception) {
                logger.error("Failed to parse:")
                logger.error("move: $move")
                return null
            }
            currentState = newState
            states += currentState
        }
        return ParsedGame(meta, pgn, states)
    }

    private fun parseMove(fullTextMove: String, color: Color): Move? {
        val textMove = fullTextMove.replace(Regex("[+#]"), "")
        val piece = PieceType.parsePiece(textMove[0])
        if (piece == null) {
            if (textMove == "O-O") return CastleMove(true, color)
            if (textMove == "O-O-O") return CastleMove(false, color)
            return null
        }
        if (piece == PieceType.Pawn) {
            val indexOfEq = textMove.indexOf('=')
            if (indexOfEq != -1) {
                val newPiece = PieceType.parsePiece(textMove.last())
                if (newPiece == null || newPiece == PieceType.Pawn) throw IllegalArgumentException("Unknown promoted piece at move $fullTextMove")
                val targetField = textMove.substring(textMove.length - 4, textMove.length - 2)
                val parsedField = Board.fieldIndex(targetField)
                return PromotionMove(newPiece, parsedField, textMove[0], color)
            }
        }
        val targetField = textMove.substring(textMove.length - 2)
        val parsedField = Board.fieldIndex(targetField)
        val fromFile: Char?
        val fromRank: Int?
        if (piece == PieceType.Pawn) {
            fromFile = textMove[0]
            fromRank = null
        } else {
            val moveWithoutTarget = textMove.substring(1, textMove.length - 2).replace("x", "")
            fromFile = moveWithoutTarget.find { it.isLetter() }
            fromRank = moveWithoutTarget.find { it.isDigit() }?.toInt()?.let { it - 48 - 1 }
        }
        return RegularMove(piece, parsedField, fromFile, fromRank, color)
    }
}