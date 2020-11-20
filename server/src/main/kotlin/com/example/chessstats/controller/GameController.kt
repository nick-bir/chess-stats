package com.example.chessstats.controller

import com.example.chessstats.model.domain.Game
import com.example.chessstats.parser.Parser
import com.example.chessstats.parser.Stats
import com.example.chessstats.repository.GameRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityManager

@Controller
@RequestMapping("/api/v1/games")
class GameController(
        val gameRepository: GameRepository,
        val entityManager: EntityManager
) {

    @GetMapping("/stats")
    @ResponseBody
    fun stats(
            @RequestParam(required = false) filter: String?
    ): Stats {
        val games = if (filter.isNullOrBlank())
            gameRepository.findAll().asSequence()
        else {
            val res = entityManager.createQuery("select g from Game g where $filter", Game::class.java).resultList
            res.asSequence()
        }
        return Stats.generateStatsForGames(games.mapNotNull {
            Parser.parsePgn(it.pgn)
        })
    }
}