package trivia.manager;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class PlacesManager {
    
    private final MoveManager moveManager;
    // private List<Integer> places = new ArrayList();
    int[] places = new int[6];

    public PlacesManager(MoveManager moveManager) {
        this.moveManager = moveManager;
    }

//    public void addEmptyPlace() {
//        places.add(0);
//    }

    public String getCategory() {
        if (getCurrent() == 0) return "Pop";
        if (getCurrent() == 4) return "Pop";
        if (getCurrent() == 8) return "Pop";
        if (getCurrent() == 1) return "Science";
        if (getCurrent() == 5) return "Science";
        if (getCurrent() == 9) return "Science";
        if (getCurrent() == 2) return "Sports";
        if (getCurrent() == 6) return "Sports";
        if (getCurrent() == 10) return "Sports";
        return "Rock";
    }

    public void addRollAndGet(int roll) {
        int currentMove = moveManager.getCurrentMove();

        places[currentMove] = places[currentMove] + roll;
        if (places[currentMove] > 11) places[currentMove] = places[currentMove] - 12;

    }


    public int getCurrent() {
        int currentMove = moveManager.getCurrentMove();
        return places[currentMove];
    }
}
