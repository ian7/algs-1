import spock.lang.Shared
import spock.lang.Specification

class BoardTest extends Specification {
    @Shared
    def blocks = [[0,1,3],[4,2,5],[7,6,8]] as int[][]

    def "legit construction"(){
        when:
        def b = new Board(blocks)
        then:
        1==1
    }

    def "non-rectangular blocks"(){
        when:
        blocks = new int[3][5]
        new Board(blocks)
        then:
        thrown( IllegalArgumentException )
    }

    def "elements out of range-"(){
        when:
        blocks[1][1]=-1
        new Board(blocks)
        then:
        thrown( IllegalArgumentException )
    }

    def "elements out of range+"(){
        when:
        blocks[1][1]=124
        new Board(blocks)
        then:
        thrown( IllegalArgumentException )
    }

    def "duplicate elements"(){
        when:
        blocks[0][0]=1
        blocks[0][1]=1
        new Board(blocks)
        then:
        thrown( IllegalArgumentException )
    }

    def "isGoalNegative"(){
        when:
        def b = new Board(blocks)
        then:
        b.isGoal() == false
    }

    def "isGoalPositive"(){
        when:
        blocks = [[1,2,3],[4,5,6],[7,8,0]] as int[][]
        def b = new Board(blocks)
        then:
        b.isGoal() == true
    }
}
