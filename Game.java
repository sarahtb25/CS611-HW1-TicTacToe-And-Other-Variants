import java.util.List;

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
