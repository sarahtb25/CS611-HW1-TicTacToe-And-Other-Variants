import java.util.*;

// This class models the players
public class Players {
    // Stores players' names and IDs
    private LinkedHashMap<String, String> playerDetails = new LinkedHashMap<>();

    // Stores players' names and scores (number of wins, losses and ties)
    private LinkedHashMap<String, List<Integer>> playerScores = new LinkedHashMap<>();

    public Players() { }

    // 0th index: name, 1st index: ID/Team (ID and team are the same and can be used interchangeably)
    public String setPlayerDetails(List<String> playerNameAndId) {
        String response = "";
        String name = playerNameAndId.get(0);
        String id = playerNameAndId.get(1);

        if (!playerDetails.isEmpty() && playerDetails.containsKey(name)) {
            // Name exists
            response = "UE";
        } else {
            playerDetails.put(name, id);
            initPlayerScores(name);
            response = "OK";
        }

        return response;
    }

    public void initPlayerScores(String name) {
        List<Integer> scores = Arrays.asList(0, 0, 0);

        playerScores.put(name, scores);
    }

    public List<String> getAllPlayerNames() {
        List<String> playerNames = new ArrayList<>();

        Set<String> names = playerDetails.keySet();
        for (String name : names) {
            playerNames.add(name);
        }

        return playerNames;
    }

    public List<Integer> getScoresOfATeam(String team) {
        int win = 0, loss = 0, tie = 0;
        List<Integer> teamScores = new ArrayList<>();

        Set<String> names = playerDetails.keySet();
        for (String name : names) {
            if ((playerDetails.get(name)).equals(team)) {
                List<Integer> scores = playerScores.get(name);
                win += scores.get(0);
                loss += scores.get(1);
                tie += scores.get(2);
            }
        }

        teamScores.add(win);
        teamScores.add(loss);
        teamScores.add(tie);

        return teamScores;
    }

    public List<String> getPlayerNamesInATeam(String team) {
        List<String> playerNames = new ArrayList<>();

        Set<String> names = playerDetails.keySet();
        for (String name : names) {
            if (playerDetails.get(name).equals(team)) {
                playerNames.add(name);
            }
        }

        return playerNames;
    }

    public String getPlayerId(String name) {
        return playerDetails.get(name);
    }

    public List<Integer> getPlayerScores(String name) {
        return playerScores.get(name);
    }

    public void setPlayerScores(String name, String status) {
        List<Integer> scoresBreakdown = playerScores.get(name);

        if (status == "win") {
            int win = scoresBreakdown.get(0) + 1;
            scoresBreakdown.set(0, win);
        } else if (status == "lose") {
            int lose = scoresBreakdown.get(1) + 1;
            scoresBreakdown.set(1, lose);
        } else {
            int tie = scoresBreakdown.get(2) + 1;
            scoresBreakdown.set(2, tie);
        }

        playerScores.put(name, scoresBreakdown);
        printScore(name, status);
    }

    public void printScore(String name, String status) {
        if (status == "win") {
            System.out.println(name + " wins! Congratulations!");
        } else if (status == "lose") {
            System.out.println(name + " loses! Better luck next time!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public void printIndividualScoreSummary(String name) {
        List<Integer> scoresBreakdown = playerScores.get(name);
        System.out.println(name + " has " + scoresBreakdown.get(0) + " win(s), " + scoresBreakdown.get(1) + " loss(es) and " + scoresBreakdown.get(2) + " ties.");
    }

    public void clearPlayersAndScores() {
        playerDetails.clear();
        playerScores.clear();
    }
}
