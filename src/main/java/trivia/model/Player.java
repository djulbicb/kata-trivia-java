package trivia.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
    private String name;
    private boolean inPenaltyBox;
    private int purse;

    public String toString() {
        return name;
    }

    public void addToPurse() {
        purse += 1;
    }
}
