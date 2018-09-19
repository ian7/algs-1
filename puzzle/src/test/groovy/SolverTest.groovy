import spock.lang.Specification

class SolverTest extends Specification {
    def "legit construction"(){
        when:
        def blocks = [[0,1,3],[4,2,5],[7,8,6]] as int[][]
        def b = new Board(blocks)
        def s = new Solver(b)
        def solution = s.solution()
        then:
        1==1
    }
}
