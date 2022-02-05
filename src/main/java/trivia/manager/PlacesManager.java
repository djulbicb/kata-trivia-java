package trivia.manager;

public class PlacesManager {
    
    private final StepManager stepManager;
    // private List<Integer> places = new ArrayList();
    final int[] places = new int[6];

    public PlacesManager(StepManager stepManager) {
        this.stepManager = stepManager;
    }

    public String getCurrentPlaceCategory() {
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

    public void addRoledNumber(int roll) {
        int currentMove = stepManager.getCurrentStep();

        places[currentMove] = places[currentMove] + roll;
        if (places[currentMove] > 11) places[currentMove] = places[currentMove] - 12;

    }

    public int getCurrent() {
        int currentMove = stepManager.getCurrentStep();
        return places[currentMove];
    }
}
