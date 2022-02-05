package trivia.manager;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class PlacesManager {
    
    private final MoveManager moveManager;
    private List<Integer> places = new ArrayList();
    private int currentIndex;

    public PlacesManager(MoveManager moveManager) {
        this.moveManager = moveManager;
    }

    public void addEmptyPlace() {
        places.add(0);
    }

    public String getCategory() {
        int currentMove = moveManager.getCurrentMove();
        if (currentMove == 0) return "Pop";
        if (currentMove == 4) return "Pop";
        if (currentMove == 8) return "Pop";
        if (currentMove == 1) return "Science";
        if (currentMove == 5) return "Science";
        if (currentMove == 9) return "Science";
        if (currentMove == 2) return "Sports";
        if (currentMove == 6) return "Sports";
        if (currentMove == 10) return "Sports";
        return "Rock";
    }

    public void addRollAndGet(int roll) {
        int currentMove = moveManager.getCurrentMove();
//
//        places[currentMove] = places[currentMove] + roll;
//        if (places[currentMove] > 11) places[currentMove] = places[currentMove] - 12;

    }


}
