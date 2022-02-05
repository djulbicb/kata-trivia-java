package trivia;

import trivia.manager.MoveManager;
import trivia.manager.PlacesManager;
import trivia.model.Player;
import trivia.manager.PlayerManager;
import trivia.manager.QuestionManager;

import java.util.Arrays;

import static trivia.Logger.log;

// REFACTOR ME
public class GameBetter implements IGame {
   MoveManager mManager = new MoveManager();
   PlayerManager pManager = new PlayerManager(mManager);
   QuestionManager qManager = new QuestionManager();
   PlacesManager placesManager = new PlacesManager(mManager);
   

   boolean isGettingOutOfPenaltyBox;

   public boolean add(String playerName) {
      mManager.increaseMaxCurrentMoveLimit();

      Player newPlayer = Player.builder().name(playerName).build();
      pManager.addPlayer(newPlayer);

      log(playerName + " was added");
      log("They are player number " + pManager.howManyPlayers());
      
      return true;
   }

   public void roll(int roll) {
      log(pManager.getCurrent() + " is the current player");
      log("They have rolled a " + roll);

      int currentMove = mManager.getCurrentMove();
      Player current = pManager.getCurrent();
      
      if (current.isInPenaltyBox()) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            log(pManager.getCurrent() + " is getting out of the penalty box");
           placesManager.addRollAndGet(roll);

            log(pManager.getCurrent() + "'s new location is " + placesManager.getCurrent());
            log("The category is " + placesManager.getCategory());
            qManager.askQuestion(placesManager.getCategory());
         } else {
            log(pManager.getCurrent() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         placesManager.addRollAndGet(roll);

         log(pManager.getCurrent() + "'s new location is " +  placesManager.getCurrent());
         log("The category is " + placesManager.getCategory());
         qManager.askQuestion(placesManager.getCategory());
      }

   }

   public boolean wasCorrectlyAnswered() {
      int currentMove = mManager.getCurrentMove();
      Player player = pManager.getCurrent();

      if (player.isInPenaltyBox()) {
         if (isGettingOutOfPenaltyBox) {
            log("Answer was correct!!!!");
            player.purseIncrement();
            log(pManager.getCurrent() + " now has " + player.getPurse() + " Gold Coins.");

            boolean winner = didPlayerWin();

            mManager.next();
            return winner;
         } else {
            mManager.next();
            return true;
         }


      } else {
         log("Answer was corrent!!!!");
         player.purseIncrement();
         log(pManager.getCurrent() + " now has " + player.getPurse() + " Gold Coins.");

         boolean winner = didPlayerWin();

         mManager.next();
         return winner;
      }
   }

   public boolean wrongAnswer() {
      log("Question was incorrectly answered");
      log(pManager.getCurrent() + " was sent to the penalty box");

      int currentMove = mManager.getCurrentMove();
      Player current = pManager.getCurrent();
      current.setInPenaltyBox(true);

      mManager.next();
      return true;
   }


   private boolean didPlayerWin() {
      Player current = pManager.getCurrent();
      return !(current.getPurse() == 6);
   }
}
