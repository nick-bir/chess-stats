package com.cstats.parser

enum class GameResult {
    WHITE_WINS,
    BLACK_WINS,
    DRAW;
}

enum class Side {
    WHITE,
    BLACK;
}

data class Game(val tags: List<Tag>, val moves: List<Move>) {
    override fun toString(): String {
        val tagsStr = tags.joinToString(separator = "\n", postfix = "\n\n") { it.toString() }
        val movesStr = moves.joinToString(separator = " ", postfix = "\n") { it.toString() }
        return tagsStr + movesStr
    }
}

data class Tag(val src: String, val key: String, val value: String) {
    override fun toString(): String {
        return src
    }
}
data class Move(val src: String) {
    override fun toString(): String {
        return src
    }
}
