public class Outcast {
  private final WordNet wordNet;

  public Outcast(WordNet wordnet) {
    this.wordNet = wordnet;
  }         // constructor takes a WordNet object

  public String outcast(String[] nouns) {
    if (nouns == null || nouns.length == 0) {
      throw new IllegalArgumentException();
    }
    int[] distance = new int[nouns.length];

    for (int i = 0; i < nouns.length; i++) {
      distance[i] = 0;
      if (nouns[i] == null) {
        throw new IllegalArgumentException();
      }
      for (int j = 0; j < nouns.length; j++) {
        if (i != j) {
          distance[i] += wordNet.distance(nouns[i], nouns[j]);
        }
      }
    }

    int outcastIndex = 0;

    for (int i = 1; i < nouns.length; i++) {
      if (distance[i] > distance[outcastIndex]) {
        outcastIndex = i;
      }
    }
    return nouns[outcastIndex];
  }  // given an array of WordNet nouns, return an outcast

  public static void main(String[] args) {
    // nothing here
  }  // see test client below
}
