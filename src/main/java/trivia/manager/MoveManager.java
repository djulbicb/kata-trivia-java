package trivia.manager;

import lombok.Getter;

@Getter
public class MoveManager {
    int currentMove = 0;
    int totalMoveCount = 0;

    public void next() {
        currentMove++;

        if (currentMove == totalMoveCount) {
            currentMove = 0;
        }
    }

    public void increaseMaxMoveLimit() {
        totalMoveCount++;
    }
}
