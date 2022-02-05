package trivia.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    List<Player> players = new ArrayList<>();
    int currentPlayer = 0;

    public int howManyPlayers() {
        return players.size();
    }

    public Player getCurrent() {
        return players.get(currentPlayer);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void goNextPlayer() {
        currentPlayer++;
    }
}
