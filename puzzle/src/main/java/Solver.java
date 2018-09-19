import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solver {
  private final Puzzle originalPuzzle;
  private final Puzzle modifiedPuzzle;

  public Solver(Board initialBoard) {
    if (initialBoard == null) {
      throw new IllegalArgumentException();
    }
    this.originalPuzzle = new Puzzle(initialBoard);
    this.modifiedPuzzle = new Puzzle(initialBoard.twin());

    while (!originalPuzzle.isSolved() && !modifiedPuzzle.isSolved()) {
      originalPuzzle.makeStep();
      modifiedPuzzle.makeStep();
    }

  }          // find a solution to the initial board (using the A* algorithm)


  public boolean isSolvable() {
    return originalPuzzle.isSolved();
  }           // is the initial board solvable?

  public int moves() {
    if (originalPuzzle.isSolved()) {
      return originalPuzzle.moves();
    } else {
      return -1;
    }
  }                     // min number of moves to solve initial board; -1 if unsolvable

  public Iterable<Board> solution() {
    if (originalPuzzle.isSolved()) {
      return originalPuzzle.solution();
    } else {
      return null;
    }
  }     // sequence of boards in a shortest solution; null if unsolvable

  public static void main(String[] args) {

  } // solve a slider puzzle (given below)

  private class Puzzle {
    private final Node initial;
    private final MinPQ<Node> queue;
    private Node min;

    Puzzle(Board initialBoard) {
      this.initial = new Node(null, initialBoard, 0);
      this.queue = new MinPQ<>();
      queue.insert(this.initial);
      this.min = this.initial;
    }

    public void makeStep() {

      min = this.queue.delMin();
      for (Node neighbor : min.neighbors()) {
        // first optimization
        this.queue.insert(neighbor);
      }

    }

    public boolean isSolved() {
      return this.min.isGoal();
    }

    public Iterable<Board> solution() {
      return this.min.getSolution();
    }

    public int moves() {
      return this.min.getMoves();
    }
  }

  private class Node implements Comparable<Node> {
    private final Board board;
    private final int depth;
    private final Node predecessor;
    private final boolean isGoal;
    private final int manhattanMetricValue;

    Node(Node predecessor, Board b, int depth) {
      this.board = b;
      this.depth = depth;
      this.predecessor = predecessor;
      this.isGoal = b.isGoal();
      this.manhattanMetricValue = b.manhattan();
    }

    @Override
    public int compareTo(Node that) {
      return Integer.compare(this.metric(), that.metric());
    }

    private int metric() {
      return this.manhattanMetricValue + depth;
    }

    public boolean isGoal() {
      return this.isGoal;
    }

    public List<Node> neighbors() {
      List<Node> neighbors = new ArrayList<>();
      for (Board board : this.board.neighbors()) {
        neighbors.add(new Node(this, board, this.depth + 1));
      }
      return neighbors;
    }

    public List<Board> getSolution() {
      LinkedList<Board> solution = new LinkedList<>();
      Node n = this;

      while (n.predecessor != null) {
        solution.addFirst(n.board);
        n = n.predecessor;
      }
      solution.addFirst(n.board);
      return solution;
    }

    public int getMoves() {
      int moves = 0;
      for (Node n = this; n.predecessor != null; n = n.predecessor) {
        moves++;
      }
      return moves;
    }

  }

}
