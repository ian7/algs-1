import spock.lang.Specification

class WordNetTest extends Specification {
    def "file loading"(){
        given:
        def wn = new WordNet("synsets.txt","hypernyms.txt")
        expect:
        wn.nounsHash.size() > 0
    }
}
