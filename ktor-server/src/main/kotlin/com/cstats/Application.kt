package com.cstats

import com.cstats.parser.RoughParser
import com.cstats.plugins.configureRouting
import io.ktor.server.application.*

fun main() {
    println(System.currentTimeMillis())
    val games = RoughParser.parsePgnList(Application::class.java.classLoader.getResourceAsStream("lichess-2016-03.pgn")!!, true)
//    val games = Parser.parsePgnList(Application::class.java.classLoader.getResourceAsStream("pgn-batch-0.pgn")!!, true)
    println(System.currentTimeMillis())
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
//        .start(wait = true)
}

fun Application.module() {
    configureRouting()
}
