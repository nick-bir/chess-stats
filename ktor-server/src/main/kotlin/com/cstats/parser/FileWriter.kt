package com.cstats.parser

import java.io.FileOutputStream

// Temp Object to simplify large file processing
object FileWriter {
    private var counter = 0;
    fun writeGames(games: List<Game>) {
        if (games.isEmpty()) {
            return
        }
        val file = FileOutputStream("lichess-2016-03/pgn-batch-${counter++}.pgn")
        file.write(games.joinToString(separator = "\n") { game ->
            game.toString()
        }.toByteArray())
    }
}