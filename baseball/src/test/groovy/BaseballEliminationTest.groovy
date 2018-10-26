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
    def "loading4"(){
        when:
        def be = new BaseballElimination("teams4.txt")
        then:
        be.isEliminated("Atlanta") == false
        be.isEliminated("Philadelphia") == true
        be.isEliminated("New_York") == false
        be.isEliminated("Montreal") == true

    }
    def "loading5"(){
        when:
        def be = new BaseballElimination("teams5.txt")
        then:
        be.isEliminated("New_York") == false
        be.isEliminated("Detroit") == true
        be.isEliminated("Baltimore") == false
        be.isEliminated("Boston") == false
        be.isEliminated("Toronto") == false

    }
}
