import spock.lang.Specification

class SolverTest extends Specification {
    def "legit construction"(){
        when:
        def blocks = [[0,1,3],[4,2,5],[7,8,6]] as int[][]
        def b = new Board(blocks)
        def s = new Solver(b)
        then:
        s.solution().size() == 5
        s.isSolvable()
        s.moves() == 4
    }

    def "dual construction"(){
        when:
        def blocks = [[0,4,3],[1,2,5],[7,8,6]] as int[][]
        def b = new Board(blocks)
        def s = new Solver(b)
        then:
        s.isSolvable() == false
        s.moves() == -1
    }

    def "legit construction2"(){
        when:
        def blocks = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def b = new Board(blocks)
        def s = new Solver(b)
        then:
        s.solution().size() == 15
        s.isSolvable()
        s.moves() == 14
    }


    def "legit construction3"(){
        when:
        def blocks = [[1,6,4],[0,3,5],[8,2,7]] as int[][]
        def b = new Board(blocks)
        def s = new Solver(b)
        then:
        s.solution().size() == 28
        s.isSolvable()
        s.moves() == 27
    }

}
