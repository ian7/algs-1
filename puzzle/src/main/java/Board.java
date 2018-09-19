public class Board {
  private int[][] blocks;
  private int size;
  private int priorMovesCount;

  public Board(int[][] blocks) {
    // let's do assertions and copy the stuff locally
    this.size = blocks.length;
    this.blocks = blocks.clone();
    boolean checked[] = new boolean[this.size * this.size];

    // checking if array is rectangular
    if (blocks.length != blocks[0].length) {
      throw new IllegalArgumentException();
    }

    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (blocks[i][j] < 0 || blocks[i][j] > this.size * this.size) {
          throw new IllegalArgumentException();
        }
        final int blockValue = blocks[i][j];

        if (checked[blockValue]) {
          throw new IllegalArgumentException();
        } else {
          checked[blockValue] = true;
        }
      }
    }
  }           // construct a board from an n-by-n array of blocks

  // (where blocks[i][j] = block in row i, column j)
  public int dimension() {
    return 0;
  } // board dimension n

  public int hamming() {
    return 0;
  }                   // number of blocks out of place

  public int manhattan() {
    return 0;
  }                 // sum of Manhattan distances between blocks and goal

  public boolean isGoal() {
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        final int blockIndex = i * this.size + j;
        final int expectedValue;
        if (blockIndex < this.size * this.size-1) {
          expectedValue = blockIndex + 1;
        } else {
          expectedValue = 0;
        }
        if (this.blocks[i][j] != expectedValue) {
          return false;
        }
      }
    }
    return true;
  }                // is this board the goal board?

  public Board twin() {
    return null;
  }                    // a board that is obtained by exchanging any pair of blocks

  public boolean equals(Object y) {
    return false;
  }        // does this board equal y?

  public Iterable<Board> neighbors() {
    return null;
  } // all neighboring boards

  public String toString() {
    return null;
  }               // string representation of this board (in the output format specified below)

  public static void main(String[] args) {

  }// unit tests (not graded)
}
