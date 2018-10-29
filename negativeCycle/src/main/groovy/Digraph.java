import java.util.ArrayList;

public class Digraph<T> {
  private final ArrayList<T> nodes;
  private final ArrayList<Edge<T>> edges;

  Digraph(){
    this.nodes = new ArrayList<>();
    this.edges = new ArrayList<>();
  }

  int size(){
    return this.nodes.size();
  }

  private class Edge<T>{
    private final T from;
    private final T to;

    Edge( T from, T to ){
      this.from = from;
      this.to = to;
    }

    public T getFrom() {
      return from;
    }

    public T getTo() {
      return to;
    }

  }
}
