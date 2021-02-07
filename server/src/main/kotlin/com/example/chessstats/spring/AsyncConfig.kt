package com.example.chessstats.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor


@Configuration
@EnableAsync
class AsyncConfig {
    @Bean
    fun taskExecutor(): Executor? {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = Runtime.getRuntime().availableProcessors()
        executor.maxPoolSize = executor.corePoolSize
        executor.setQueueCapacity(100)
        executor.setThreadNamePrefix("AsyncThread-")
        executor.initialize()
        return executor
    }
}