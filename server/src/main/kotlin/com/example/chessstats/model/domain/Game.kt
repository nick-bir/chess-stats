package com.example.chessstats.model.domain

import com.example.chessstats.parser.ParsedGame
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name="games")
data class Game(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val event: String?,
        val date: Date?,
        val white: String?,
        val black: String?,
        val result: Int?,
        val whiteElo: Int?,
        val blackElo: Int?,
        val eco: String?,
        val pgn: String
) {
        companion object {
                val df = SimpleDateFormat("yyyy.MM.dd")

                fun fromParsedGame(parsedGame: ParsedGame) = Game(
                        event = parsedGame.meta["Event"],
                        date = parsedGame.meta["Date"]?.let {
                                try {
                                        df.parse(it)
                                } catch (e: Exception) {
                                        null
                                }
                        },
                        white = parsedGame.meta["White"],
                        black = parsedGame.meta["Black"],
                        result = parsedGame.meta["Result"]?.let { parseResult(it) },
                        whiteElo = parsedGame.meta["WhiteElo"]?.toInt(),
                        blackElo = parsedGame.meta["BlackElo"]?.toInt(),
                        eco = parsedGame.meta["ECO"],
                        pgn = parsedGame.pgn
                )

                private fun parseResult(result: String) = when (result) {
                        "1-0" -> 0
                        "0-1" -> 1
                        else -> 2
                }
        }
}