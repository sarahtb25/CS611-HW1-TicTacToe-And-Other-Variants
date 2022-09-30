import java.util.Arrays;
import java.util.List;

public class OrderAndChaos extends BoardGame {
    private final int numSquaresPerSide = 6;
    private final int numDimension = 0;
    private final int minNumPlayers = 1;
    private final int maxNumPlayers = 4;
    private List<String> possibleBoardGamePieces = Arrays.asList("X", "O");
    private final List<String> idsAndTeams = Arrays.asList("Order", "Chaos");

    public OrderAndChaos() {

    }

    public OrderAndChaos(List<String> playerNames, Players players) {
        super(Arrays.asList(6), playerNames, players, Arrays.asList("X", "O"));
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
            } else {
                String isGamePieceValidResponse = checkValidGamePiece(gamePiece);
                if (!isGamePieceValidResponse.equals("BGP OK")) {
                    return isGamePieceValidResponse;
                }

                return "OK";
            }
        }
    }

    // This method checks if there is a winner
    @Override
    protected boolean checkGameStatus(List<String> playerNames) {
        String playerOrder = playerNames.get(0); // Always Order
        String playerChaos = playerNames.get(1); // Always Chaos
        String[][] board = getBoard();
        String pieceX =  getBoardGamePieces().get(0);
        String pieceO =  getBoardGamePieces().get(1);

        int numberOfSquaresPerSide = numSquaresPerSide - 1; // need 5 like pieces to win
        int countX = 0, countO = 0;
        boolean hasWinner = false, hasFinished = false;

        // Check each column top to bottom for winner
        for (int i = 0; i <= numberOfSquaresPerSide; i++) {
            for (int j = 0; j < numberOfSquaresPerSide; j++) {
                if (board[j][i].equals(pieceX)) {
                    countX++;
                } else if (board[j][i].equals(pieceO)) {
                    countO++;
                }
            }

            if (countX == numberOfSquaresPerSide || countO == numberOfSquaresPerSide) {
                players.setPlayerScores(playerOrder, "win");
                players.setPlayerScores(playerChaos, "lose");

                hasWinner = true;
                break;
            }

            countX = countO = 0;
        }

        if (!hasWinner) {
            // Check each column bottom to top for winner
            for (int i = 0; i <= numberOfSquaresPerSide; i++) {
                for (int j = numberOfSquaresPerSide; j > 0; j--) {
                    if (board[j][i].equals(pieceX)) {
                        countX++;
                    } else if (board[j][i].equals(pieceO)) {
                        countO++;
                    }
                }

                if (countX == numberOfSquaresPerSide || countO == numberOfSquaresPerSide) {
                    players.setPlayerScores(playerOrder, "win");
                    players.setPlayerScores(playerChaos, "lose");

                    hasWinner = true;
                    break;
                }

                countX = countO = 0;
            }
        }

        if (!hasWinner) {
            // Check each row left to right for winner
            for (int i = 0; i <= numberOfSquaresPerSide; i++) {
                for (int j = 0; j < numberOfSquaresPerSide; j++) {
                    if (board[i][j].equals(pieceX)) {
                        countX++;
                    } else if (board[i][j].equals(pieceO)) {
                        countO++;
                    }
                }

                if (countX == numberOfSquaresPerSide || countO == numberOfSquaresPerSide) {
                    players.setPlayerScores(playerOrder, "win");
                    players.setPlayerScores(playerChaos, "lose");

                    hasWinner = true;
                    break;
                }

                countX = countO = 0;
            }
        }

        if (!hasWinner) {
            // Check each row right to left for winner
            for (int i = 0; i <= numberOfSquaresPerSide; i++) {
                for (int j = numberOfSquaresPerSide; j > 0; j--) {
                    if (board[i][j].equals(pieceX)) {
                        countX++;
                    } else if (board[i][j].equals(pieceO)) {
                        countO++;
                    }
                }

                if (countX == numberOfSquaresPerSide || countO == numberOfSquaresPerSide) {
                    players.setPlayerScores(playerOrder, "win");
                    players.setPlayerScores(playerChaos, "lose");

                    hasWinner = true;
                    break;
                }

                countX = countO = 0;
            }
        }

        if (!hasWinner) {
            // Check one diagonal top left to bottom right for winner
            for (int i = 0; i < numberOfSquaresPerSide; i++) {
                if (board[i][i].equals(pieceX)) {
                    countX++;
                } else if (board[i][i].equals(pieceO)) {
                    countO++;
                }
            }

            if (countX == numberOfSquaresPerSide || countO == numberOfSquaresPerSide) {
                players.setPlayerScores(playerOrder, "win");
                players.setPlayerScores(playerChaos, "lose");

                hasWinner = true;
            }

            countX = countO = 0;
        }

        if (!hasWinner) {
            // Check one diagonal bottom right to top left for winner
            for (int i = numberOfSquaresPerSide; i > 0; i--) {
                if (board[i][i].equals(pieceX)) {
                    countX++;
                } else if (board[i][i].equals(pieceO)) {
                    countO++;
                }
            }

            if (countX == numberOfSquaresPerSide || countO == numberOfSquaresPerSide) {
                players.setPlayerScores(playerOrder, "win");
                players.setPlayerScores(playerChaos, "lose");

                hasWinner = true;
            }

            countX = countO = 0;
        }

        if (!hasWinner) {
            // Check other diagonal top right to bottom left for winner
            for (int i = 0; i < numberOfSquaresPerSide; i++) {
                if (board[i][numberOfSquaresPerSide - i].equals(pieceX)) {
                    countX++;
                } else if (board[i][numberOfSquaresPerSide - i].equals(pieceO)) {
                    countO++;
                }
            }

            if (countX == numberOfSquaresPerSide || countO == numberOfSquaresPerSide) {
                players.setPlayerScores(playerOrder, "win");
                players.setPlayerScores(playerChaos, "lose");

                hasWinner = true;
            }

            countX = countO = 0;
        }

        if (!hasWinner) {
            // Check other diagonal bottom left to top right for winner
            for (int i = numberOfSquaresPerSide; i > 0; i--) {
                if (board[i][numberOfSquaresPerSide - i].equals(pieceX)) {
                    countX++;
                } else if (board[i][numberOfSquaresPerSide - i].equals(pieceO)) {
                    countO++;
                }
            }

            if (countX == numberOfSquaresPerSide || countO == numberOfSquaresPerSide) {
                players.setPlayerScores(playerOrder, "win");
                players.setPlayerScores(playerChaos, "lose");

                hasWinner = true;
            }

            countX = countO = 0;
        }

        if (!hasWinner) {
            int numSquaresFilled = 0;
            for (int i = 0; i < numberOfSquaresPerSide; i++) {
                for (int j = 0; j < numberOfSquaresPerSide; j++) {
                    if (board[i][j].equals(pieceO) || board[i][j].equals(pieceX)) {
                        numSquaresFilled++;
                    }
                }
            }

            if (numSquaresFilled == numSquaresPerSide * numSquaresPerSide) {
                hasFinished = true;
                players.setPlayerScores(playerOrder, "lose");
                players.setPlayerScores(playerChaos, "win");
            }
        }

        if (hasWinner) {
            hasFinished = true;
        }

        return hasFinished;
    }
}
