package com.example.chessstats.parser

import java.lang.StringBuilder

class Board {
    val data: Array<Piece?>
    var inPassingSquareIdx: Int = -1

    override fun toString(): String {
        val sb = StringBuilder()
        for (i in 7 downTo 0) {
            for (j in 'a'..'h') {
                val piece = this[fieldIndex(i, j)]
                if (piece == null) sb.append(' ') else sb.append(piece.symbol)
            }
            if (i != 0) sb.append('\n')
        }
        return sb.toString()
    }

    constructor(data: Array<Piece?>, inPassingSquareIdx: Int) {
        this.data = data
        this.inPassingSquareIdx = inPassingSquareIdx
    }

    constructor() {
        this.data = Array(64) { null }
    }

    operator fun get(idx: Int): Piece? {
        return data[idx]
    }

    operator fun set(idx: Int, value: Piece?) {
        data[idx] = value
    }

    operator fun get(field: String): Piece? {
        return data[fieldIndex(field)]
    }

    operator fun set(field: String, value: Piece?) {
        data[fieldIndex(field)] = value
    }

    companion object {
        fun fieldIndex(field: String): Int {
            val rank = field[1].toInt() - 48 - 1
            val file = field[0]
            val offset = (file - 'a') * 8
            return rank + offset
        }

        fun fieldIndex(rank: Int, file: Char): Int {
            val offset = (file - 'a') * 8
            return rank + offset
        }

        fun fieldName(rank: Int, file: Char) = "$file${rank + 1}"
    }
}

val Int.rank: Int get() = this % 8
val Int.file: Char get() = 'a' + (this / 8)