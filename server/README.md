#Chess-Stats API Reference
##GET /api/v1/games/stats
returns json containing percentage by piece for each cell  
parameters:  
- filter: String  
sql filter for the games. Examples:
- 'result=0' - games where white won.
- 'whiteElo>2600 and blackElo>2600' - games where rating of both players is higher than 2600
- 'white="Nepomniachtchi,I" or black="Nepomniachtchi,I"' - games of a specific player
- '(white="Nepomniachtchi,I" and result=0) or (black="Nepomniachtchi,I" and result=1' - games of a specific player that they won


full list of fields:
- event - tournament or other event if applicable 
- date - date of the game
- white - name of the player who played white
- black - name of the player who played black
- result - result of the game. 0=white won. 1=black won. 2=draw
- whiteElo - rating of the white side before the game
- blackElo - rating of the black side before the game 
- eco - opening code