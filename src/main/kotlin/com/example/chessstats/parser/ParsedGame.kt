package com.example.chessstats.parser

data class ParsedGame(
        val meta: Map<String, String>,
        val pgn: String,
        val states: List<Board>
) {
    override fun toString(): String {
        return meta.toString() + "\n" + states.lastOrNull()?.toString()
    }
    companion object {
        fun getInitialState(): Board {
            val result = Board()

            result["a1"] = Piece(PieceType.Rook, Color.White)
            result["b1"] = Piece(PieceType.Knight, Color.White)
            result["c1"] = Piece(PieceType.Bishop, Color.White)
            result["d1"] = Piece(PieceType.Queen, Color.White)
            result["e1"] = Piece(PieceType.King, Color.White)
            result["f1"] = Piece(PieceType.Bishop, Color.White)
            result["g1"] = Piece(PieceType.Knight, Color.White)
            result["h1"] = Piece(PieceType.Rook, Color.White)

            result["a2"] = Piece(PieceType.Pawn, Color.White)
            result["b2"] = Piece(PieceType.Pawn, Color.White)
            result["c2"] = Piece(PieceType.Pawn, Color.White)
            result["d2"] = Piece(PieceType.Pawn, Color.White)
            result["e2"] = Piece(PieceType.Pawn, Color.White)
            result["f2"] = Piece(PieceType.Pawn, Color.White)
            result["g2"] = Piece(PieceType.Pawn, Color.White)
            result["h2"] = Piece(PieceType.Pawn, Color.White)

            result["a7"] = Piece(PieceType.Pawn, Color.Black)
            result["b7"] = Piece(PieceType.Pawn, Color.Black)
            result["c7"] = Piece(PieceType.Pawn, Color.Black)
            result["d7"] = Piece(PieceType.Pawn, Color.Black)
            result["e7"] = Piece(PieceType.Pawn, Color.Black)
            result["f7"] = Piece(PieceType.Pawn, Color.Black)
            result["g7"] = Piece(PieceType.Pawn, Color.Black)
            result["h7"] = Piece(PieceType.Pawn, Color.Black)

            result["a8"] = Piece(PieceType.Rook, Color.Black)
            result["b8"] = Piece(PieceType.Knight, Color.Black)
            result["c8"] = Piece(PieceType.Bishop, Color.Black)
            result["d8"] = Piece(PieceType.Queen, Color.Black)
            result["e8"] = Piece(PieceType.King, Color.Black)
            result["f8"] = Piece(PieceType.Bishop, Color.Black)
            result["g8"] = Piece(PieceType.Knight, Color.Black)
            result["h8"] = Piece(PieceType.Rook, Color.Black)

            return result
        }
    }
}