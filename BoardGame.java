import java.util.List;

// This class models a board game
public abstract class BoardGame extends Game {
    protected Board board;

    public BoardGame() {
        super();
    }

    public BoardGame(List<Integer> numSquaresPerSide, List<String> playerNames, Players players, List<String> gamePiecesAvailable) {
        super(playerNames, players);
        board = new Board(numSquaresPerSide, gamePiecesAvailable);
    }

    public List<Integer> getBoardDimensions() {
        return board.getDimensionsOfBoard();
    }

    public String[][] getBoard() {
        return board.getBoard();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void setBoard(int row, int column, String gamePiece) {
        board.updateBoard(row, column, gamePiece);
    }

    public String checkValidGamePiece(String gamePiece) {
        return board.checkValidBoardGamePiece(gamePiece);
    }

    public int getNumberOfPossibleBoardGamePieces() {
        return board.getNumberOfPossibleBoardGamePieces();
    }

    public List<String> getBoardGamePieces() {
        return board.getPossibleBoardGamePieces();
    }

    // These abstract methods force each specific game class to create their own
    // methods to allow players to make a move (setMove),
    // check if the move a player made is a valid move (checkValidMove), and
    // check for winners (checkGameStatus)
    abstract protected String setMove(int row, int column, String gamePiece);
    abstract protected String checkValidMove(int row, int column, String gamePiece);
    abstract protected boolean checkGameStatus(List<String> playerNames);
}
