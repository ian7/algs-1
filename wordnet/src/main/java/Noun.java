import java.util.ArrayList;
import java.util.List;

public class Noun {
  private final Integer id;
  private final String name;
  private final String definition;
  private final List<Noun> hypernyms;

  Noun(Integer id, String name, String definition) {
    this.id = id;
    this.name = name;
    this.definition = definition;
    this.hypernyms = new ArrayList<>();
  }

  public void addHypernym(Noun hypernym){
    this.hypernyms.add(hypernym);
  }
}
