import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordNet {
  private HashMap<Integer, String[]> nounHash;
  private HashMap<String, List<Integer>> reverseNounHash;
  private final Digraph digraph;
  private final SAP sap;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) {
    if (synsets == null || hypernyms == null) {
      throw new IllegalArgumentException();
    }
    final int graphSize = loadNouns(synsets);
    this.digraph = new Digraph(graphSize);
    loadHypernyms(hypernyms);
    this.sap = new SAP(this.digraph);

    DirectedCycle dc = new DirectedCycle(this.digraph);
    Topological t = new Topological(this.digraph);
    if (dc.hasCycle() || !t.hasOrder()) {
      throw new IllegalArgumentException();
    }
  }

  private int loadNouns(String synsets) {

    this.nounHash = new HashMap<>();
    this.reverseNounHash = new HashMap<>();

    // pass the path to the file as a parameter
    final In in = new In(synsets);
    String rawLine = in.readLine();

    while (rawLine != null) {
      final String[] line = rawLine.split(",", 3);

      final Integer synsetId = Integer.valueOf(line[0]);
      final String[] splitSynset = line[1].split("\\s+");
      this.nounHash.put(synsetId, splitSynset);

      for (String s : splitSynset) {
        if (this.reverseNounHash.containsKey(s)) {
          this.reverseNounHash.get(s).add(synsetId);
        } else {
          ArrayList<Integer> il = new ArrayList<>();
          il.add(synsetId);
          this.reverseNounHash.put(s, il);
        }
      }
      rawLine = in.readLine();
    }
    return nounHash.size();
  }

  private void loadHypernyms(String hypernyms) {


    // pass the path to the file as a parameter
    final In in = new In(hypernyms);
    String rawLine = in.readLine();

    while (rawLine != null) {
      final String[] line = rawLine.split(",", 2);

      final Integer synsetId = Integer.valueOf(line[0]);

      if (line.length > 1) {
        // if there are any hypernyms after all
        final String[] hypernymStrings = line[1].split(",");
        for (String hymernymString : hypernymStrings) {
          final Integer hypernymId = Integer.valueOf(hymernymString);
          this.digraph.addEdge(synsetId, hypernymId);
        }
      }
      rawLine = in.readLine();
    }
  }

  // returns all WordNet nounHash
  public Iterable<String> nouns() {
    return this.reverseNounHash.keySet();
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    if (word == null) {
      throw new IllegalArgumentException();
    }
    return this.reverseNounHash.containsKey(word);
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    List<Integer> indicesA = stringToIndices(nounA);
    List<Integer> indicesB = stringToIndices(nounB);

    if (indicesA.isEmpty() || indicesB.isEmpty()) {
      throw new IllegalArgumentException();
    }

    return sap.length(indicesA, indicesB);
  }

  private List<Integer> stringToIndices(String string) {
    if (string == null) {
      throw new IllegalArgumentException();
    }

    final String[] splitString = string.split("\\s+");
    final ArrayList<Integer> indices = new ArrayList<>();

    for (String s : splitString) {
      if (this.reverseNounHash.containsKey(s)) {
        for (Integer i : this.reverseNounHash.get(s)) {
          indices.add(i);
        }
      }
    }
    return indices;
  }

  // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    List<Integer> indicesA = stringToIndices(nounA);
    List<Integer> indicesB = stringToIndices(nounB);

    if (indicesA.isEmpty() || indicesB.isEmpty()) {
      throw new IllegalArgumentException();
    }

    final int ancestorId = sap.ancestor(indicesA, indicesB);
    if (ancestorId != -1) {
      StringBuilder sb = new StringBuilder();
      for (String s : this.nounHash.get(ancestorId)) {
        sb.append(s);
        sb.append(" ");
      }
      return sb.toString().trim();
    } else
      return null;
  }

  // do unit testing of this class
  public static void main(String[] args) {
    // nothing here
  }
}
