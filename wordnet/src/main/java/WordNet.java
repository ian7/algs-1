import edu.princeton.cs.algs4.Digraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class WordNet {
  private HashMap<Integer,String> nounHash;
  private HashMap<String,Integer> reverseNounHash;
  private final Digraph digraph;
  private final int graphSize;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) throws FileNotFoundException {
    if( synsets == null || hypernyms == null ){
      throw new IllegalArgumentException();
    }
    this.graphSize = loadNouns(synsets);
    this.digraph = new Digraph(this.graphSize);
    loadHypernyms(hypernyms);

  }

  private int loadNouns(String synsets) throws FileNotFoundException {

    this.nounHash = new HashMap<>();
    this.reverseNounHash = new HashMap<>();

    // pass the path to the file as a parameter
    final File file = new File(synsets);
    final Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        final String[] line = sc.nextLine().split(",",3);

        final Integer synsetId = Integer.valueOf(line[0]);
        final String synset = line[1];
        this.nounHash.put(synsetId, synset);
        this.reverseNounHash.put(synset,synsetId);
      }
    return nounHash.size();
  }

  private void loadHypernyms(String hypernyms) throws FileNotFoundException {


    // pass the path to the file as a parameter
    final File file = new File(hypernyms);
    final Scanner sc = new Scanner(file);

    while (sc.hasNextLine()){
      final String[] line = sc.nextLine().split(",",2);

      final Integer synsetId = Integer.valueOf(line[0]);

      if( line.length > 1 ) {
        // if there are any hypernyms after all
        final String[] hypernymStrings = line[1].split(",");
        for (String hymernymString : hypernymStrings) {
          final Integer hypernymId = Integer.valueOf(hymernymString);
          this.digraph.addEdge(synsetId,hypernymId);
        }
      }
    }
  }

  // returns all WordNet nounHash
  public Iterable<String> nouns() {
    return this.nounHash.values();
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    return this.reverseNounHash.containsKey(word);
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    if( !this.reverseNounHash.containsKey(nounA) ||
        !this.reverseNounHash.containsKey(nounB)){
      throw new IllegalArgumentException();
    }

    final int nounAId = this.reverseNounHash.get(nounA);
    final int nounBId = this.reverseNounHash.get(nounB);
    final SAP sap = new SAP(this.digraph);

    return sap.length(nounAId,nounBId);
  }

  // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    if( !this.reverseNounHash.containsKey(nounA) ||
      !this.reverseNounHash.containsKey(nounB)){
      throw new IllegalArgumentException();
    }
    final int nounAId = this.reverseNounHash.get(nounA);
    final int nounBId = this.reverseNounHash.get(nounB);
    final SAP sap = new SAP(this.digraph);

    final int ancestorId = sap.ancestor(nounAId,nounBId);
    if( ancestorId != -1 ){
      return this.nounHash.get(ancestorId);
    }
    else
      return null;
  }

  // do unit testing of this class
  public static void main(String[] args) {

  }
}
