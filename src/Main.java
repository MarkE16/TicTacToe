import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    startGame();
  }

  public static void startGame() {
    TicTacToe game = new TicTacToe();
    Scanner input = new Scanner(System.in);
    boolean playing = true;

    while (playing) {
      game.printBoard();

      System.out.println("Player " + game.getCurrentPlayer() + ", your turn:");
      System.out.print("Row: ");
      int row = input.nextInt();
      System.out.print("Column: ");
      int col = input.nextInt();

      input.nextLine(); // Make inputs work again

      if (game.isValidPosition(row, col)) {
        game.play(row, col);
        if (game.hasWinner()) {
          System.out.print("Congratulations! Player " + game.getCurrentPlayer() + " wins! Play Again? (y/n): ");
          char response = input.nextLine().charAt(0);
          if (response == 'n') {
            System.out.println("Goodbye!");
            playing = false;
            return;
          }
          game.restart();
        } else {
          game.incrementTurn();
        }
      } else {
        System.out.println("[!] Position invalid. Select a different one.");
      }
    }
  }
}