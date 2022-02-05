package trivia;

import trivia.manager.MoveManager;
import trivia.model.Player;
import trivia.manager.PlayerManager;
import trivia.manager.QuestionManager;

import static trivia.Logger.log;

// REFACTOR ME
public class GameBetter implements IGame {
   MoveManager mManager = new MoveManager();
   PlayerManager pManager = new PlayerManager(mManager);
   QuestionManager qManager = new QuestionManager();
   
   int[] places = new int[6];

   boolean isGettingOutOfPenaltyBox;

   public boolean add(String playerName) {
      mManager.increaseMoveLimit();

      pManager.addPlayer(Player.builder().name(playerName).build());

      int playerCount = pManager.howManyPlayers();

      places[playerCount] = 0;

      log(playerName + " was added");
      log("They are player number " + playerCount);
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
            places[currentMove] = places[currentMove] + roll;
            if (places[currentMove] > 11) places[currentMove] = places[currentMove] - 12;

            log(pManager.getCurrent() + "'s new location is " + places[currentMove]);
            log("The category is " + currentCategory());
            qManager.askQuestion(currentCategory());
         } else {
            log(pManager.getCurrent() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         places[currentMove] = places[currentMove] + roll;
         if (places[currentMove] > 11) places[currentMove] = places[currentMove] - 12;

         log(pManager.getCurrent() + "'s new location is " + places[currentMove]);
         log("The category is " + currentCategory());
         qManager.askQuestion(currentCategory());
      }

   }

   private String currentCategory() {
      int currentMove = mManager.getCurrentMove();
      if (places[currentMove] == 0) return "Pop";
      if (places[currentMove] == 4) return "Pop";
      if (places[currentMove] == 8) return "Pop";
      if (places[currentMove] == 1) return "Science";
      if (places[currentMove] == 5) return "Science";
      if (places[currentMove] == 9) return "Science";
      if (places[currentMove] == 2) return "Sports";
      if (places[currentMove] == 6) return "Sports";
      if (places[currentMove] == 10) return "Sports";
      return "Rock";
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
