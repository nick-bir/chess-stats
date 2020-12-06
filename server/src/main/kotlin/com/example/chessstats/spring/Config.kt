package com.example.chessstats.spring

import com.example.chessstats.model.domain.Game
import com.example.chessstats.parser.Parser
import com.example.chessstats.repository.GameRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component

@Component
class Config(
        private val gameRepository: GameRepository
): InitializingBean {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun afterPropertiesSet() {
        if (gameRepository.count() > 0) {
            log.info("DB initialization skipped")
            return
        }
        log.info("DB initialization started")
        val resourceResolver = PathMatchingResourcePatternResolver(this.javaClass.classLoader)
        resourceResolver.getResources("classpath*:*.pgn").forEach { res ->
            res.inputStream.use { stream ->
                Parser.parsePgnFile(stream).forEach {
                    gameRepository.save(Game.fromParsedGame(it))
                }
            }
        }
        log.info("DB initialization finished")
    }
}