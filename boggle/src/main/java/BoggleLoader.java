import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class BoggleLoader {
  private final BoggleSolver bs;
  private final String[] dictionary;
  public BoggleLoader(String filename) {
    final In in = new In(filename);
    final List<String> lines = new ArrayList<>();
    while( in.hasNextLine() ){
      lines.add(in.readLine());
    }
    dictionary = lines.toArray(new String[1]);
    this.bs = new BoggleSolver(dictionary);
  }
  public int length(){
    return this.dictionary.length;
  }
}
