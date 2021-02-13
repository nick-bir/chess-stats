package com.example.chessstats.spring

import com.example.chessstats.model.params.StatsFilterParams
import com.example.chessstats.service.GameService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Configuration
@EnableCaching
class CacheConfig {
    @Bean
    fun cacheManager(): CacheManager {
        return ConcurrentMapCacheManager("stats")
    }
}

@Component
class CacheInit(
        private val gameService: GameService
) : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        gameService.stats(StatsFilterParams())
    }
}