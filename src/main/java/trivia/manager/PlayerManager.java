package trivia.manager;

import trivia.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    List<Player> players = new ArrayList<>();
    StepManager stepManager;

    public PlayerManager(StepManager stepManager) {
        this.stepManager = stepManager;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public Player getCurrentPlayer() {
        return players.get(stepManager.getCurrentStep());
    }

    public void addPlayer(String playerName) {
        Player newPlayer = Player.builder().name(playerName).build();
        players.add(newPlayer);
    }
}
