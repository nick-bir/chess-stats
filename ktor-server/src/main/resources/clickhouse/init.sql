create table GAMES_TEST
(
    id       Int32,
    white    String,
    black    String,
    whiteElo UInt16,
    blackElo UInt16,
    result Enum8('white' = 1, 'black' = 2, 'draw' = 3),
    stats Map(Enum8(
        'ra1' = 1, 'nb1' = 2, 'bc1' = 3, 'qd1' = 4, 'ke1' = 5, 'bf1' = 6, 'ng1' = 7, 'rh1' = 8,
        'ra8' = 9, 'nb8' = 10, 'bc8' = 11, 'qd8' = 12, 'ke8' = 13, 'bf8' = 14, 'ng8' = 15, 'rh8' = 16
        ), Map(Enum8(
        'a1' = 1, 'b1' = 2, 'c1' = 3, 'd1' = 4, 'e1' = 5, 'f1' = 6, 'g1' = 7, 'h1' = 8
        ), Decimal32(2)))
)
    ENGINE = MergeTree()
        ORDER BY (whiteElo);