import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Integer> numSquaresPerSide = new ArrayList<>();
    private String[][] board;
    private BoardGamePieces boardGamePieces;

    public Board(List<Integer> numSquaresPerSide, List<String> possibleGamePieces) {
        setDimensionsOfBoard(numSquaresPerSide);

        board = new String[this.numSquaresPerSide.get(0)][this.numSquaresPerSide.get(1)];
        initBoard();
        boardGamePieces = new BoardGamePieces(possibleGamePieces);
    }

    public void setDimensionsOfBoard(List<Integer> numSquaresPerSide) {
        for (int i = 0; i < numSquaresPerSide.size(); i++) {
            this.numSquaresPerSide.add(numSquaresPerSide.get(i));
        }

        // make it n x n for those with the same dimension
        if (this.numSquaresPerSide.size() == 1) {
            this.numSquaresPerSide.add(numSquaresPerSide.get(0));
        }
    }

    public void initBoard() {
        board = new String[numSquaresPerSide.get(0)][numSquaresPerSide.get(1)];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "-";
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public void printBoard() {
        int i, j, k;
        int max = numSquaresPerSide.get(0) + 1;

        for (i = 0; i < max; i++) {
            for (j = 0; j < numSquaresPerSide.get(1); j++) {
                System.out.print("+---");
            }
            System.out.println("+");

            if (i < numSquaresPerSide.get(0)) {
                for (k = 0; k < numSquaresPerSide.get(1); k++) {
                    String piece = board[i][k];
                    if (piece.equals("-")) {
                        piece = " ";
                    }
                    System.out.print("| " + piece + " ");
                }
                System.out.println("|");
            }
        }
    }

    public List<Integer> getDimensionsOfBoard() {
        return numSquaresPerSide;
    }

    public void updateBoard(int row, int column, String gamePiece) {
        board[row][column] = gamePiece;
    }

    public List<String> getPossibleBoardGamePieces() {
        return boardGamePieces.getBoardGamePieces();
    }

    public int getNumberOfPossibleBoardGamePieces() {
        return boardGamePieces.getNumberOfPossibleBoardGamePieces();
    }

    public String checkValidBoardGamePiece(String gamePiece) {
        // Invalid Board Game Piece
        if(!boardGamePieces.checkIfGamePieceExists(gamePiece)) {
            return "IBGP";
        } else {
            return "BGP OK";
        }
    }
}
