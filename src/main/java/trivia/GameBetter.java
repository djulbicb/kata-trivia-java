package trivia;

import trivia.manager.MoveManager;
import trivia.manager.PlacesManager;
import trivia.model.Player;
import trivia.manager.PlayerManager;
import trivia.manager.QuestionManager;

import static trivia.Logger.log;

// REFACTOR ME
public class GameBetter implements IGame {
   MoveManager mManager = new MoveManager();
   PlayerManager pManager = new PlayerManager(mManager);
   QuestionManager qManager = new QuestionManager();
   PlacesManager placesManager = new PlacesManager(mManager);

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
            pManager.canPlayerEscapeFromJail(true);

            log(pManager.getCurrent() + " is getting out of the penalty box");
           placesManager.addRoledNumber(roll);

            log(pManager.getCurrent() + "'s new location is " + placesManager.getCurrent());
            log("The category is " + placesManager.getCurrentPlaceCategory());
            qManager.askQuestion(placesManager.getCurrentPlaceCategory());
         } else {
            log(pManager.getCurrent() + " is not getting out of the penalty box");
            pManager.canPlayerEscapeFromJail(false);
         }

      } else {
         placesManager.addRoledNumber(roll);

         log(pManager.getCurrent() + "'s new location is " +  placesManager.getCurrent());
         log("The category is " + placesManager.getCurrentPlaceCategory());
         qManager.askQuestion(placesManager.getCurrentPlaceCategory());
      }

   }

   public boolean wasCorrectlyAnswered() {
      Player player = pManager.getCurrent();

      if (player.isInPenaltyBox()) {
         if (pManager.isGettingOutOfPenaltyBox()) {
            log("Answer was correct!!!!");
            player.purseIncrement();
            log(pManager.getCurrent() + " now has " + player.getPurse() + " Gold Coins.");

            boolean winner = pManager.didCurrentPlayerWin();

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

         boolean winner = pManager.didCurrentPlayerWin();

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
}
