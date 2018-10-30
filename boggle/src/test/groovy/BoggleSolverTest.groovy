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
    def "scoreOf"(){
        when:
        def bl = new BoggleLoader("dictionary-algs4.txt")
        def bs = new BoggleSolver(bl.getDictionary())
        then:
        bs.scoreOf("") == 0
        bs.scoreOf("a") == 0
        bs.scoreOf("ab") == 0
        bs.scoreOf("abc") == 1
        bs.scoreOf("abcd") == 1
        bs.scoreOf("abcde") == 2
        bs.scoreOf("abcdef") == 3
        bs.scoreOf("abcdefg") == 5
        bs.scoreOf("abcdefgh") == 11
        bs.scoreOf("abcdefghi") == 11
    }
    def "totalScoreOf"(){
        when:
        def bl = new BoggleLoader("dictionary-yawl.txt")
        def b = bl.loadBoard("board-points1000.txt")
        def bs = new BoggleSolver(bl.getDictionary())
        then:
        bl.getTotalScore(bs.getAllValidWords(b)) == 1000
    }

}
