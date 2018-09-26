import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Topological;

import java.util.HashMap;

public class SAP {
  private final Digraph digraph;
  private final int size;

  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    this.digraph = G;
    this.size = G.V();
    DirectedCycle dc = new DirectedCycle(this.digraph);
    //Topological t = new Topological(this.digraph);
    if( dc.hasCycle() ) {//|| !t.hasOrder()){
      throw new IllegalArgumentException();
    }
  }

  // length of shortest ancestral path bfetween v and w; -1 if no such path
  public int length(int v, int w) {
    final Queue<Integer> queueLeft = new Queue<>();
    final Queue<Integer> queueRight = new Queue<>();
    queueLeft.enqueue(v);
    queueRight.enqueue(w);
    return this.length(queueLeft, queueRight);
  }

  // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w) {
    final Queue<Integer> queueLeft = new Queue<>();
    final Queue<Integer> queueRight = new Queue<>();
    queueLeft.enqueue(v);
    queueRight.enqueue(w);
    return this.ancestor(queueLeft, queueRight);
  }


  // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    Queue<Integer> queueLeft = new Queue<>();
    for (Integer i : v) {
      checkIndex(i);
      queueLeft.enqueue(i);
    }

    Queue<Integer> queueRight = new Queue<>();
    for (Integer i : w) {
      checkIndex(i);
      queueRight.enqueue(i);
    }

    final HashMap<Integer, Integer> distancesLeft = new HashMap<>();
    final HashMap<Integer, Integer> distancesRight = new HashMap<>();

    int currentDistance = 0;
    int ancestor = -1;
    int length = -1;

    while (ancestor == -1) {
      Queue<Integer> nextLeftRow = new Queue<>();
      Queue<Integer> nextRightRow = new Queue<>();

      for (Integer i : queueLeft) {
        if (!distancesLeft.containsKey(i)) {
          distancesLeft.put(i, currentDistance);
        }
        if (distancesRight.containsKey(i)) {
          ancestor = i;
          length = distancesRight.get(i) + distancesLeft.get(i);
          break;
        }
        for (Integer j : this.digraph.adj(i)) {
          nextLeftRow.enqueue(j);
        }
      }
      for (Integer i : queueRight) {
        if (!distancesRight.containsKey(i)) {
          distancesRight.put(i, currentDistance);
        }
        if (distancesLeft.containsKey(i)) {
          ancestor = i;
          length = distancesRight.get(i) + distancesLeft.get(i);
          break;
        }
        for (Integer j : this.digraph.adj(i)) {
          nextRightRow.enqueue(j);
        }
      }
      queueLeft = nextLeftRow;
      queueRight = nextRightRow;
      currentDistance++;
      if (queueLeft.size() == 0 && queueRight.size() == 0 && ancestor == -1) {
        return -1;
      }
    }
    return length;
  }

  private void checkIndex(Integer i) {
    if (i == null || i < 0 || i >= this.size) {
      throw new IllegalArgumentException();
    }
  }

  // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    Queue<Integer> queueLeft = new Queue<>();
    for (Integer i : v) {
      checkIndex(i);
      queueLeft.enqueue(i);
    }

    Queue<Integer> queueRight = new Queue<>();
    for (Integer i : w) {
      checkIndex(i);
      queueRight.enqueue(i);
    }

    final HashMap<Integer, Integer> distancesLeft = new HashMap<>();
    final HashMap<Integer, Integer> distancesRight = new HashMap<>();

    int currentDistance = 0;
    int ancestor = -1;
    int length = -1;

    while (ancestor == -1) {
      Queue<Integer> nextLeftRow = new Queue<>();
      Queue<Integer> nextRightRow = new Queue<>();

      for (Integer i : queueLeft) {
        if (!distancesLeft.containsKey(i)) {
          distancesLeft.put(i, currentDistance);
        }
        if (distancesRight.containsKey(i)) {
          ancestor = i;
          length = distancesRight.get(i) + distancesLeft.get(i);
          break;
        }
        for (Integer j : this.digraph.adj(i)) {
          nextLeftRow.enqueue(j);
        }
      }
      for (Integer i : queueRight) {
        if (!distancesRight.containsKey(i)) {
          distancesRight.put(i, currentDistance);
        }
        if (distancesLeft.containsKey(i)) {
          ancestor = i;
          length = distancesRight.get(i) + distancesLeft.get(i);
          break;
        }
        for (Integer j : this.digraph.adj(i)) {
          nextRightRow.enqueue(j);
        }
      }
      queueLeft = nextLeftRow;
      queueRight = nextRightRow;
      currentDistance++;
      if (queueLeft.size() == 0 && queueRight.size() == 0 && ancestor == -1) {
        return -1;
      }
    }
    return ancestor;
  }

  // do unit testing of this class
  public static void main(String[] args) {
    // nothing here
  }
}
