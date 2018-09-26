import spock.lang.Shared
import spock.lang.Specification

class OutcastTest extends Specification {
    @Shared
            wn

    def setup() {
        wn = new WordNet("synsets.txt", "hypernyms.txt")
    }

    def "find outcasts"() {
        given:
        def String[] outcast5 = ["zebra", "cat", "bear", "table"]
        when:
        def outcast = new Outcast(wn)
        then:
        outcast.outcast(outcast5) == "table"
    }

}
