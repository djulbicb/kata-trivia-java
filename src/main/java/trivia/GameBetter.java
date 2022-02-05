package trivia;

import trivia.manager.StepManager;
import trivia.manager.PlacesManager;
import trivia.model.Player;
import trivia.manager.PlayerManager;
import trivia.manager.QuestionManager;

import static trivia.Logger.log;

// REFACTOR ME
public class GameBetter implements IGame {
   StepManager moveMng = new StepManager();
   QuestionManager questionMng = new QuestionManager();

   PlayerManager playerMng = new PlayerManager(moveMng);
   PlacesManager placesMng = new PlacesManager(moveMng);

   private boolean canPlayerGetOutOfPenaltyBox;

   public boolean add(String playerName) {
      moveMng.incrementMaxStepCount();
      playerMng.addPlayer(playerName);

      log(playerName + " was added");
      log("They are player number " + playerMng.howManyPlayers());

      return true;
   }

   public void roll(int roll) {
      log(playerMng.getCurrentPlayer() + " is the current player");
      log("They have rolled a " + roll);

      Player player = playerMng.getCurrentPlayer();
      boolean isPlayerInPenalty = player.isInPenaltyBox();

      if (isPlayerInPenalty && !isRolledNumberEven(roll)) {
         canPlayerGetOutOfPenaltyBox = true;
         log(player + " is getting out of the penalty box");

         placesMng.addRoledNumber(roll);

         logCurrentQuestion(player);
      }
      else if(isPlayerInPenalty) {
         log(player + " is not getting out of the penalty box");
         canPlayerGetOutOfPenaltyBox = false;
      }
      else {
         placesMng.addRoledNumber(roll);
         logCurrentQuestion(playerMng.getCurrentPlayer());
      }
   }

   private void logCurrentQuestion(Player currentPlayer) {
      log(currentPlayer + "'s new location is " + placesMng.getCurrent());
      log("The category is " + placesMng.getCurrentPlaceCategory());

      questionMng.askQuestion(placesMng.getCurrentPlaceCategory());
   }

   private boolean isRolledNumberEven(int roll) {
      return roll % 2 == 0;
   }

   public boolean wasCorrectlyAnswered() {
      Player player = playerMng.getCurrentPlayer();

      if (player.isInPenaltyBox() && canPlayerGetOutOfPenaltyBox) {
         log("Answer was correct!!!!");

         player.purseIncrement();
         log(playerMng.getCurrentPlayer() + " now has " + player.getPurse() + " Gold Coins.");

         return checkIfGameRunningAndMoveToNextStep();
      }
      else if (player.isInPenaltyBox()) {
         moveMng.nextStep();
         return true;
      }
      else {
         log("Answer was corrent!!!!");

         player.purseIncrement();
         log(playerMng.getCurrentPlayer() + " now has " + player.getPurse() + " Gold Coins.");

         return checkIfGameRunningAndMoveToNextStep();
      }
   }

   private boolean checkIfGameRunningAndMoveToNextStep() {
      boolean isGameRunning = isGameOver();
      moveMng.nextStep();
      return isGameRunning;
   }

   public boolean wrongAnswer() {
      log("Question was incorrectly answered");
      log(playerMng.getCurrentPlayer() + " was sent to the penalty box");

      Player current = playerMng.getCurrentPlayer();
      current.setInPenaltyBox(true);

      moveMng.nextStep();
      return true;
   }

   public boolean isGameOver() {
      Player current = playerMng.getCurrentPlayer();
      return !(current.getPurse() >= 6);
   }
}
