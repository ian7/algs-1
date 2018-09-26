import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

import java.util.HashMap;

public class SAP {
  private final Digraph digraph;

  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G){
    this.digraph = G;
  }

  // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w){
    return 0;
  }

  // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w){
    final Queue<Integer> left = new Queue<>();
    final Stack<Integer> right = new Stack<>();

    boolean done = false;

    Queue<Integer> queueLeft = new Queue<>();
    for( Integer i : this.digraph.adj(v)){
      queueLeft.enqueue(i);
    }
    Queue<Integer> queueRight = new Queue<>();
    for( Integer i : this.digraph.adj(w)){
      queueRight.enqueue(i);
    }

    final HashMap<Integer,Integer> distancesLeft = new HashMap<>();
    final HashMap<Integer,Integer> distancesRight = new HashMap<>();

    int currentDistance = 1;
    int ancestor = -1;

    while(ancestor==-1){
      Queue<Integer> nextLeftRow = new Queue<>();
      Queue<Integer> nextRightRow = new Queue<>();
      for( Integer i : queueLeft ){
        if( !distancesLeft.containsKey( i )) {
          distancesLeft.put(i, currentDistance);
        }
        if( distancesRight.containsKey(i)){
          ancestor = i;
          break;
        }
        for( Integer j : this.digraph.adj(i)){
          nextLeftRow.enqueue(j);
        }
      }
      for( Integer i : queueRight ){
        if( !distancesRight.containsKey( i )) {
          distancesRight.put(i, currentDistance);
        }
        if( distancesLeft.containsKey(i)){
          ancestor = i;
          break;
        }
        for( Integer j : this.digraph.adj(i)){
          nextRightRow.enqueue(j);
        }
      }
      queueLeft = nextLeftRow;
      queueRight = nextRightRow;
      currentDistance++;
      if( queueLeft.size() == 0 && queueRight.size() == 0 && ancestor == -1){
        return -1;
      }
    }

    return ancestor;
  }



  // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w){
    return 0;

  }

  // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
    return 0;

  }

  // do unit testing of this class
  public static void main(String[] args){

  }
}
