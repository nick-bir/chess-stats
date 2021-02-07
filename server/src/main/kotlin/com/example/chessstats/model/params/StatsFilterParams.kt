package com.example.chessstats.model.params

import com.example.chessstats.model.domain.GameResult
import com.example.chessstats.model.domain.Side
import java.util.*

data class StatsFilterParams(
        val player: String? = null,
        val side: Side? = null,
        val result: GameResult? = null,
        val minRating: Int? = null,
        val maxRating: Int? = null,
        val event: String? = null,
        val startDate: Date? = null,
        val endDate: Date? = null
) {
    fun toSqlFilterParams(): SqlFilterParams {
        val filter = mutableListOf<String>()
        val paramMap = mutableMapOf<String, Any>()
        if (player != null) {
            filter += when (result) {
                GameResult.WIN -> when (side) {
                    Side.WHITE -> "g.result = 0 and g.white like :player"
                    Side.BLACK -> "g.result = 1 and g.black like :player"
                    else -> "(g.result = 0 and g.white like :player or g.result = 1 and g.black like :player)"
                }
                GameResult.LOSS -> when (side) {
                    Side.WHITE -> "g.result = 1 and g.white like :player"
                    Side.BLACK -> "g.result = 0 and g.black like :player"
                    else -> "(g.result = 1 and g.white like :player or g.result = 0 and g.black like :player)"
                }
                GameResult.DRAW -> when (side) {
                    Side.WHITE -> "g.result = 2 and g.white like :player"
                    Side.BLACK -> "g.result = 2 and g.black like :player"
                    else -> "g.result = 2 and (g.white like :player or g.black like :player)"
                }
                else -> when (side) {
                    Side.WHITE -> "g.white like :player"
                    Side.BLACK -> "g.black like :player"
                    else -> "(g.white like :player or g.black like :player)"
                }
            }
            paramMap += "player" to player
        } else if (side != null) {
            val resultFilter = if (side == Side.WHITE && result == GameResult.WIN || side == Side.BLACK && result == GameResult.LOSS) 0 else 1
            if (result != null && result != GameResult.DRAW) filter += "g.result = $resultFilter"
        }
        if (minRating != null) {
            filter += "(g.whiteElo >= :minRating and g.blackElo >= :minRating)"
            paramMap += "minRating" to minRating
        }
        if (maxRating != null) {
            filter += "(g.whiteElo <= :maxRating and g.blackElo <= :maxRating)"
            paramMap += "maxRating" to maxRating
        }
        if (event != null) {
            filter += "(g.event like :event)"
            paramMap += "event" to event
        }
        if (startDate != null) {
            filter += "g.date >= :startDate"
            paramMap += "startDate" to startDate
        }
        if (endDate != null) {
            filter += "date(g.date) <= :endDate"
            paramMap += "endDate" to endDate
        }
        if (filter.isEmpty()) filter += "1=1"
        return SqlFilterParams(filter, paramMap)
    }
}