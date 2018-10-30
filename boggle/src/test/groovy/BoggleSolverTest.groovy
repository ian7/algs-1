import spock.lang.Specification

class BoggleSolverTest extends Specification {
    def "smallDict"(){
        when:
        def bl = new BoggleLoader("dictionary-algs4.txt")
        def b = bl.loadBoard("board-points1000.txt")
        def bs = new BoggleSolver(bl.getDictionary())
        then:
        bs.getAllValidWords(b).size()==52
    }
    def "largeDict"(){
        when:
        def bl = new BoggleLoader("dictionary-yawl.txt")
        def b = bl.loadBoard("board-points1000.txt")
        def bs = new BoggleSolver(bl.getDictionary())
        then:
        bs.getAllValidWords(b).size()==460
    }
}
