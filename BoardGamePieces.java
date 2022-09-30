import java.util.List;

// This class contains the details of the possible board game pieces available for a game
public class BoardGamePieces {
    List<String> boardGamePieces;

    public BoardGamePieces(List<String> possibleBoardGamePieces) {
        setGamePieces(possibleBoardGamePieces);
    }

    public void setGamePieces(List<String> possibleGamePieces) {
        boardGamePieces = possibleGamePieces;
    }

    public int getNumberOfPossibleBoardGamePieces() {
        return boardGamePieces.size();
    }

    public List<String> getBoardGamePieces() {
        return boardGamePieces;
    }

    public boolean checkIfGamePieceExists(String piece) {
        return boardGamePieces.contains(piece);
    }
}
