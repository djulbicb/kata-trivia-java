package trivia.model;

import java.util.LinkedList;

public class QuestionManager {
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    public QuestionManager() {
        for (int i = 0; i < 50; i++) {
            addPop("Pop Question " + i);
            addScience(("Science Question " + i));
            addSports(("Sports Question " + i));
            addRock("Rock Question " + i);
        }
    }

    public void addPop(String s) {
        popQuestions.add(s);
    }

    public void addScience(String s) {
        scienceQuestions.add(s);
    }

    public void addSports(String s) {
        sportsQuestions.add(s);
    }

    public void addRock(String s) {
        rockQuestions.add(s);
    }

    public Object removeRock() {
        return rockQuestions.removeFirst();
    }

    public Object removeSports() {
        return sportsQuestions.removeFirst();
    }

    public Object removeScience() {
        return scienceQuestions.removeFirst();
    }

    public Object removePop() {
        return popQuestions.removeFirst();
    }
}
