# Chess Board Analysis
This project proposes various analytics for chess games and chess game sets.

### Planned views:

* Percentage of piece location by board field
* Overall heatmap of the board (showing if the field was taken or free)
* Heatmap of selected pieces (showing what fields were taken by selected pieces)
* Continuous heatmaps

### Planned features:

* filtration of the games by game tags
* groupings of the stats by color/piece/whatever
* sharing results

Calculation of piece occupation:
Res = x'/y*
x' = Σ(x[i]/y[i])
x[i] = number of moves when piece was located at the spot
y[i] = total number of moves in the game
y* = number of games


Sample for some squares:
A1 = {WRA1: 40%, WK: 20%, Blank: 40%}
B1 = {WNB1: 25%, WK: 40%, WRA1: 15%, Blank: 20%}
....
Win rate: w:47, 0: 10, b: 43

Let's say the set above describes avg positions for all games rated 2500+

Now we're looking at specific player as white and get following results:
A1 = {WRA1: 60%, WK: 10%, Blank: 30%}
B1 = {WNB1: 5%, WK: 15%, WRA1: 0%, Blank: 80%}
...
Win rate: w:35, 0: 20, b: 55


Also wer can filter by specific piece position:
e.g. 'only show games where B1 was occupied by king >50% of the game, i.e. white did short castle'
The results:
A1 = {WRA1: 20%, WK: 20%, Blank: 60%}
B1 = {WNB1: 5%, WK: 60%, WRA1: 10%, Blank: 25%}
...
Win rate: w:49, 0: 2, b: 49


