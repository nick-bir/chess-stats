package com.cstats.dto

import com.cstats.parser.GameResult

/**
 * Stands for the Piece and it's starting position. Promoted pieces are not taken into account
 */
enum class Piece {
    RA1,
    NB1,
    BC1,
    QD1,
    KE1,
    BF1,
    NG1,
    RH1,
    PA2,
    PB2,
    PC2,
    PD2,
    PE2,
    PF2,
    PG2,
    PH2,
    PA7,
    PB7,
    PC7,
    PD7,
    PE7,
    PF7,
    PG7,
    PH7,
    RA8,
    NB8,
    BC8,
    QD8,
    KE8,
    BF8,
    NG8,
    RH8
}
data class GameStats(
    val white: String,
    val wElo: Int,
    val black: String,
    val bElo: Int,
    val timeControl: Int,
    val timeControlInc: Int,
    val result: GameResult
)