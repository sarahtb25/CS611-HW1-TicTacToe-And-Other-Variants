import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// This class helps to get the user inputs, and handles the turn-taking for each game
public class Helper {
    Players players = new Players();
    Scanner scan = new Scanner(System.in);
    String[] games = {"Tic Tac Toe", "Order And Chaos"};

    public Helper() {

    }

    // Checks if a user input is an integer
    // Obtained from https://www.freecodecamp.org/news/java-string-to-int-how-to-convert-a-string-to-an-integer/
    public static boolean isNumber(String str) {
        try {
            int num = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // Gets which game the user wants to play, and subtracts 1 from the value to get the index
    public int getGameIndex() {
        String option = scan.next().trim();
        int gameIndex, gameNumber = 0;
        boolean optionIsNumber = isNumber(option);

        while (!optionIsNumber || (Integer.parseInt(option) < 1 || Integer.parseInt(option) > games.length)) {
            System.out.println("Please enter a number between 1 and " + games.length + "!");
            option = scan.next().trim();
            optionIsNumber = isNumber(option);
        }

        gameNumber = Integer.parseInt(option);
        gameIndex = gameNumber - 1;

        return gameIndex;
    }

    // Gets the user input for the dimensions of the board, if needed
    public List<Integer> getDimensionsOfBoard(int gameIndex) {
        int numDimensionsNeeded = 0;
        int minDimension = 0;
        int maxDimension = 0;
        List<Integer> dimensions = new ArrayList<>();

        if (gameIndex == 0) {
            TicTacToe T3 = new TicTacToe();
            numDimensionsNeeded = T3.getNumberOfDimension();
            minDimension = T3.getMinMaxDimensions().get(0);
            maxDimension = T3.getMinMaxDimensions().get(1);
        } else if (gameIndex == 1) {
            OrderAndChaos OC = new OrderAndChaos();
            numDimensionsNeeded = OC.getNumberOfDimension();
        }

        if (numDimensionsNeeded > 0) {
            for (int i = 0; i < numDimensionsNeeded; i++) {
                System.out.println("Please enter the number of squares on each side. Value must be between " + minDimension + " and " + maxDimension + ".");
                String dim = scan.next().trim();

                boolean dimIsNumber = isNumber(dim);

                while (!dimIsNumber || (Integer.parseInt(dim) < minDimension || Integer.parseInt(dim) > maxDimension)) {
                    System.out.println("Please enter the number of squares on each side. Value must be between " + minDimension + " and " + maxDimension + ".");
                    dim = scan.next().trim();
                    dimIsNumber = isNumber(dim);
                }

                int dimension = Integer.parseInt(dim);
                dimensions.add(dimension);
            }
        }

        return dimensions;
    }

    // Gets the number of teams based on the game selected, which depends on the number of
    // type of players e.g. X and O, Order and Chaos, etc.
    public int getNumberOfTeams(int gameIndex) {
        int numTeams = 0;

        if (gameIndex == 0) {
            TicTacToe T3 = new TicTacToe();
            numTeams = T3.getNumberOfTeams();
        } else if (gameIndex == 1) {
            OrderAndChaos OC = new OrderAndChaos();
            numTeams = OC.getNumberOfTeams();
        }

        return numTeams;
    }

    // Gets user input for the number of players in a team.
    // All teams will have the same number of people
    public int getNumberOfPlayersPerTeam(int gameIndex) {
        int minNum = 0;
        int maxNum = 0;
        int numPlayersPerTeam = 0;

        if (gameIndex == 0) {
            TicTacToe T3 = new TicTacToe();
            minNum = T3.getMinMaxNumPeoplePerTeam().get(0);
            maxNum = T3.getMinMaxNumPeoplePerTeam().get(1);
        } else if (gameIndex == 1) {
            OrderAndChaos OC = new OrderAndChaos();
            minNum = OC.getMinMaxNumPeoplePerTeam().get(0);
            maxNum = OC.getMinMaxNumPeoplePerTeam().get(1);
        }

        System.out.println("Please enter the number of players per team. Minimum " + minNum +  " player and maximum " + maxNum + " players.");
        String numPlayers = scan.next().trim();

        boolean numPlayersIsNumber = isNumber(numPlayers);

        while (!numPlayersIsNumber || (Integer.parseInt(numPlayers) < minNum || Integer.parseInt(numPlayers) > maxNum)) {
            System.out.println("Please enter a number from " + minNum + " to " + maxNum + "!");
            numPlayers = scan.next().trim();
            numPlayersIsNumber = isNumber(numPlayers);
        }

        numPlayersPerTeam = Integer.parseInt(numPlayers);

        return numPlayersPerTeam;
    }

    // Gets the name of players for each team
    public void getPlayerDetails(int gameIndex, int numTeams, int numPeoplePerTeam) {
        List<String> idAndTeams = new ArrayList<>();

        if (gameIndex == 0) {
            TicTacToe T3 = new TicTacToe();
            idAndTeams = T3.getPossibleIdsAndTeams();
        } else if (gameIndex == 1) {
            OrderAndChaos OC = new OrderAndChaos();
            idAndTeams = OC.getPossibleIdsAndTeams();
        }

        for (int i = 0; i < numTeams; i++) {
            System.out.println("Please enter the name of player(s) who is/are playing " + idAndTeams.get(i));
            for (int j = 0; j < numPeoplePerTeam; j++) {
                String name = scan.next().trim();
                List<String> playerNameAndId = Arrays.asList(name, idAndTeams.get(i));
                String response = players.setPlayerDetails(playerNameAndId);

                while(response.equals("UE")) {
                    System.out.println(name + " already exists! Please enter a different name");
                    name = scan.next().trim();
                    playerNameAndId = Arrays.asList(name, idAndTeams.get(i));
                    response = players.setPlayerDetails(playerNameAndId);
                }
            }
        }
    }

    // Handles the logic for alternating players and getting user inputs for the respective games
    public void playGame(int gameIndex, List<Integer> numSquaresPerSide, int numTeams, int numPlayersPerTeam) {
        List<String> playerNames = players.getAllPlayerNames();
        List<String> gamePiecesAvailable;
        List<String> teams;

        String userResponse = "y";
        int playerIndex = 0, playerTurn = 0;
        boolean roundFinished = false;

        while(userResponse.equals("y")) {
            List<String> playersInvolved = new ArrayList<>();
            playerTurn = 0;
            if (playerIndex >= numPlayersPerTeam) {
                playerIndex = 0;
            }

            // Get the players involved (1 from each team/ID e.g. X and O, Order and Chaos)
            for (int i = 0; i < numTeams; i++) {
                playersInvolved.add(playerNames.get(playerIndex + i * numPlayersPerTeam));
            }

            if (gameIndex == 0) {
                TicTacToe T3 = new TicTacToe(numSquaresPerSide, playersInvolved, players);
                gamePiecesAvailable = T3.getBoardGamePieces();
                teams = T3.getPossibleIdsAndTeams();
                String message = "Please enter who is starting first: ";

                for (int i = 0; i < gamePiecesAvailable.size(); i++) {
                    message += gamePiecesAvailable.get(i) + " or ";
                }

                message = message.substring(0, message.length() - 4);
                message += ".";

                System.out.println(message);

                // Get who starts first: X or O
                String startingPlayerId = scan.next().trim();

                while(!teams.contains(startingPlayerId)) {
                    System.out.println(message);
                    startingPlayerId = scan.next().trim();
                }

                for (int j = 0; j < teams.size(); j++) {
                    if (startingPlayerId.equals(teams.get(j))) {
                        playerTurn = j;
                        break;
                    }
                }

                T3.printBoard();

                do {
                    // Get the game piece of the player in this turn
                    String playerGamePiece = players.getPlayerId(playersInvolved.get(playerTurn));
                    System.out.println(playersInvolved.get(playerTurn) + " (" + playerGamePiece + "), please enter the location e.g. 1,1:");
                    String location = scan.next().trim();

                    while(!location.contains(",") || location.split(",").length != 2 || !isNumber(location.split(",")[0]) || !isNumber(location.split(",")[1])) {
                        System.out.println("Please provide the row and column in a comma-separated manner e.g. 1,1:");
                        location = scan.next().trim();
                    }

                    int row = Integer.parseInt(location.split(",")[0]);
                    int column = Integer.parseInt(location.split(",")[1]);

                    String response = T3.setMove(row, column, playerGamePiece);

                    while(!response.equals("OK")) {
                        // Location Out Of Bounds
                        if (response.equals("LOOB")) {
                            System.out.println("Row and column values must be between 1 and " + numSquaresPerSide.get(0) + "! Please try again");
                            location = scan.next().trim();
                        } else if (response.equals("LO")) { // Location Occupied
                            System.out.println("Location is occupied! Please try again.");
                            location = scan.next().trim();
                        }

                        while(!location.contains(",") || location.split(",").length != 2 || !isNumber(location.split(",")[0]) || !isNumber(location.split(",")[1])) {
                            System.out.println("Please provide the row and column in a comma-separated manner e.g. 1,1:");
                            location = scan.next().trim();
                        }

                        row = Integer.parseInt(location.split(",")[0]);
                        column = Integer.parseInt(location.split(",")[1]);

                        response = T3.setMove(row, column, playerGamePiece);
                    }

                    T3.printBoard();
                    roundFinished = T3.checkGameStatus(playersInvolved);

                    // Print the scores of each player if the round is finished
                    if (roundFinished) {
                        System.out.println();
                        for (int i = 0; i < playersInvolved.size(); i++) {
                            players.printIndividualScoreSummary(playersInvolved.get(i));
                        }
                        System.out.println("");
                    }

                    // Get the index of the next player
                    playerTurn++;

                    // Go back to the first player once all the players involved have had their turn
                    if (playerTurn == playersInvolved.size()) {
                        playerTurn = 0;
                    }

                } while(!roundFinished);
            } else if (gameIndex == 1) {
                OrderAndChaos OC = new OrderAndChaos(playersInvolved, players);
                gamePiecesAvailable = OC.getBoardGamePieces();

                OC.printBoard();

                do {
                    String playerId = players.getPlayerId(playersInvolved.get(playerTurn));
                    System.out.println(playersInvolved.get(playerTurn) + " (" + playerId + "), please enter the location e.g. 1,1:");
                    String location = scan.next().trim();

                    while(!location.contains(",") || location.split(",").length != 2 || !isNumber(location.split(",")[0]) || !isNumber(location.split(",")[1])) {
                        System.out.println("Please provide the row and column in a comma-separated manner e.g. 1,1:");
                        location = scan.next().trim();
                    }

                    int row = Integer.parseInt(location.split(",")[0]);
                    int column = Integer.parseInt(location.split(",")[1]);

                    String gamePieces = "";

                    for (int j = 0; j < gamePiecesAvailable.size(); j++) {
                        gamePieces += gamePiecesAvailable.get(j) + " or ";
                    }

                    gamePieces = gamePieces.substring(0, gamePieces.length() - 4);

                    System.out.println("Please enter the game piece (" + gamePieces + "):");
                    String playerGamePiece = scan.next().trim();

                    String response = OC.setMove(row, column, playerGamePiece);

                    while(!response.equals("OK")) {
                        if (response.equals("LOOB")) {
                            System.out.println("Row and column values must be between 1 and 6! Please try again.");
                            location = scan.next().trim();
                        } else if (response.equals("LO")) {
                            System.out.println("Location is occupied! Please try again.");
                            location = scan.next().trim();
                        } else if (response.equals("IBGP")) { // Invalid Board Game Piece
                            System.out.println("Invalid board game piece provided! Please enter " + gamePieces + ".");
                            playerGamePiece = scan.next().trim();
                        }

                        while(!location.contains(",") || location.split(",").length != 2 || !isNumber(location.split(",")[0]) || !isNumber(location.split(",")[1])) {
                            System.out.println("Please provide the row and column in a comma-separated manner e.g. 1,1:");
                            location = scan.next().trim();
                        }

                        row = Integer.parseInt(location.split(",")[0]);
                        column = Integer.parseInt(location.split(",")[1]);

                        response = OC.setMove(row, column, playerGamePiece);
                    }

                    OC.printBoard();
                    roundFinished = OC.checkGameStatus(playersInvolved);

                    if (roundFinished) {
                        System.out.println();
                        for (int i = 0; i < playersInvolved.size(); i++) {
                            players.printIndividualScoreSummary(playersInvolved.get(i));
                        }
                        System.out.println();
                    }

                    playerTurn++;

                    if (playerTurn == playersInvolved.size()) {
                        playerTurn = 0;
                    }

                } while(!roundFinished);
            }


            userResponse = endGame();
            // Get the next index of players in a team if the players are in teams
            playerIndex++;
        }

        printScoreSummary(gameIndex);
        players.clearPlayersAndScores();
        System.exit(0);
    }

    // Checks if the player would like to continue playing the game or not
    public String endGame() {
        System.out.println("Would you like to play another round? Enter Y or y to continue, N or n to end.");

        String userResponse = scan.next().toLowerCase().trim();

        while (!userResponse.equals("y") && !userResponse.equals("n")) {
            System.out.println("Enter Y or y to continue, N or n to end.");
            userResponse = scan.next().toLowerCase().trim();
        }

        return userResponse;
    }

    // Prints the summary of scores if the player chooses not to continue playing
    public void printScoreSummary(int gameIndex) {
        System.out.println("\nScore Summary: ");
        List<String> teams = new ArrayList<>();

        if (gameIndex == 0) {
            TicTacToe T3 = new TicTacToe();
            teams = T3.getPossibleIdsAndTeams();
        } else if (gameIndex == 1) {
            OrderAndChaos OC = new OrderAndChaos();
            teams = OC.getPossibleIdsAndTeams();
        }

        for (int i = 0; i < teams.size(); i++) {
            List<Integer> scoresOfATeam = players.getScoresOfATeam(teams.get(i));
            System.out.println("Team " + teams.get(i) + " has " + scoresOfATeam.get(0) + " win(s), " + scoresOfATeam.get(1) + " loss(es) and " + scoresOfATeam.get(2) + " ties.");
            List<String> playersInATeam = players.getPlayerNamesInATeam(teams.get(i));
            for (int j = 0; j < playersInATeam.size(); j++) {
                List<Integer> scores = players.getPlayerScores(playersInATeam.get(j));
                System.out.println("\tPlayer " + playersInATeam.get(j) + " has " + scores.get(0) + " win(s), " + scores.get(1) + " loss(es) and " + scores.get(2) + " ties.");
            }
            System.out.println();
        }
    }
}
