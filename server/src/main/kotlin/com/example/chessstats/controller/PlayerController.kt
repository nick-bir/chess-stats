package com.example.chessstats.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager

@CrossOrigin
@RestController
@RequestMapping("/api/v1/players")
class PlayerController(
        val entityManager: EntityManager
) {
    @GetMapping
    fun players(): List<Any?> {
        return entityManager.createNativeQuery("""
            select player, count(player) cid from (
            select g.white player from games g union all select distinct g.black from games g
            ) t group by player order by cid desc
        """.trimIndent()).resultList
    }
}