package com.example.chessstats.parser

import kotlin.math.abs

enum class Color {
    White,
    Black
}

enum class PieceType(val symbol: Char) {
    Pawn('p') {
        override fun isValidMove(state: Board, from: Int, to: Int): Boolean {
            val color = state[from]?.color ?: return false
            return if (color == Color.White) {
                if (from.rank == 1) to.rank == 2 || (to.rank == 3 && to.file == from.file && piecesBetween(state, from, to).isEmpty())
                else to.rank == from.rank + 1
            } else {
                if (from.rank == 6) to.rank == 5 || (to.rank == 4 && to.file == from.file && piecesBetween(state, from, to).isEmpty())
                else to.rank == from.rank - 1
            }
        }

        override fun threatensKing(field: Int, kingField: Int) = false
    },
    Rook('r') {
        override fun isValidMove(state: Board, from: Int, to: Int): Boolean {
            return (from.rank == to.rank || from.file == to.file) && piecesBetween(state, from, to).isEmpty()
        }

        override fun threatensKing(field: Int, kingField: Int): Boolean {
            return field.rank == kingField.rank || field.file == kingField.file
        }
    },
    Knight('n') {
        override fun isValidMove(state: Board, from: Int, to: Int): Boolean {
            val rankDiff = abs(from.rank - to.rank)
            val fileDiff = abs(from.file - to.file)
            return (rankDiff == 2 && fileDiff == 1) || (rankDiff == 1 && fileDiff == 2)
        }

        override fun threatensKing(field: Int, kingField: Int) = false
    },
    Bishop('b') {
        override fun isValidMove(state: Board, from: Int, to: Int): Boolean {
            val rankDiff = abs(from.rank - to.rank)
            val fileDiff = abs(from.file - to.file)
            return rankDiff == fileDiff && piecesBetween(state, from, to).isEmpty()
        }

        override fun threatensKing(field: Int, kingField: Int): Boolean {
            val rankDiff = abs(field.rank - kingField.rank)
            val fileDiff = abs(field.file - kingField.file)
            return rankDiff == fileDiff
        }
    },
    King('k') {
        override fun isValidMove(state: Board, from: Int, to: Int) = true
        override fun threatensKing(field: Int, kingField: Int) = false
    },
    Queen('q') {
        override fun isValidMove(state: Board, from: Int, to: Int): Boolean {
            val rankDiff = abs(from.rank - to.rank)
            val fileDiff = abs(from.file - to.file)
            return (from.rank == to.rank || from.file == to.file || rankDiff == fileDiff) && piecesBetween(state, from, to).isEmpty()
        }

        override fun threatensKing(field: Int, kingField: Int): Boolean {
            val rankDiff = abs(field.rank - kingField.rank)
            val fileDiff = abs(field.file - kingField.file)
            return field.rank == kingField.rank || field.file == kingField.file || rankDiff == fileDiff
        }
    };

    abstract fun isValidMove(state: Board, from: Int, to: Int): Boolean
    abstract fun threatensKing(field: Int, kingField: Int): Boolean

    fun updateInPassingSquare(state: Board, from: Int, to: Int) = when {
        (this != Pawn) -> state.inPassingSquareIdx = -1
        (from.rank == 1 && to.rank == 3) -> state.inPassingSquareIdx = to - 1
        (from.rank == 6 && to.rank == 4) -> state.inPassingSquareIdx = to + 1
        else -> state.inPassingSquareIdx = -1
    }

    companion object {
        fun parsePiece(c: Char) = when (c) {
            'R' -> Rook
            'N' -> Knight
            'B' -> Bishop
            'K' -> King
            'Q' -> Queen
            in 'a'..'h' -> Pawn
            else -> null
        }
    }
}

data class Piece(
        val type: PieceType,
        val color: Color
) {
    val symbol: Char get() = if (color == Color.White) type.symbol.toUpperCase() else type.symbol

    fun isValidMove(state: Board, from: Int, to: Int): Boolean {
        val king = state.data.indexOfFirst { it?.color == color && it.type == PieceType.King }
        state.data.forEachIndexed { fieldIdx, piece ->
            if (piece != null && piece.color != color && piece.type.threatensKing(fieldIdx, king)) {
                val piecesBetween = piecesBetween(state, fieldIdx, king)
                if (piecesBetween.singleOrNull() == from && !fieldIsBetween(to, fieldIdx, king)) return false
            }
        }
        return type.isValidMove(state, from, to)
    }
}

fun fieldIsBetween(field: Int, from: Int, to: Int): Boolean {
    val minFile = minOf(from.file, to.file)
    val maxFile = maxOf(from.file, to.file)
    val minRank = minOf(from.rank, to.rank)
    val maxRank = maxOf(from.rank, to.rank)
    return when {
        (from.rank == to.rank) -> field.file in minFile..maxFile && field.rank == from.rank
        (from.file == to.file) -> field.rank in minRank..maxRank && field.file == from.file
        (abs(from.rank - to.rank) == abs(from.file - to.file)) -> {
            val fileMux = if ((from.rank - to.rank) * (from.file - to.file) < 0) -1 else 1
            val isDiagonal = (field.rank - from.rank) == fileMux * (field.file - from.file)
            isDiagonal && field.file in minFile..maxFile
        }
        else -> false
    }
}

fun piecesBetween(state: Board, from: Int, to: Int): List<Int> {
    val rankDiff = abs(from.rank - to.rank)
    val fileDiff = abs(from.file - to.file)
    val result = mutableListOf<Int>()
    val minFile = minOf(from.file, to.file)
    val maxFile = maxOf(from.file, to.file)
    val minRank = minOf(from.rank, to.rank)
    val maxRank = maxOf(from.rank, to.rank)
    return when {
        (from.rank == to.rank) -> {
            for (i in minFile + 1 until maxFile) {
                val field = Board.fieldIndex(from.rank, i)
                state[field]?.let { result += field }
            }
            result
        }
        (from.file == to.file) -> {
            for (i in minRank + 1 until maxRank) {
                val field = Board.fieldIndex(i, from.file)
                state[field]?.let { result += field }
            }
            result
        }
        (rankDiff == fileDiff) -> {
            val fileIncrement = if ((from.rank - to.rank) * (from.file - to.file) < 0) -1 else 1
            var j = if (fileIncrement == -1) maxFile else minFile
            for (i in minRank + 1 until maxRank) {
                j += fileIncrement
                val field = Board.fieldIndex(i, j)
                state[field]?.let { result += field }
            }
            result
        }
        else -> result
    }
}
