
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

public class BoggleSolver {
  private final MyTrieSET tst = new MyTrieSET();

  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
  public BoggleSolver(String[] dictionary) {
    for (String s : dictionary) {
      tst.add(s);
    }
  }

  // Returns the set of all valid words in the given Boggle board, as an Iterable.
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    ArrayList<String> words = new ArrayList<>();
    boolean[][] predecessors = new boolean[board.rows()][board.cols()];

    for (int i = 0; i < board.rows(); i++) {
      for (int j = 0; j < board.cols(); j++) {
        collect(board, i, j, predecessors, "", words);
      }
    }
    return words;
  }

  private void collect(BoggleBoard board, int row, int col, boolean[][] predecessors, String prefix, ArrayList<String> set) {
    if (predecessors[row][col]) {
      return;
    }

    char letter = board.getLetter(row, col);
    String word = prefix;
    if (letter == 'Q') {
      word += "QU";
    } else {
      word += letter;
    }
    if (!tst.containsPrefix(word)) {
      return;
    }
    if (word.length() > 2 && tst.contains(word) && !set.contains(word)) {
      set.add(word);
    }

    predecessors[row][col] = true;

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        // eliminate self
        if (i == 0 && j == 0) {
          continue;
        }
        if ((row + i >= 0) && (row + i < board.rows()) && col + j >= 0 && col + j < board.cols()) {
          collect(board, row + i, col + j, predecessors, word, set);
        }
      }
    }
    predecessors[row][col] = false;
  }

  // Returns the score of the given word if it is in the dictionary, zero otherwise.
  // (You can assume the word contains only the uppercase letters A through Z.)
  public int scoreOf(String word) {
    final int length = word.length();
    if (length < 3) {
      return 0;
    }
    if (length < 5) {
      return 1;
    }
    if (length == 5) {
      return 2;
    }
    if (length == 6) {
      return 3;
    }
    if (length == 7) {
      return 5;
    }
    return 11;
  }

}

