import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
  private int turns = 0;
  private final char[][] board = new char[3][3];
  private boolean AIMode;
  private Scanner input;
  private boolean playing;

  private void createBoard() {
    for (char[] chars : board) {
      Arrays.fill(chars, '.');
    }
  }

  public boolean isPlaying() {
    return this.playing;
  }

  public TicTacToe(boolean AIMode) {
    if (AIMode) {
      System.out.println("[!] Playing in AI mode.");
    }
    this.AIMode = AIMode;
    this.input = new Scanner(System.in);
    this.playing = true;
    this.createBoard(); // When creating the object, initialize the board with default values.
  }

  public int getRow() {
    System.out.print("Row: ");
    int row = input.nextInt();
    input.nextLine();
    return row;
  }

  public int getCol() {
    System.out.print("Col: ");
    int col = input.nextInt();
    input.nextLine();
    return col;
  }

  public void printBoard() {
    int row;
    int col;
    System.out.print(this.getCurrentPlayer() + " ");
    for (int i = 0; i < this.board.length; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
    for (row = 0; row < this.board.length; row++) {
      System.out.print(row + " ");
      for (col = 0; col < this.board[row].length; col++) {
        System.out.print(this.board[row][col] + " ");
      }
      System.out.println();
    }
  }

  public char getCurrentPlayer() {
    return (
      this.turns % 2 == 0 ? 'X' : 'O'
    );
  }

  public boolean isValidPosition(int row, int col) {
    if (row >= board.length || col >= board[row].length) {
      return false;
    }
    return this.board[row][col] == '.';
  }

  public void play(int row, int col) {
    this.board[row][col] = this.getCurrentPlayer();

    if (this.hasWinner()) {
      handleGameOver(true);
    } else if (!this.hasWinner() && this.getNumOfPositions() == 0) {
      handleGameOver(false);
    }
    else {
      this.incrementTurn();
    }
  }

  public boolean AIModeActive() {
    return this.AIMode;
  }

  public void runAITurn() {
    int emptySpots = this.getNumOfPositions();
    int[][] spotCords = new int[emptySpots][2];
    int arrIdx = 0;

    // Get all the current empty positions.
    for (int row = 0; row < this.board.length; row++) {
      for (int col = 0; col < this.board[row].length; col++) {
        if (this.isValidPosition(row, col)) {
          spotCords[arrIdx] = new int[]{row, col};
          arrIdx++;
        }
      }
    }

    // Choose a place.
    int random = (int) (Math.random() * spotCords.length);
    this.play(
      spotCords[random][0], // The chosen row
      spotCords[random][1] // The chosen col
    );
  }

  private boolean horizontalWin(char player) {
    int rowCount = 0;
    for (char[] row : this.board) {
      for (char position : row) {
        if (position == player) {
          rowCount++;
        }
      }
      if (rowCount == 3) {
        return true;
      }
      rowCount = 0;
    }
    return false;
  }

  private boolean verticalWin(char player) {
    int colCount = 0;
    for (int col = 0; col < this.board[0].length; col++) {
      for (int row = 0; row < this.board.length; row++) {
        if (this.board[row][col] == player) {
          colCount++;
        }
      }
      if (colCount == 3) {
        return true;
      }
      colCount = 0;
    }
    return false;
  }

  private boolean diagonalWin(char player) {
    char[][] board = this.board;
    return (
      (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
      (board[2][0] == player && board[1][1] == player && board[0][2] == player)
    );
  }

  private boolean hasWinner() {
    char player = this.getCurrentPlayer();
    return this.horizontalWin(player) || this.verticalWin(player) || this.diagonalWin(player);
  }

  private void restart() {
    this.createBoard();
    this.turns = 0;
  }

  private void incrementTurn() {
    this.turns++;
  }

  public int getTurns() {
    return this.turns;
  }

  private int getNumOfPositions() {
    int total = 0;
    for (int row = 0; row < this.board.length; row++) {
      for (int col = 0; col < this.board[row].length; col++) {
        if (this.isValidPosition(row, col)) {
          total++;
        }
      }
    }

    return total;
  }

  private void handleGameOver(boolean isWin) {
    System.out.println("----- Final Board -----");
    this.printBoard();
    System.out.println();
    if (isWin) {
      System.out.print("Congratulations! Player " + this.getCurrentPlayer() + " wins! Play Again? (y/n): ");
    } else {
      System.out.print("GAME OVER! It's a tie! Play Again? (y/n): ");
    }
    char response = input.nextLine().charAt(0);
    if (response == 'n') {
      System.out.println("Goodbye!");
      this.playing = false;
      return;
    }
    this.restart();
  }


  @Override
  public String toString() {
    return "A Tic-Tac-Toe game that is currently playing with " + this.turns + " turns. AI MODE: " + (AIMode ? "ACTIVE" : "NOT ACTIVE");
  }
}
