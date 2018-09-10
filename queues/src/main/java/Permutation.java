import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
  public static void main(String[] args) {
    if (args.length != 1) {
      throw new RuntimeException("wrong number of arguments - should be exactly one");
    }
    final RandomizedQueue<String> rq = new RandomizedQueue<String>();
    final int outputSize = Integer.parseInt(args[0]);
    while (!StdIn.isEmpty()) {
      String item = StdIn.readString();
      rq.enqueue(item);
    }   // unit testing (optional)
    for (int i = 0; i < outputSize; i++) {
      StdOut.print(rq.dequeue() + "\n");
    }
  }
}
