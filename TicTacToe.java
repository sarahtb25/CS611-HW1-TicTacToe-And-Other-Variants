import java.util.Arrays;
import java.util.List;

// This class handles the logic for Tic Tac Toe
public class TicTacToe extends BoardGame {
    private final int numDimension = 1;
    private final int minDimension = 3;
    private final int maxDimension = 10;
    private final int minNumPlayers = 1;
    private final int maxNumPlayers = 4;
    private List<String> possibleBoardGamePieces = Arrays.asList("X", "O");
    private final List<String> idsAndTeams = Arrays.asList("X", "O");

    public TicTacToe() {

    }

    public TicTacToe(List<Integer> numSquaresPerSide, List<String> playerNames, Players players) {
        // X and O represents the available board game pieces
        super(numSquaresPerSide, playerNames, players, Arrays.asList("X", "O"));
    }

    public List<Integer> getMinMaxDimensions() {
        List<Integer> minMaxDimensions = Arrays.asList(minDimension, maxDimension);

        return minMaxDimensions;
    }

    public List<Integer> getMinMaxNumPeoplePerTeam() {
        List<Integer> minMaxNumPeople = Arrays.asList(minNumPlayers, maxNumPlayers);

        return minMaxNumPeople;
    }

    public int getNumberOfDimension() {
        return numDimension;
    }

    public List<String> getPossibleIdsAndTeams() {
        return idsAndTeams;
    }

    public int getNumberOfTeams() {
        return idsAndTeams.size();
    }

    @Override
    protected String setMove(int row, int column, String gamePiece) {
        String response = checkValidMove(row, column, gamePiece);

        if (response.equals("OK")) {
            setBoard(row - 1, column - 1, gamePiece);
            return "OK";
        } else {
            return response;
        }
    }

    @Override
    protected String checkValidMove(int row, int column, String gamePiece) {
        int numSquaresPerSide = getBoardDimensions().get(0);
        String[][] board = getBoard();

        // Location is out of bounds
        if (row < 1 || row > numSquaresPerSide || column < 1 || column > numSquaresPerSide) {
            return "LOOB";
        } else {
            // Location is occupied
            if (!board[row - 1][column - 1].equals("-")) {
                return "LO";
            }
        }

        return "OK";
    }

    // This method checks if there is a winner
    @Override
    protected boolean checkGameStatus(List<String> playerNames) {
        String playerX = playerNames.get(0); // Always X
        String playerO = playerNames.get(1); // Always O
        String[][] board = getBoard();
        String idX = getBoardGamePieces().get(0);
        String idO = getBoardGamePieces().get(1);

        int numberOfSquaresPerSide = getBoardDimensions().get(0);
        int countX = 0, countO = 0;
        boolean hasWinner = false, hasFinished = false;

        // Check each column for winner
        for (int i = 0; i < numberOfSquaresPerSide; i++) {
            for (int j = 0; j < numberOfSquaresPerSide; j++) {
                if (board[j][i].equals(idX)) {
                    countX++;
                } else if (board[j][i].equals(idO)) {
                    countO++;
                }
            }

            if (countX == numberOfSquaresPerSide) {
                players.setPlayerScores(playerX, "win");
                players.setPlayerScores(playerO, "lose");

                hasWinner = true;
                break;
            } else if (countO == numberOfSquaresPerSide) {
                players.setPlayerScores(playerX, "lose");
                players.setPlayerScores(playerO, "win");

                hasWinner = true;
                break;
            }

            countX = countO = 0;
        }

        if (!hasWinner) {
            // Check each row for winner
            for (int i = 0; i < numberOfSquaresPerSide; i++) {
                for (int j = 0; j < numberOfSquaresPerSide; j++) {
                    if (board[i][j].equals(idX)) {
                        countX++;
                    } else if (board[i][j].equals(idO)) {
                        countO++;
                    }
                }

                if (countX == numberOfSquaresPerSide) {
                    players.setPlayerScores(playerX, "win");
                    players.setPlayerScores(playerO, "lose");

                    hasWinner = true;
                    break;
                } else if (countO == numberOfSquaresPerSide) {
                    players.setPlayerScores(playerX, "lose");
                    players.setPlayerScores(playerO, "win");

                    hasWinner = true;
                    break;
                }

                countX = countO = 0;
            }
        }

        if (!hasWinner) {
            // Check one diagonal for winner
            for (int i = 0; i < numberOfSquaresPerSide; i++) {
                if (board[i][i].equals(idX)) {
                    countX++;
                } else if (board[i][i].equals(idO)) {
                    countO++;
                }
            }

            if (countX == numberOfSquaresPerSide) {
                players.setPlayerScores(playerX, "win");
                players.setPlayerScores(playerO, "lose");

                hasWinner = true;
            } else if (countO == numberOfSquaresPerSide) {
                players.setPlayerScores(playerX, "lose");
                players.setPlayerScores(playerO, "win");

                hasWinner = true;
            }

            countX = countO = 0;
        }

        if (!hasWinner) {
            // Check other diagonal for winner
            for (int i = 0; i < numberOfSquaresPerSide; i++) {
                if (board[i][numberOfSquaresPerSide - 1 - i].equals(idX)) {
                    countX++;
                } else if (board[i][numberOfSquaresPerSide - 1 - i].equals(idO)) {
                    countO++;
                }
            }

            if (countX == numberOfSquaresPerSide) {
                players.setPlayerScores(playerX, "win");
                players.setPlayerScores(playerO, "lose");

                hasWinner = true;
            } else if (countO == numberOfSquaresPerSide) {
                players.setPlayerScores(playerX, "lose");
                players.setPlayerScores(playerO, "win");

                hasWinner = true;
            }

            countX = countO = 0;
        }

        if (!hasWinner) {
            int numSquaresFilled = 0;
            for (int i = 0; i < numberOfSquaresPerSide; i++) {
                for (int j = 0; j < numberOfSquaresPerSide; j++) {
                    if (board[i][j].equals(idX) || board[i][j].equals(idO)) {
                        numSquaresFilled++;
                    }
                }
            }

            if (numSquaresFilled == numberOfSquaresPerSide * numberOfSquaresPerSide) {
                hasFinished = true;
                players.setPlayerScores(playerX, "tie");
                players.setPlayerScores(playerO, "tie");
            }
        }

        if (hasWinner) {
            hasFinished = true;
        }

        return hasFinished;
    }
}
