package trivia.manager;

import java.util.LinkedList;

import static trivia.Logger.log;

public class QuestionManager {
    final LinkedList popQuestions = new LinkedList();
    final LinkedList scienceQuestions = new LinkedList();
    final LinkedList sportsQuestions = new LinkedList();
    final LinkedList rockQuestions = new LinkedList();

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

    public void askQuestion(String currentCategory) {
        if (currentCategory.equalsIgnoreCase("Pop"))
            log(removePop());
        if (currentCategory.equalsIgnoreCase("Science"))
            log(removeScience());
        if (currentCategory.equalsIgnoreCase("Sports"))
            log(removeSports());
        if (currentCategory.equalsIgnoreCase("Rock"))
            log(removeRock());
    }
}
