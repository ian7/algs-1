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
    def "load board1000"(){
        when:
        def bl = new BoggleLoader("dictionary-algs4.txt")
        def b = bl.loadBoard("board-points1000.txt")
        then:
        b.cols() == 4
        b.rows() == 4
    }
    def "load anti"(){
        when:
        def bl = new BoggleLoader("dictionary-algs4.txt")
        def b = bl.loadBoard("board-antidisestablishmentarianisms.txt")
        then:
        b.cols() == 29
        b.rows() == 1
    }

}
