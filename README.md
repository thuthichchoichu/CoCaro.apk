The core of my program is how to detect when a user or my machine has a chance to win.

Calculate all the moves of the 3x3 board (2D array). It has eight moves to win. A move to win includes 3 coordinates, for example: 00 11 22.


00 | 01 | 02


---+---+----


10 | 11 | 12


---+---+----


20 | 21 | 22

2.Loop for all coordinates of the user or my machine, searching in that, if any 2 coordinates in a set of 3 moves calculated above, the left move is where to block.

#Pausedo code
if (1): Check My Machine Can Win, then pick those coordinates and end the game.

else if (2) Check my user can Win if(user has chance win) -> block that coordinates

else (3) Find a free coordinates with has highest score -> pick that coordinates,

When there are more moves to win when going through it, the move with the highest score takes precedence over the others.

In 3x3 like this:


--3--|--2--|--3--


---+----+------+


--3--|--4--|--3--


---+----+------+


--3--|--2--|--3--

Other rules

Both of user and machine has moves to win togetherly equal when game begin - 8 move


when user or machine pick a coordinate, all of their opponent move to win which has include this coordinate will be remove - to reduce amount step of calculation.
