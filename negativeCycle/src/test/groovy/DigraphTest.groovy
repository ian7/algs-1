import spock.lang.Specification

class DigraphTest extends Specification {
    def "empty graph"(){
        when:
        def g = new Digraph();
        then:
        g.size() == 0
    }
}
