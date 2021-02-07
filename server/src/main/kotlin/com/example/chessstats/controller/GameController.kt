package com.example.chessstats.controller

import com.example.chessstats.model.domain.GameResult
import com.example.chessstats.model.domain.Side
import com.example.chessstats.model.params.StatsFilterParams
import com.example.chessstats.parser.Stats
import com.example.chessstats.service.GameService
import org.slf4j.LoggerFactory
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.util.*

@CrossOrigin
@RestController
@RequestMapping("/api/v1/games")
class GameController(
        private val gameService: GameService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/stats")
    fun stats(
            @RequestParam(required=false) player: String?,
            @RequestParam(required=false) side: Side?,
            @RequestParam(required=false) result: GameResult?,
            @RequestParam(required=false) minRating: Int?,
            @RequestParam(required=false) maxRating: Int?,
            @RequestParam(required=false) event: String?,
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: Date?,
            @RequestParam(required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: Date?
    ): Stats {
        val params = StatsFilterParams(player, side, result, minRating, maxRating, event, startDate, endDate)
        logger.info("Incoming request params: $params")
        return gameService.stats(params).get()
    }
}