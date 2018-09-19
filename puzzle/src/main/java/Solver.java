import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
  private Puzzle originalPuzzle;
  private Puzzle modifiedPuzzle;

  public Solver(Board initialBoard) {
    if( initialBoard == null ){
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
    if( originalPuzzle.isSolved()) {
      return originalPuzzle.solution.size();
    }
    else{
      return -1;
    }
  }                     // min number of moves to solve initial board; -1 if unsolvable

  public Iterable<Board> solution() {
    if( originalPuzzle.isSolved()){
      return originalPuzzle.solution();
    }
    else{
      return null;
    }
  }     // sequence of boards in a shortest solution; null if unsolvable

  public static void main(String[] args) {

  } // solve a slider puzzle (given below)

  private class Puzzle {
    private final Node initial;
    private MinPQ<Node> queue;
    private List<Board> solution;
    private Board predecessor;

    Puzzle(Board initialBoard) {
      this.initial = new Node(initialBoard, 0);
      this.queue = new MinPQ<>();
      queue.insert(this.initial);
      this.solution = new ArrayList<>();
      this.predecessor = null;
    }

    public void makeStep() {

      Node min = this.queue.delMin();
      solution.add(min.board);
      for (Node neighbor : min.neighbors()) {
        // first optimization
        if (predecessor == null || (
            predecessor != null && !predecessor.equals(neighbor.board))) {
          this.queue.insert(neighbor);
        }
      }
      predecessor = min.board;
    }

    public boolean isSolved() {
      return this.queue.min().isGoal();
    }

    public Iterable<Board> solution() {
      return solution;
    }
  }

  private class Node implements Comparable<Node> {
    private Board board;
    private int depth;

    Node(Board b, int depth) {
      this.board = b;
      this.depth = depth;
    }

    @Override
    public int compareTo(Node that) {
      return Integer.compare(this.metric(), that.metric());
    }

    private int metric() {
      return this.depth + this.board.hamming();
    }

    public boolean isGoal() {
      return this.board.isGoal();
    }

    public List<Node> neighbors() {
      List<Node> neighbors = new ArrayList<>();
      for (Board board : this.board.neighbors()) {
        neighbors.add(new Node(board, this.depth + 1));
      }
      return neighbors;
    }

  }

}
