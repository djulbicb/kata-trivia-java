package trivia;

import trivia.manager.MoveManager;
import trivia.manager.PlacesManager;
import trivia.model.Player;
import trivia.manager.PlayerManager;
import trivia.manager.QuestionManager;

import static trivia.Logger.log;

// REFACTOR ME
public class GameBetter implements IGame {
   MoveManager moveMng = new MoveManager();
   PlayerManager playerMng = new PlayerManager(moveMng);
   QuestionManager questionMng = new QuestionManager();
   PlacesManager placesMng = new PlacesManager(moveMng);

   public boolean add(String playerName) {
      moveMng.increaseMaxMoveLimit();

      Player newPlayer = Player.builder().name(playerName).build();
      playerMng.addPlayer(newPlayer);

      log(playerName + " was added");
      log("They are player number " + playerMng.howManyPlayers());
      
      return true;
   }

   public void roll(int roll) {
      log(playerMng.getCurrent() + " is the current player");
      log("They have rolled a " + roll);

      Player currentPlayer = playerMng.getCurrent();
      
      if (currentPlayer.isInPenaltyBox()) {
         if (roll % 2 != 0) {
            playerMng.canPlayerEscapeFromJail(true);

           log(playerMng.getCurrent() + " is getting out of the penalty box");
           placesMng.addRoledNumber(roll);

            log(playerMng.getCurrent() + "'s new location is " + placesMng.getCurrent());
            log("The category is " + placesMng.getCurrentPlaceCategory());
            questionMng.askQuestion(placesMng.getCurrentPlaceCategory());
         } else {
            log(playerMng.getCurrent() + " is not getting out of the penalty box");
            playerMng.canPlayerEscapeFromJail(false);
         }

      } else {
         placesMng.addRoledNumber(roll);

         log(playerMng.getCurrent() + "'s new location is " +  placesMng.getCurrent());
         log("The category is " + placesMng.getCurrentPlaceCategory());
         questionMng.askQuestion(placesMng.getCurrentPlaceCategory());
      }

   }

   public boolean wasCorrectlyAnswered() {
      Player player = playerMng.getCurrent();

      if (player.isInPenaltyBox()) {
         if (playerMng.isGettingOutOfPenaltyBox()) {
            log("Answer was correct!!!!");
            player.purseIncrement();
            log(playerMng.getCurrent() + " now has " + player.getPurse() + " Gold Coins.");

            boolean winner = playerMng.didCurrentPlayerWin();

            moveMng.next();
            return winner;
         } else {
            moveMng.next();
            return true;
         }
      } else {
         log("Answer was corrent!!!!");
         player.purseIncrement();
         log(playerMng.getCurrent() + " now has " + player.getPurse() + " Gold Coins.");

         boolean winner = playerMng.didCurrentPlayerWin();

         moveMng.next();
         return winner;
      }
   }

   public boolean wrongAnswer() {
      log("Question was incorrectly answered");
      log(playerMng.getCurrent() + " was sent to the penalty box");

      Player current = playerMng.getCurrent();
      current.setInPenaltyBox(true);

      moveMng.next();
      return true;
   }
}
