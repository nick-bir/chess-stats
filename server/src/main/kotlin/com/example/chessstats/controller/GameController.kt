package com.example.chessstats.controller

import com.example.chessstats.model.domain.Game
import com.example.chessstats.model.domain.GameResult
import com.example.chessstats.parser.Parser
import com.example.chessstats.parser.Stats
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.EntityManager

@CrossOrigin
@RestController
@RequestMapping("/api/v1/games")
class GameController(
        val entityManager: EntityManager
) {

    @GetMapping("/stats")
    fun stats(
            @RequestParam(required=false) player: String?,
            @RequestParam(required=false) result: GameResult?,
            @RequestParam(required=false) minRating: Int?,
            @RequestParam(required=false) maxRating: Int?,
            @RequestParam(required=false) event: String?,
            @RequestParam(required=false) startDate: Date?,
            @RequestParam(required=false) endDate: Date?
    ): Stats {
        val filter = mutableListOf<String>()
        val paramMap = mutableMapOf<String, Any>()
        if (player != null) {
            filter += when (result) {
                GameResult.WIN -> "(g.result = 0 and g.white like :player or g.result = 1 and g.black like :player)"
                GameResult.LOSS -> "(g.result = 1 and g.white like :player or g.result = 0 and g.black like :player)"
                GameResult.DRAW -> "g.result = 2 and (g.white like :player or g.black like :player)"
                else -> "(g.white like :player or g.black like :player)"
            }
            paramMap += "player" to player
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
        if (filter.isEmpty()) filter += "1=1"
        val query = entityManager.createQuery("select g from Game g where ${filter.joinToString(" and ")}", Game::class.java)
        paramMap.forEach { (paramName, paramValue) ->
            query.setParameter(paramName, paramValue)
        }
        val games = query.resultList.asSequence()
        return Stats.generateStatsForGames(games.mapNotNull {
            Parser.parsePgn(it.pgn)
        })
    }
}