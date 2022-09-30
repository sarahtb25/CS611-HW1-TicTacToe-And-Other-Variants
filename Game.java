import java.util.List;

// This class contains the details of players playing the game
public class Game {
    protected List<String> playerNames;
    protected Players players;

    public Game() {

    }

    public Game(List<String> playerNames, Players players) {
        this.players = players;
        this.playerNames = playerNames;
    }
}
