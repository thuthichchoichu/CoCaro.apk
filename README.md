 Link to apk file: https://github.com/thuthichchoichu/apk/blob/main/CoCaro.apk

# The core of my program is how to detect when user or my machine has chance to win
1. Calculate all move of 3x3 board - 2d array. It has 8 move to win, a move to win include 3 coordinate, example 00 11 22.
 *   00 | 01 | 02
 *  ---+---+-----
 *   10 | 11 | 12
 *  ---+---+-----
 *   20 | 21 | 22

2.Loop for all coordinates of user or my machine, searching in that, if any 2 coordinate in a a set of 3 move calculated in above, left move is where to block   


# Pausedo code 
• Details about my turn 
- if (1): Check my machine can Win -> pick that coordinates and end game
- else if (2) Check my user can Win if(user has chance win) -> block that coordinates 
- else (3) Find a free coordinates with has highest score -> pick that coordinates, 

# The move which has highscore rather than other when has more move to win go through it.
- In 3x3 like this:
 *  3 |  2 |  3
 * ---+----+------+
 *  3 |  4 |  3
 * ---+----+------+
 *  3 |  2 |  3


# Other rules
- Both of user and machine has moves to win togetherly equal when game begin - 8 move
- when user or machine pick a coordinate, all of their opponent move to win which has include this coordinate will be remove - to reduce amount step of calculation.
    

