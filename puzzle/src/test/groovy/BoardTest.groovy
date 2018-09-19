import spock.lang.Specification

class BoardTest extends Specification {

    def "legit construction"(){
        when:
        def blocks = [[0,1,3],[4,2,5],[7,6,8]] as int[][]
        new Board(blocks)
        then:
        1==1
    }

    def "non-rectangular blocks"(){
        when:
        def blocks = new int[3][5]
        new Board(blocks)
        then:
        thrown( IllegalArgumentException )
    }

    def "elements out of range-"(){
        when:
        def blocks = [[0,1,3],[4,2,5],[7,6,8]] as int[][]
        blocks[1][1]=-1
        new Board(blocks)
        then:
        thrown( IllegalArgumentException )
    }

    def "elements out of range+"(){
        when:
        def blocks = [[0,1,3],[4,2,5],[7,6,8]] as int[][]
        blocks[1][1]=124
        new Board(blocks)
        then:
        thrown( IllegalArgumentException )
    }

    def "duplicate elements"(){
        when:
        def blocks = [[0,1,3],[4,2,5],[7,6,8]] as int[][]
        blocks[0][0]=1
        blocks[0][1]=1
        new Board(blocks)
        then:
        thrown( IllegalArgumentException )
    }

    def "isGoalNegative"(){
        when:
        def blocks = [[0,1,3],[4,2,5],[7,6,8]] as int[][]
        def b = new Board(blocks)
        then:
        b.isGoal() == false
    }

    def "isGoalPositive"(){
        when:
        def blocks = [[1,2,3],[4,5,6],[7,8,0]] as int[][]
        def b = new Board(blocks)
        then:
        b.isGoal() == true
    }

    def "dimension"(){
        when:
        def blocks = [[1,2,3],[4,5,6],[7,8,0]] as int[][]
        def b = new Board(blocks)
        then:
        b.dimension()==3
    }
    def "hamming0"(){
        when:
        def blocks = [[1,2,3],[4,5,6],[7,8,0]] as int[][]
        def b = new Board(blocks)
        then:
        b.hamming()==0
    }
    def "hamming2"(){
        when:
        def blocks = [[1,2,3],[4,5,6],[7,0,8]] as int[][]
        def b = new Board(blocks)
        then:
        b.hamming()==1
    }
    def "hamming6"(){
        when:
        def blocks = [[0,1,3],[4,2,5],[7,6,8]] as int[][]
        def b = new Board(blocks)
        then:
        b.hamming()==5
    }

    def "hamming5"(){
        when:
        def blocks = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def b = new Board(blocks)
        then:
        b.hamming()==5
    }

    def "manhattan2"(){
        when:
        def blocks = [[0,1],[2,3]] as int[][]
        def b = new Board(blocks)
        then:
        b.manhattan()==4
    }

    def "manhattan3"(){
        when:
        def blocks = [[5,8,7],[1,0,6],[3,4,2]] as int[][]
        def b = new Board(blocks)
        then:
        b.manhattan()==18
    }


    /* can't be tested since it's private
    def "manhattanDistance1"(){
        when:
        def blocks = [[0,1,3],[4,2,5],[7,6,8]] as int[][]
        def b = new Board(blocks)
        then:
        b.manhattanDistance(0,1)==1
    }

    def "manhattanDistance2"(){
        when:
        def blocks = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def b = new Board(blocks)
        then:
        b.manhattanDistance(1,2)==2
    }
    def "findIndex"(){
        when:
        def blocks = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def b = new Board(blocks)
        then:
        b.findIndex(8)==0
        b.findIndex(4)==3
    }
    def "findEmptyIndex"(){
        when:
        def blocks = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def b = new Board(blocks)
        then:
        b.findEmptyIndex()==4
    }
    */

    def "manhattanMetric1"(){
        when:
        def blocks = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def b = new Board(blocks)
        then:
        b.manhattan()==10
    }
    def "equalsPositive"(){
        when:
        def blocks1 = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def blocks2 = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def b1 = new Board(blocks1)
        def b2 = new Board(blocks2)
        then:
        b1.equals(b2)==true
    }
    def "equalsNegative"(){
        when:
        def blocks1 = [[8,1,3],[4,0,2],[7,5,6]] as int[][]
        def blocks2 = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def b1 = new Board(blocks1)
        def b2 = new Board(blocks2)
        then:
        b1.equals(b2)==false
    }
    def "neighbors"(){
        when:
        def blocks = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def b = new Board(blocks)
        then:
        b.neighbors().size() ==4
    }
    def "twin"(){
        when:
        def blocks = [[8,1,3],[4,0,2],[7,6,5]] as int[][]
        def b = new Board(blocks)
        then:
        b.twin() != null
    }
}
