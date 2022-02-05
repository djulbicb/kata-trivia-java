package trivia.manager;

import trivia.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    List<Player> players = new ArrayList<>();
    MoveManager moveManager;

    boolean isGettingOutOfPenaltyBox;

    public PlayerManager(MoveManager moveManager) {
        this.moveManager = moveManager;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public Player getCurrent() {
        return players.get(moveManager.getCurrentMove());
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean didCurrentPlayerWin() {
        Player current = getCurrent();
        return !(current.getPurse() == 6);
    }

    public void canPlayerEscapeFromJail(boolean b) {
        isGettingOutOfPenaltyBox = b;
    }

    public boolean isGettingOutOfPenaltyBox() {
        return isGettingOutOfPenaltyBox;
    }
}
