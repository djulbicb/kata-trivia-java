package trivia.manager;

import lombok.Getter;

@Getter
public class StepManager {
    int currentStep = 0;
    int maximumStepCount = 0;

    public void nextMove() {
        currentStep++;

        if (currentStep == maximumStepCount) {
            currentStep = 0;
        }
    }

    public void incrementMaxStepCount() {
        maximumStepCount++;
    }
}
