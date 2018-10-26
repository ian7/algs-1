import spock.lang.Specification

class BaseballEliminationTest extends Specification {
    def "factorial"(){
        expect:
        BaseballElimination.factorial(1i)==1
        BaseballElimination.factorial(2i)==2
        BaseballElimination.factorial(3i)==6
        BaseballElimination.factorial(4i)==24
    }
    def "combinations"(){
        expect:
        BaseballElimination.combinations(4i,2i)==6
    }
}
