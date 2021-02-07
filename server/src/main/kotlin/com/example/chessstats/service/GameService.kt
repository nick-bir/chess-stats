package com.example.chessstats.service

import com.example.chessstats.model.domain.Game
import com.example.chessstats.model.params.StatsFilterParams
import com.example.chessstats.parser.Parser
import com.example.chessstats.parser.Stats
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import javax.persistence.EntityManager

@Service
class GameService(
        private val entityManager: EntityManager,
        private val applicationContext: AbstractApplicationContext
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Cacheable("stats", sync = true)
    fun stats(params: StatsFilterParams): CompletableFuture<Stats> {
        return applicationContext.getBean(GameService::class.java).statsAsync(params)
    }

    @Async
    fun statsAsync(params: StatsFilterParams): CompletableFuture<Stats> {
        logger.info("Calculating stats for params $params")
        val sqlParams = params.toSqlFilterParams()
        val query = entityManager.createQuery("select g from Game g where ${sqlParams.filters.joinToString(" and ")}", Game::class.java)
        sqlParams.params.forEach { (paramName, paramValue) ->
            query.setParameter(paramName, paramValue)
        }
        val games = query.resultList.asSequence()
        return CompletableFuture.completedFuture(Stats.generateStatsForGames(games.mapNotNull {
            Parser.parsePgn(it.pgn)
        }))
    }
}
