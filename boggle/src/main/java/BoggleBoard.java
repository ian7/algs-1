public class BoggleBoard {
  private final int m;
  private final int n;
  private char[][] board;

  // Initializes a random 4-by-4 Boggle board.
  // (by rolling the Hasbro dice)
  public BoggleBoard() {
    this(4, 4);
  }

  // Initializes a random m-by-n Boggle board.
  // (using the frequency of letters in the English language)
  public BoggleBoard(int m, int n) {
    this.m = m;
    this.n = n;
    this.board = new char[n][m];
  }

  // Initializes a Boggle board from the specified filename.
  public BoggleBoard(String filename) {
    this.n = 4;
    this.m = 4;
  }

  // Initializes a Boggle board from the 2d char array.
  // (with 'Q' representing the two-letter sequence "Qu")
  public BoggleBoard(char[][] a) {
    this.n = a.length;
    this.m = a[0].length;
  }

  // Returns the number of rows.
  public int rows() {
    return m;
  }

  // Returns the number of columns.
  public int cols() {
    return n;
  }

  // Returns the letter in row i and column j.
  // (with 'Q' representing the two-letter sequence "Qu")
  public char getLetter(int i, int j) {
    return this.board[i][j];
  }

  // Returns a string representation of the board.
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for( int i=0; i<this.n;i++){
      for( int j=0; j<this.m;j++){
        sb.append(this.getLetter(i,j)).append(" ");
      }
    }
    return sb.toString();
  }
}
