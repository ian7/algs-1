import edu.princeton.cs.algs4.TST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BoggleSolver {
  private final TST<Integer> tst = new TST<>();

  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
  public BoggleSolver(String[] dictionary) {
    for (String s : dictionary) {
      tst.put(s, 7);
    }
  }

  // Returns the set of all valid words in the given Boggle board, as an Iterable.
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    DiceBoard db = new DiceBoard(board);
    return db.findWords();
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

  private class DiceBoard {
    private final Queue<Dice> queue = new LinkedList<>();

    DiceBoard(BoggleBoard board) {
      for (int i = 0; i < board.rows(); i++) {
        for (int j = 0; j < board.cols(); j++) {
          //this.dices[i][j] = new BoggleDice(board.getLetter(i,j));
          this.queue.add(new Dice(board, i, j, new ArrayList<Dice>()));
        }
      }
    }

    public List<String> findWords() {
      List<String> words = new ArrayList<>();
      while (!queue.isEmpty()) {
        Dice d = queue.poll();
        for (Dice n : d.getNeighbors()) {
          if (tst.keysWithPrefix(n.string).iterator().hasNext()) {
            queue.add(n);
          }
        }
        //queue.addAll(d.getNeighbors());
        if (d.string.length() > 2 && tst.contains(d.string)) {
          if (!words.contains(d.string)) {
            words.add(d.string);
          }
        }
      }
      return words;
    }

    private class Dice {
      final int i;
      final int j;
      final List<Dice> predecessors;
      final BoggleBoard board;
      final String string;
      List<Dice> neighbors = null;

      public List<Dice> getNeighbors() {
        if (neighbors == null) {
          neighbors = new ArrayList<>();
          populateNeighbors();
        }
        return neighbors;
      }


      Dice(BoggleBoard board, int i, int j, List<Dice> predecessors) {
        this.board = board;
        this.i = i;
        this.j = j;
        StringBuilder sb = new StringBuilder();
        if (!predecessors.isEmpty()) {
          sb.append(predecessors.get(predecessors.size() - 1).string);
        }
        final char letter = board.getLetter(i, j);
        if (letter == 'Q') {
          sb.append("QU");
        } else {
          sb.append(String.valueOf(letter));
        }
        this.string = sb.toString();
        this.predecessors = new ArrayList<>();
        this.predecessors.addAll(predecessors);
        this.predecessors.add(this);
      }

      private void populateNeighbors() {
        if (!isFirstColumn()) {
          // left
          addNeighbor(this.i, this.j - 1);
          if (!isFistRow()) {
            addNeighbor(this.i - 1, this.j - 1);
          }
          if (!isLastRow()) {
            addNeighbor(this.i + 1, this.j - 1);
          }
        }
        if (!isLastColumn()) {
          // right
          addNeighbor(this.i, this.j + 1);
          if (!isFistRow()) {
            addNeighbor(this.i - 1, this.j + 1);
          }
          if (!isLastRow()) {
            addNeighbor(this.i + 1, this.j + 1);
          }
        }
        if (!isFistRow()) {
          addNeighbor(this.i - 1, this.j);
          if (!isFirstColumn()) {
            addNeighbor(this.i - 1, this.j - 1);
          }
          if (!isLastColumn()) {
            addNeighbor(this.i - 1, this.j + 1);
          }
        }
        if (!isLastRow()) {
          addNeighbor(this.i + 1, this.j);
          if (!isFirstColumn()) {
            addNeighbor(this.i + 1, this.j - 1);
          }
          if (!isLastColumn()) {
            addNeighbor(this.i + 1, this.j + 1);
          }
        }
      }

      private boolean addNeighbor(int i, int j) {
        //check predecessors
        for (Dice p : this.predecessors) {
          if (p.i == i && p.j == j) {
            return false;
          }
        }
        neighbors.add(new Dice(this.board, i, j, this.predecessors));
        return true;
      }


      boolean isFirstColumn() {
        return j == 0;
      }

      boolean isLastColumn() {
        return j == board.cols() - 1;
      }

      boolean isFistRow() {
        return i == 0;
      }

      boolean isLastRow() {
        return i == board.rows() - 1;
      }
    }
  }
}

