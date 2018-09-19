import spock.lang.Specification

class SolverTest extends Specification {
    def "legit construction"(){
        when:
        def blocks = [[0,1,3],[4,2,5],[7,8,6]] as int[][]
        def b = new Board(blocks)
        def s = new Solver(b)
        then:
        s.solution().size() == 4
        s.isSolvable()
        s.moves() == 5
    }

    def "dual construction"(){
        when:
        def blocks = [[0,3,1],[4,2,5],[7,8,6]] as int[][]
        def b = new Board(blocks)
        def s = new Solver(b)
        then:
        s.isSolvable() == false
        s.moves() == -1
    }

}
