import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    startGame(true);
  }

  public static void startGame(boolean playAI) {
    TicTacToe game = new TicTacToe(playAI);
    Scanner input = new Scanner(System.in);

    while (game.isPlaying()) {
      if (game.AIModeActive() && game.getTurns() != 0 && game.isPlaying()) {
        game.runAITurn();
      }
      game.printBoard();

      System.out.println("Player " + game.getCurrentPlayer() + ", your turn:");
      int row = game.getRow();
      int col = game.getCol();

      if (game.isValidPosition(row, col)) {
        game.play(row, col);
      } else {
        System.out.println("[!] Position invalid. Select a different one.");
      }
    }
  }
}