package com.cstats.parser

import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

enum class ParserState {
    START,
    MOVES,
    INVALID
}

object Parser {
    val moveRegexps = listOf<Regex>(
        Regex("[abcdefgh][234567]#?"), // regular pawn move
        Regex("[abcdefgh][18]=[RNBQ]#?"), // promotion pawn move
        Regex("[abcdefgh]x[abcdefgh][234567]#?"), // pawn takes move
        Regex("[abcdefgh]x[abcdefgh][18]=[RNBQ]#?"), // promotion pawn takes move
        Regex("O-O(-O)?#?"), // castling
        Regex("[RNBQK][12345678]?[abcdefgh]?x?[abcdefgh][12345678]#?"), // piece move and piece take move
    )
    val possibleResults = listOf("1/2-1/2", "1-0", "0-1")
    val movesSeparator = Regex("\\d+\\.")
    val evalRegex = Regex("\\{ \\[%eval [^]]+] } ")
    val evalContinueRegex = Regex("(\\d+)\\.\\.\\. ")

    fun removeEval(allMoves: String): String {
        val noEval = allMoves.replace(evalRegex, "")
        return noEval.replace(evalContinueRegex, "")
    }
    fun buildMoves(cleanMoves: String): List<Move> {
        val moves = cleanMoves.split(movesSeparator).filter { it.isNotBlank() }.map { it.trim() }
        return moves.map { Move(it) }
    }

    enum class GameValidationResult {
        VALID,
        NO_MOVES,
        NO_TIME_CONTROL,
        BAD_TIME_CONTROL,
        NO_RATING,
        BAD_RATING;
    }

    fun validateGame(tags: List<Tag>, moves: List<Move>): GameValidationResult {
        if (moves.size < 10) {
            return GameValidationResult.NO_MOVES
        }
        val timeControlTag = tags.find { it.key == "TimeControl" } ?: return GameValidationResult.NO_TIME_CONTROL
        val timeControl = timeControlTag.value.split('+')[0].toIntOrNull()
        if (timeControl != null && timeControl < 300) {
            return GameValidationResult.BAD_TIME_CONTROL
        }
        val whiteEloTag = tags.find { it.key == "WhiteElo" } ?: return GameValidationResult.NO_RATING
        val whiteElo = whiteEloTag.value.toIntOrNull()
        val blackEloTag = tags.find { it.key == "BlackElo" } ?: return GameValidationResult.NO_RATING
        val blackElo = blackEloTag.value.toIntOrNull()
        if (whiteElo == null || blackElo == null) {
            return GameValidationResult.NO_RATING
        }
        if (whiteElo < 2000 || blackElo < 2000) {
            return GameValidationResult.BAD_RATING
        }
        return GameValidationResult.VALID
    }

    val tagRegexp = Regex("\\[(\\w+)\\s+\"(.+)\"]")

    var savedBatches = 0
    var batchSize = 100_000
    val validationResultMap = mutableMapOf<GameValidationResult, Int>()
    val games = LinkedList<Game>()
    var processed = 0
    fun processNextGame(tags: List<Tag>, allMoves: String, write: Boolean) {
        processed++
        if (processed % batchSize == 0) {
            println("Total processed: $processed")
        }
        val cleanMoves = if (allMoves.contains("%eval")) removeEval(allMoves) else allMoves
        val moves = buildMoves(cleanMoves)
        val validationResult = validateGame(tags, moves)
        validationResultMap[validationResult] = validationResultMap.getOrDefault(validationResult, 0) + 1
        if (validationResult == GameValidationResult.VALID) {
            games += Game(tags = tags, moves = listOf(Move(cleanMoves)))
        }
        if (games.size > batchSize) {
            savedBatches++
            println("Total processed: ${processed}. Saved: ${savedBatches * batchSize}")
            if (write) {
                FileWriter.writeGames(games)
            }
            games.clear()
        }
    }


    fun parsePgnList(source: InputStream, write: Boolean): List<Game> {
        val reader = InputStreamReader(source)
        var state = ParserState.START
        val allMoves = StringBuilder()
        var tags = LinkedList<Tag>()
        reader.forEachLine { line ->
            if (line.isBlank()) {
                if (state == ParserState.START) {
                    state = ParserState.MOVES
                } else {
                    state = ParserState.START
                    processNextGame(tags, allMoves.toString(), write)
                    allMoves.clear()
                    tags = LinkedList()
                }
                return@forEachLine
            }
            if (state == ParserState.START) {
                val tagMatchResult = tagRegexp.matchAt(line, 0)
                if (tagMatchResult != null) {
                    tags.add(Tag(line, tagMatchResult.groupValues[1], tagMatchResult.groupValues[2]))
                }
            }
            if (state == ParserState.MOVES) {
                allMoves.append(line)
            }
        }
        processNextGame(tags, allMoves.toString(), write)
        if (write) {
            FileWriter.writeGames(games)
        }
        validationResultMap.forEach {
            println("${it.key}: ${it.value}")
        }
        return games
    }
}