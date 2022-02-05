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
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   boolean isGettingOutOfPenaltyBox;

   public boolean add(String playerName) {
      mManager.increaseMoveLimit();
      pManager.addPlayer(Player.builder().name(playerName).build());

      int playerCount = pManager.howManyPlayers();

      places[playerCount] = 0;
      purses[playerCount] = 0;
      inPenaltyBox[playerCount] = false;

      log(playerName + " was added");
      log("They are player number " + playerCount);
      return true;
   }

   public void roll(int roll) {
      log(pManager.getCurrent() + " is the current player");
      log("They have rolled a " + roll);

      int currentMove = mManager.getCurrentMove();
      if (inPenaltyBox[currentMove]) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            log(pManager.getCurrent() + " is getting out of the penalty box");
            places[currentMove] = places[currentMove] + roll;
            if (places[currentMove] > 11) places[currentMove] = places[currentMove] - 12;

            log(pManager.getCurrent()
                               + "'s new location is "
                               + places[currentMove]);
            log("The category is " + currentCategory());
            askQuestion();
         } else {
            log(pManager.getCurrent() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         places[currentMove] = places[currentMove] + roll;
         if (places[currentMove] > 11) places[currentMove] = places[currentMove] - 12;

         log(pManager.getCurrent()
                            + "'s new location is "
                            + places[currentMove]);
         log("The category is " + currentCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      if (currentCategory() == "Pop")
         log(qManager.removePop());
      if (currentCategory() == "Science")
         log(qManager.removeScience());
      if (currentCategory() == "Sports")
         log(qManager.removeSports());
      if (currentCategory() == "Rock")
         log(qManager.removeRock());
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

      if (inPenaltyBox[currentMove]) {
         if (isGettingOutOfPenaltyBox) {
            log("Answer was correct!!!!");
            purses[currentMove]++;
            log(pManager.getCurrent()
                               + " now has "
                               + purses[currentMove]
                               + " Gold Coins.");

            boolean winner = didPlayerWin();

            mManager.next();
            return winner;
         } else {
            mManager.next();
            return true;
         }


      } else {

         log("Answer was corrent!!!!");
         purses[currentMove]++;
         log(pManager.getCurrent()
                            + " now has "
                            + purses[currentMove]
                            + " Gold Coins.");

         boolean winner = didPlayerWin();

         mManager.next();
         return winner;
      }
   }

   public boolean wrongAnswer() {
      log("Question was incorrectly answered");
      log(pManager.getCurrent() + " was sent to the penalty box");
      int currentMove = mManager.getCurrentMove();
      inPenaltyBox[currentMove] = true;

      mManager.next();
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[mManager.getCurrentMove()] == 6);
   }
}
