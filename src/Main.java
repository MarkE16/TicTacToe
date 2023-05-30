import java.util.Scanner;

public class Main {
  static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    int diff = 1;

    printHeader("Welcome to Tic-Tac-Toe!");
    System.out.println(
      "1. Against A.I.\n" +
      "2. 2 Player"
    );
    int mode = getInput("Select Mode: ");
    if (mode == 1) {
      System.out.println(
        "NOTE: Hard is not available at the moment. Basically, no matter what you input, you will be directed to Easy mode.\n" +
        "1. Easy\n" +
        "2. Hard"
      );
      diff = getInput("Select Difficulty: ");

    } else if (mode != 2) {
      System.out.println("You must enter a valid mode.");
      return;
    }

    startGame(mode == 1, diff);
  }

  public static void startGame(boolean playAI, int difficulty) {
    TicTacToe game = new TicTacToe(playAI, difficulty);
    while (game.isPlaying()) {
      /*
        `game.getTurns() % 2 != 0` refers to the fact that the AI should not play first and that if the player doesn't enter a valid position
        then the AI will wait.
      */
      if (playAI && game.getTurns() % 2 != 0 && game.isPlaying()) {
        game.runAITurn();
      }
      game.printBoard();

      System.out.println("Player " + game.getCurrentPlayer() + ", your turn:");
      int row = getInput("Row: ");
      int col = getInput("Col: ");

      if (game.isValidPosition(row, col)) {
        game.play(row, col);
      } else {
        System.out.println("[!] Position invalid. Select a different one.");
      }
    }
  }

  public static void printHeader(String txt) {
    int mid = txt.length() / 2;
    for (int i = 0; i < mid; i++) {
      System.out.print("-");
    }

    System.out.print(" " + txt + " ");

    for (int i = mid; i < txt.length(); i++) {
      System.out.print("-");
    }
    System.out.println();
  }

  public static int getInput(String message) {
    int result;
    while (true) {
      System.out.print(message);
      try {
        result = input.nextInt();
        break;
      } catch (Exception e) {
        System.out.println("Invalid input.");
      } finally {
        input.nextLine();
      }
    }
    return result;
  }
}