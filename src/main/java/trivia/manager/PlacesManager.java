package trivia.manager;

public class PlacesManager {
    
    private final StepManager stepManager;
    final int[] places = new int[6];
    final int MAX_STEP_COUNT = 12;

    public PlacesManager(StepManager stepManager) {
        this.stepManager = stepManager;
    }

    public String getCurrentPlaceCategory() {
        switch (getCurrentPlace()) {
            case 0:
            case 4:
            case 8:
                return "Pop";
            case 1:
            case 5:
            case 9:
                return "Science";
            case 2:
            case 6:
            case 10:
                return "Sports";
            default:
                return "Rock";
        }
    }

    public void addRoledNumber(int roll) {
        int currentMove = stepManager.getCurrentStep();

        places[currentMove] = places[currentMove] + roll;

        if (places[currentMove] >= MAX_STEP_COUNT) {
            places[currentMove] = places[currentMove] - MAX_STEP_COUNT;
        }
    }

    public int getCurrentPlace() {
        int currentMove = stepManager.getCurrentStep();
        return places[currentMove];
    }
}
