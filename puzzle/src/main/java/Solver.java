import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
  private final Node initial;
  private MinPQ<Node> queue;

  public Solver(Board initial) {
    this.initial = new Node(initial,0);
    this.queue = new MinPQ<>();
    queue.insert(this.initial);

  }          // find a solution to the initial board (using the A* algorithm)

  public boolean isSolvable() {

    return false;
  }           // is the initial board solvable?

  public int moves() {
    return 0;
  }                     // min number of moves to solve initial board; -1 if unsolvable

  public Iterable<Board> solution() {
    List<Board> solution = new ArrayList<>();
    Board predecessor = null;

    while (!this.queue.min().isGoal()) {
      Node min = this.queue.delMin();
      solution.add(min.board);
      for (Node neighbor : min.neighbors()) {
        // first optimization
        if( predecessor == null || (
            predecessor != null && !predecessor.equals(neighbor.board))) {
          this.queue.insert(neighbor);
        }
      }
      predecessor = min.board;
    }
    solution.add(this.queue.min().board);
    return solution;
  }     // sequence of boards in a shortest solution; null if unsolvable

  public static void main(String[] args) {

  } // solve a slider puzzle (given below)

  private class Node implements Comparable<Node> {
    private Board board;
    private int depth;

    Node(Board b, int depth) {
      this.board = b;
      this.depth = depth;
    }

    @Override
    public int compareTo(Node that) {
      return Integer.compare(this.metric(),that.metric());
    }

    private int metric() {
      return this.depth + this.board.hamming();
    }

    public boolean isGoal(){
      return this.board.isGoal();
    }

    public List<Node> neighbors(){
      List<Node> neighbors = new ArrayList<>();
      for( Board board : this.board.neighbors()){
        neighbors.add( new Node( board, this.depth+1 ));
      }
      return neighbors;
    }

  }

}
