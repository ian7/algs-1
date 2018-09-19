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
    return this.size;
  } // board dimension n

  public int hamming() {
    int hammingMetric = 0;

    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if ( this.blocks[i][j] != 0 && !isExpectedValue(i, j)) {
          hammingMetric++;
        }
      }
    }
    return hammingMetric + priorMovesCount;
  }                   // number of blocks out of place

  public int manhattan() {
    int manhattanMetric = 0;
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        manhattanMetric += manhattanDistance(i, j);
      }
    }
    return manhattanMetric;
  }                 // sum of Manhattan distances between blocks and goal

  private int manhattanDistance(int i, int j) {
    final int currentValue = this.blocks[i][j];

    if (currentValue == 0) {
      return 0;
    }

    final int shouldI = (currentValue - 1) / this.size;
    final int shouldJ = (currentValue - 1) % this.size;
    final int deltaI = Math.abs(shouldI - i);
    final int deltaJ = Math.abs(shouldJ - j);
    return (deltaI + deltaJ);
  }

  public boolean isGoal() {
    return (hamming() == 0);
  }                // is this board the goal board?

  private boolean isExpectedValue(int i, int j) {
    final int blockIndex = i * this.size + j;
    final int expectedValue;
    if (blockIndex < this.size * this.size - 1) {
      expectedValue = blockIndex + 1;
    } else {
      expectedValue = 0;
    }

    return (this.blocks[i][j] == expectedValue);
  }

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
