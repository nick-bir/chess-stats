# Chess-Stats API Reference
## GET /api/v1/games/stats
returns json containing percentage by piece for each cell. Available filter parameters:
- player: String - name of a player
- result: {WIN|LOSS|DRAW} - applicable only if the player or side is specified, otherwise ignored
- side: {BLACK|WHITE} - this parameter depends on player and result params. Logic is as follow:
    - if the player is specified, this parameters sets side of a player in the set.
    - if the player is not specified and result is WIN or LOSS, filters games won or lost by specified side
    - otherwise ignored
- minRating: String - minimum rating of both opponents
- maxRating: String - maximum rating of both opponents
- event: String - name of an event
- startDate: String - games that were played after this date
- endDate: String - games that were played before this date