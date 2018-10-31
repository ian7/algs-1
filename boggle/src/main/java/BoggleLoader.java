import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class BoggleLoader {
  private final BoggleSolver bs;
  private final String[] dictionary;

  public BoggleLoader(String filename) {
    final In in = new In(filename);
    final List<String> lines = new ArrayList<>();
    while (in.hasNextLine()) {
      lines.add(in.readLine());
    }
    dictionary = lines.toArray(new String[1]);
    this.bs = new BoggleSolver(dictionary);
  }

  public int length() {
    return this.dictionary.length;
  }

  public BoggleBoard loadBoard(String filename) {
    return new BoggleBoard(filename);
  }

  public String[] getDictionary() {
    return this.dictionary;
  }

  public int getTotalScore(List<String> words) {
    int score = 0;
    for (String w : words) {
      score += bs.scoreOf(w);
    }
    return score;
  }

  public static void main(String[] args) {
    BoggleLoader bl = new BoggleLoader("dictionary-yawl.txt");
    BoggleBoard b = bl.loadBoard("board-estrangers.txt");
    BoggleSolver bs = new BoggleSolver(bl.getDictionary());
    for (int i = 0; i < 1; i++) {
      bs.getAllValidWords(b);
    }
  }
}
