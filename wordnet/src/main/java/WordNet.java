import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class WordNet {
  private final HashMap<Integer,Noun> nounsHash;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) throws FileNotFoundException {
    this.nounsHash = loadNouns(synsets);
    loadHypernyms(hypernyms);
  }

  private HashMap<Integer,Noun> loadNouns(String synsets) throws FileNotFoundException {

    HashMap<Integer,Noun> nouns = new HashMap<>();

    // pass the path to the file as a parameter
    final File file = new File(synsets);
    final Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        final String[] line = sc.nextLine().split(",",3);

        final Integer synsetId = Integer.valueOf(line[0]);
        final String synset = line[1];
        final String definition = line[2];
        final Noun noun = new Noun(synsetId, synset, definition);
        nouns.put(synsetId, noun);
      }
    return nouns;
  }

  private void loadHypernyms(String hypernyms) throws FileNotFoundException {


    // pass the path to the file as a parameter
    final File file = new File(hypernyms);
    final Scanner sc = new Scanner(file);

    while (sc.hasNextLine()){
      final String[] line = sc.nextLine().split(",",2);

      final Integer synsetId = Integer.valueOf(line[0]);
      Noun noun = this.nounsHash.get(synsetId);

      if( line.length > 1 ) {
        // if there are any hypernyms after all
        final String[] hypernymStrings = line[1].split(",");
        for (String hymernymString : hypernymStrings) {
          final Integer hypernymId = Integer.valueOf(hymernymString);
          final Noun hypernymNoun = this.nounsHash.get(hypernymId);
          noun.addHypernym(hypernymNoun);
        }
      }
    }
  }

  // returns all WordNet nounsHash
  public Iterable<String> nouns() {
    return null;
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    return false;
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    return 0;
  }

  // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    return null;
  }

  // do unit testing of this class
  public static void main(String[] args) {

  }
}
