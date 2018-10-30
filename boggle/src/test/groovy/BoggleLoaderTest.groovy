import spock.lang.Specification

class BoggleLoaderTest extends Specification {
    def "load dicitionary-algs4"(){
        when:
        def bl = new BoggleLoader("dictionary-algs4.txt")
        then:
        bl.length() == 6013
    }
    def "load dicitionary-yawl"(){
        when:
        def bl = new BoggleLoader("dictionary-yawl.txt")
        then:
        bl.length() == 264061
    }
}
