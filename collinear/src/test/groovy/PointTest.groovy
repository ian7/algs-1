import spock.lang.Specification

class PointTest extends Specification
{
    def "CompareBigger"(){
        given:
        def p1 = new Point(1,2)
        def p2 = new Point( 2,2)
        expect:
        p1.compareTo(p2) == -1
    }
    def "CompareSmaller"(){
        given:
        def p1 = new Point(2,2)
        def p2 = new Point( 1,2)
        expect:
        p1.compareTo(p2) == 1
    }
    def "CompareBiggerX"(){
        given:
        def p1 = new Point(1,2)
        def p2 = new Point( 1,3)
        expect:
        p1.compareTo(p2) == -1
    }
    def "CompareSmallerY"(){
        given:
        def p1 = new Point(1,3)
        def p2 = new Point( 1,2)
        expect:
        p1.compareTo(p2) == 1
    }
    def "CompareEqual"(){
        given:
        def p1 = new Point(1,2)
        def p2 = new Point( 1,2)
        expect:
        p1.compareTo(p2) == 0
    }
    def "SlopeTo1"(){
        given:
        def p1 = new Point(1,2)
        def p2 = new Point( 3,2)
        expect:
        p1.slopeTo(p2) == 0
    }
    def "SlopeToVertical"(){
        given:
        def p1 = new Point(1,2)
        def p2 = new Point( 1,3)
        expect:
        p1.slopeTo(p2) == Double.POSITIVE_INFINITY
    }
    def "SlopeToItself"(){
        given:
        def p1 = new Point(1,2)
        def p2 = new Point( 1,2)
        expect:
        p1.slopeTo(p2) == Double.NEGATIVE_INFINITY
    }
    def "SlopeTo3"(){
        given:
        def p1 = new Point(1,1)
        def p2 = new Point( 3,3)
        expect:
        p1.slopeTo(p2) == 1d
    }
    def "SlopeOrder"(){
        given:
        def p1 = new Point(0,0)
        def p2 = new Point( 3,3)
        def p3 = new Point( 2,5)
        expect:
        p1.slopeOrder().compare(p1,p2) == 1
        p1.slopeOrder().compare(p2,p1) == -1
        p1.slopeOrder().compare(p1,p1) == 0
    }
}
