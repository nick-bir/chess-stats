# Chess-Stats API Reference
## GET /api/v1/games/stats
returns json containing percentage by piece for each cell. Available filter parameters:
- player: String - name of a player
- result: {WIN|LOSS|DRAW} - applicable only if the player was specified
- minRating: String - minimum rating of both opponents
- maxRating: String - maximum rating of both opponents
- event: String - name of an event
- startDate: String - games that were played after this date
- endDate: String - games that were played before this date