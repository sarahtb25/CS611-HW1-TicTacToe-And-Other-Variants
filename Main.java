import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] games = {"Tic Tac Toe", "Order And Chaos"};
        Helper helper = new Helper();

        String welcomeMessage = "Welcome to CS611 Arcade!\n" +
                "We only offer " + games.length + " games at this moment:\n";

        for (int i = 0; i < games.length; i++) {
            welcomeMessage += i+1 + ". " + games[i] + "\n";
        }

        welcomeMessage += "Please enter ";

        for (int i = 0; i < games.length; i++) {
            welcomeMessage += i + 1 + " or ";
        }

        welcomeMessage = welcomeMessage.substring(0, welcomeMessage.length() - 3);
        welcomeMessage += "to select a game.";
        System.out.println(welcomeMessage);

        int gameIndex = helper.getGameIndex();
        System.out.println("You have selected " + games[gameIndex] + ".");

        List<Integer> dimensions = helper.getDimensionsOfBoard(gameIndex);

        int numTeams = helper.getNumberOfTeams(gameIndex);

        int numPlayersPerTeam = helper.getNumberOfPlayersPerTeam(gameIndex);

        helper.getPlayerDetails(gameIndex, numTeams, numPlayersPerTeam);
        helper.playGame(gameIndex, dimensions, numTeams, numPlayersPerTeam);
    }
}
