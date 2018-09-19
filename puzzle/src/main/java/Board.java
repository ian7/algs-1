import java.util.ArrayList;
import java.util.List;

public class Board {
  private int[][] blocks;
  private int size;
  private int priorMovesCount;

  public Board(int[][] blocks) {
    // let's do assertions and copy the stuff locally
    this.size = blocks.length;
    this.blocks = new int[this.size][this.size];
    for( int i=0; i<this.size; i++){
      for( int j=0; j<this.size;j++){
        this.blocks[i][j] = blocks[i][j];
      }
    }
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
        if (this.blocks[i][j] != 0 && !isExpectedValue(i, j)) {
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
    return neighbors().iterator().next();
  }                    // a board that is obtained by exchanging any pair of blocks

  public boolean equals(Object y) {
    Board that = (Board) y;
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (that.blocks[i][j] != this.blocks[i][j]) {
          return false;
        }
      }
    }
    return true;
  }        // does this board equal y?

  public Iterable<Board> neighbors() {
    List<Board> neighbors = new ArrayList<>();
    final int emptyIndex = findEmptyIndex();
    final int emptyI = emptyIndex / this.size;
    final int emptyJ = emptyIndex % this.size;

    // if it empty ain't top edge - swap with top
    if (emptyI > 0) {
      Board that = new Board(this.blocks);
      that.swapTop(emptyI,emptyJ);
      neighbors.add(that);
    }
    // if empty ain't at the bottom edge - swap it with bottom
    if (emptyI < this.size - 1) {
      Board that = new Board(this.blocks);
      that.swapBottom(emptyI,emptyJ);
      neighbors.add(that);
    }
    // if empty ain't at the left edge - swap it with left
    if (emptyJ > 0) {
      Board that = new Board(this.blocks);
      that.swapLeft(emptyI,emptyJ);
      neighbors.add(that);
    }
    if (emptyJ < this.size - 1) {
      Board that = new Board(this.blocks);
      that.swapRight(emptyI,emptyJ);
      neighbors.add(that);
    }
    return neighbors;
  } // all neighboring boards

  private void swapTop(int i, int j){
    this.blocks[i][j] = this.blocks[i-1][j];
    this.blocks[i-1][j] = 0;
  }

  private void swapBottom(int i, int j){
    this.blocks[i][j] = this.blocks[i+1][j];
    this.blocks[i+1][j] = 0;
  }
  private void swapLeft(int i, int j){
    this.blocks[i][j] = this.blocks[i][j-1];
    this.blocks[i][j-1] = 0;
  }
  private void swapRight(int i, int j){
    this.blocks[i][j] = this.blocks[i][j+1];
    this.blocks[i][j+1] = 0;
  }

  private int findIndex(int element) {
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (this.blocks[i][j] == element) {
          return (i * this.size + j);
        }
      }
    }
    return -1;
  }

  private int findEmptyIndex() {
    return findIndex(0);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        sb.append(this.blocks[i][j]).append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }// string representation of this board (in the output format specified below)

  public static void main(String[] args) {

  }// unit tests (not graded)
}
