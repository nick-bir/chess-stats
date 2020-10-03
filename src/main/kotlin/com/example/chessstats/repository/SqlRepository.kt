package com.example.chessstats.repository

import com.example.chessstats.model.domain.Game
import org.springframework.data.repository.CrudRepository

interface GameRepository: CrudRepository<Game, Long>