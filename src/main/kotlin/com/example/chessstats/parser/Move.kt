package com.example.chessstats.parser

abstract class Move(val color: Color) {
    abstract fun updateState(state: Board)
}

class PromotionMove(
        val newPieceType: PieceType,
        val fieldIdx: Int,
        val fromFile: Char,
        color: Color
): Move(color) {
    override fun updateState(state: Board) {
        var fromField: Int = -1
        loop@ for (from in state.data.indices) {
            val piece = state.data[from]
            if (piece != null
                    && piece.type == PieceType.Pawn && piece.color == color
                    && from.file == fromFile
                    && piece.isValidMove(state, from, fieldIdx)) {
                fromField = from
                break@loop
            }
        }
        state[fieldIdx] = Piece(newPieceType, color)
        state[fromField] = null
    }
}

class CastleMove(
        val isKingSide: Boolean,
        color: Color
): Move(color) {

    override fun updateState(state: Board) {
        val rank = if (color == Color.White) 0 else 7
        val sourceRookField = if (isKingSide) Board.fieldIndex(rank, 'h') else Board.fieldIndex(rank, 'a')
        val targetRookField = if (isKingSide) Board.fieldIndex(rank, 'f') else Board.fieldIndex(rank, 'd')
        val sourceKingField = Board.fieldIndex(rank, 'e')
        val targetKingField = if (isKingSide) Board.fieldIndex(rank, 'g') else Board.fieldIndex(rank, 'c')
        val king = state[sourceKingField]
        state[sourceKingField] = null
        state[targetKingField] = king
        val rook = state[sourceRookField]
        state[sourceRookField] = null
        state[targetRookField] = rook
    }
}

class RegularMove(
        val pieceType: PieceType,
        val fieldIdx: Int,
        val fromFile: Char?,
        val fromRank: Int?,
        color: Color
): Move(color) {

    override fun toString(): String {
        return "${Piece(pieceType, color).symbol}${fromFile ?: ""}${fromRank ?: ""}${fieldIdx.file}${fieldIdx.rank + 1}"
    }

    override fun updateState(state: Board) {
        var fromField: Int = -1
        loop@ for (from in state.data.indices) {
            val piece = state.data[from]
            if (piece != null
                    && piece.type == pieceType && piece.color == color
                    && (fromFile == null || from.file == fromFile)
                    && (fromRank == null || from.rank == fromRank)
                    && piece.isValidMove(state, from, fieldIdx)) {
                fromField = from
                break@loop
            }
        }
        val piece = state[fromField]
        if (pieceType == PieceType.Pawn && fieldIdx == state.inPassingSquareIdx) {
            val pawnIdx = if (color == Color.White) state.inPassingSquareIdx - 1 else state.inPassingSquareIdx + 1
            state[pawnIdx] = null
        }
        pieceType.updateInPassingSquare(state, fromField, fieldIdx)
        state[fieldIdx] = piece
        state[fromField] = null
    }
}
