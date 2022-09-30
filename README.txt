# CS611-HW1
## Tic Tac Toe and other variants
---------------------------------------------------------------------------
Sarah Taaher Bonna
sbonna@bu.edu
U86084310

## Files
---------------------------------------------------------------------------
1. Main.java: This file calls the methods specified in Helper.java.
2. Helper.java: This file contains the methods that get and store the user inputs required for the games.
                It also handles the changing of turns, and switching of team members after each round of the
                game as needed.
3. Game.java: This file contains the Game class, which instantiates the Players class to allow the classes
              that inherit this class either directly or via constructor chaining, to be able to access and
              manipulate the details of the relevant players (e.g. to update the scores of the players)
              as needed.
4. BoardGame.java: This file contains the Board Game class, which serves as the intermediary between the Board
                   class and the specific game class (e.g. TicTacToe and Order and Chaos)
5. Board.java: This file contains the Board class, which handles all actions related to the board.
6. BoardGamePieces.java: This file contains the BoardGamePieces class, which stores the information of
                         the possible board game pieces available for a particular game.
7. Players.java: This file contains the Players class, which handles all actions related to the players.
8. TicTacToe.java: This file contains the logic for the Tic Tac Toe game.
9. OrderAndChaos.java: This file contains the logic for the Order and Chaos game.

## Notes
---------------------------------------------------------------------------
1. There are no files to be parsed, thus ConfigFiles does not exist.
2. If there is more than 1 player in a team, then each round will have new players in the order
   that their names were entered. For example, if for Tic Tac Toe, Team X has Harry and William,
   and Team O has Tom and Jerry, the first round will be played by Harry (X) and Tom (O), the second
   round (if the game is continued) will be played by William (X) and Jerry (O). The third round (if
   the game is continued), will have Harry and Tom, and the fourth round (if the game is continued)
   will be played by William and Jerry, and the cycle continues until any player enters "N" or "n" after
   a round of game ends.
3. None

## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "HW1/src" after unzipping the files
2. Run the following instructions:
javac *.java
java Main

## Input/Output Example
---------------------------------------------------------------------------
Output:
Welcome to CS611 Arcade!
We only offer 2 games at this moment:
1. Tic Tac Toe
2. Order And Chaos
Please enter 1 or 2 to select a game.
Input: 1
Output:
You have selected Tic Tac Toe.
Please enter the number of squares on each side. Value must be between 3 and 10.
Input: 3
Output: Please enter the number of players per team. Minimum 1 player and maximum 4 players.
Input: 2
Output: Please enter the name of player(s) who is/are playing X
Input:
Sarah
Jane
Output: Please enter the name of player(s) who is/are playing O
Input:
Tom
Jerry
Output: Please enter who is starting first: X or O.
Input: X
Output:
+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
Sarah (X), please enter the location e.g. 1,1:
Input: 1,1
Output:
+---+---+---+
| X |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
Tom (O), please enter the location e.g. 1,1:
Input: 1,2
Output:
+---+---+---+
| X | O |   |
+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
Sarah (X), please enter the location e.g. 1,1:
.
.
.
Output:
+---+---+---+
| X | O | O |
+---+---+---+
|   | X |   |
+---+---+---+
|   |   |   |
+---+---+---+
Sarah (X), please enter the location e.g. 1,1:
Input: 3,3
Output:
+---+---+---+
| X | O | O |
+---+---+---+
|   | X |   |
+---+---+---+
|   |   | X |
+---+---+---+
Sarah wins! Congratulations!
Tom loses! Better luck next time!

Sarah has 1 win(s), 0 loss(es) and 0 ties.
Tom has 0 win(s), 1 loss(es) and 0 ties.

Would you like to play another round? Enter Y or y to continue, N or n to end.
Input: y
Output:
Please enter who is starting first: X or O.
Input: O
Output:
+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
Jerry (O), please enter the location e.g. 1,1:
Input: 1,1
Output:
+---+---+---+
| O |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
Jane (X), please enter the location e.g. 1,1:
Input: 2,2
Output:
+---+---+---+
| O |   |   |
+---+---+---+
|   | X |   |
+---+---+---+
|   |   |   |
+---+---+---+
Jerry (O), please enter the location e.g. 1,1:
.
.
.
Output:
+---+---+---+
| O | X | O |
+---+---+---+
| X | X |   |
+---+---+---+
| X | O | O |
+---+---+---+
Jerry (O), please enter the location e.g. 1,1:
Input: 2,3
Output:
+---+---+---+
| O | X | O |
+---+---+---+
| X | X | O |
+---+---+---+
| X | O | O |
+---+---+---+
Jane loses! Better luck next time!
Jerry wins! Congratulations!

Jane has 0 win(s), 1 loss(es) and 0 ties.
Jerry has 1 win(s), 0 loss(es) and 0 ties.

Would you like to play another round? Enter Y or y to continue, N or n to end.
n

Score Summary:
Team X has 1 win(s), 1 loss(es) and 0 ties.
        Player Sarah has 1 win(s), 0 loss(es) and 0 ties.
        Player Jane has 0 win(s), 1 loss(es) and 0 ties.

Team O has 1 win(s), 1 loss(es) and 0 ties.
        Player Tom has 0 win(s), 1 loss(es) and 0 ties.
        Player Jerry has 1 win(s), 0 loss(es) and 0 ties.