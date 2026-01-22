# A tick-tack-toe game
Basically, my program is asking one question every turn:

```“Given the current board, where is the most dangerous or most valuable move to play next?”```

It isn't an undefeatable machine, but a heuristic approach to solve the well-defined problem:
* Within a 3x3 squares, 2 players (called user & machine) take turns marking empty squares with 'X' or 'O', aiming to be the first to get three of their marks in a row.
* Each winning move is just a set of 3 coordinates,  e.g.\
	•	Diagonal: [(0,0), (1,1), (2,2)]\
	•	Top row: [(0,0), (0,1), (0,2)]
* There are exactly 8 ways to win:\
	•	3 rows\
	•	3 columns\
	•	2 diagonals\
## The point of my program isn't about endless if-else, but it just:
* Apply the game rules above to decide if user win or lose.
* Use for each loop to iterate through the squares, adding all winning moves, e.g. ((0,0), (1,1), (2,2)).
* When a user pick a square, the winning move pool is **recalculate**.
* Tell machine where to move next to block user from winning or choose the its best move.
* My program views a "best" move when its score is highest. Consider this score map (winning pool):

<img width="203" height="91" alt="Screenshot 2026-01-22 at 11 50 31" src="https://github.com/user-attachments/assets/c8577be0-ec6b-4372-b294-1f383771a685" />

The game starts with the empty state, and the center square is the best choice (score = 4), followed by the four corner squares (score = 3). This is because:

* The center square is part of 4 possible winning moves:\
	•	(0,1) (1,1) (2,1) \
	•	(1,0) (1,1) (1,2) \
	•	(0,0) (1,1) (2,2) \
	•	(0,2) (1,1) (2,0)
* Each corner square is part of 3 possible winning moves.
 For example, the corner (0,0) belongs to:
	•	(0,0) (0,1) (0,2) \
	•	(0,0) (1,0) (2,0) \
	•	(0,0) (1,1) (2,2)

So, by looking at the "winning pool", my program can find the best move it can take!


<img width="260" height="548" alt="Screenshot 2026-01-22 at 13 00 18" src="https://github.com/user-attachments/assets/04e2c0f5-3042-442b-a4e1-a67b79703b60" />

<img width="259" height="553" alt="Screenshot 2026-01-22 at 13 00 50" src="https://github.com/user-attachments/assets/741b6dcf-cadd-4d3e-bbbd-7fa23e08e864" />

