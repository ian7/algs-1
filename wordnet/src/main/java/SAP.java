import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

import java.util.HashMap;

public class SAP {
  private final Digraph digraph;
  private final int size;
  private Queue<Integer> queueLeft;
  private Queue<Integer> queueRight;
  private DistanceMap distancesLeft;
  private DistanceMap distancesRight;

  private class DistanceMap extends HashMap<Integer, Integer>{};

  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    this.digraph = new Digraph(G);
    this.size = G.V();
    this.queueLeft = new Queue<>();
    this.queueRight = new Queue<>();
    this.distancesLeft = new DistanceMap();
    this.distancesRight = new DistanceMap();
  }

  // length of shortest ancestral path bfetween v and w; -1 if no such path
  public int length(int v, int w) {
    final Queue<Integer> paramLeft = new Queue<>();
    final Queue<Integer> paramRight = new Queue<>();
    paramLeft.enqueue(v);
    paramRight.enqueue(w);
    return this.length(paramLeft, paramRight);
  }

  // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w) {
    final Queue<Integer> paramLeft = new Queue<>();
    final Queue<Integer> paramRight = new Queue<>();
    paramLeft.enqueue(v);
    paramRight.enqueue(w);
    return this.ancestor(paramLeft, paramRight);
  }

  private void setQueues(Iterable<Integer> v, Iterable<Integer> w){
    if (v == null || w == null) {
      throw new IllegalArgumentException();
    }
    this.queueLeft = new Queue<>();
    for (Integer i : v) {
      checkIndex(i);
      queueLeft.enqueue(i);
    }

    this.queueRight = new Queue<>();
    for (Integer i : w) {
      checkIndex(i);
      queueRight.enqueue(i);
    }

  }

  private DistanceMap traverse( Iterable<Integer> startingNodes ){
    DistanceMap distances = new DistanceMap();

    Queue<Integer> currentNodes = new Queue<>();
    for( Integer i : startingNodes){
      currentNodes.enqueue(i);
    }
    int currentDistance = 0;
    while( !currentNodes.isEmpty() ){
      Queue<Integer> nextNodes = new Queue<>();
      for( Integer i : currentNodes ) {
        if (!distances.containsKey(i)) {
          distances.put(i, currentDistance);
        }
        for( Integer n : this.digraph.adj(i)){
          if( !distances.containsKey(n) ) {
            nextNodes.enqueue(n);
          }
        }
      }
      currentDistance++;
      currentNodes = nextNodes;
    }
    return distances;
  }


  // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {

    if( this.ancestor(v,w) == -1 ){
      return -1;
    }
    else{
      return this.getDistance(this.ancestor(v,w));
    }
  }

  private void checkIndex(Integer i) {
    if (i == null || i < 0 || i >= this.size) {
      throw new IllegalArgumentException();
    }
  }

  private int getDistance( Integer index){
    if( this.distancesLeft.containsKey(index) && this.distancesRight.containsKey(index)){
      return (this.distancesLeft.get(index) + this.distancesRight.get(index));
    }
    else{
      return -1;
    }
  }

  // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    if (v == null || w == null) {
      throw new IllegalArgumentException();
    }
    distancesLeft = traverse(v);
    distancesRight= traverse(w);

    int topAncestorIndex = -1;
    int topDistance = Integer.MAX_VALUE;

    for( int i=0; i<this.size;i++){
      final int newDistance = getDistance(i);
      if( newDistance != -1 ){
        // so it is reachable from both
        if( topDistance > newDistance ){
          topAncestorIndex = i;
          topDistance = newDistance;
        }
      }
    }
    return topAncestorIndex;
  }

  // do unit testing of this class
  public static void main(String[] args) {
    // nothing here
  }
}
