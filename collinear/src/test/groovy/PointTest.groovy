import spock.lang.Specification

class PointTest extends Specification
{
    def "CompareBigger"(){
        given:
        def p1 = new Point(1,2);
        def p2 = new Point( 2,2);
        expect:
        p1.compareTo(p2) == -1
    }
    def "CompareSmaller"(){
        given:
        def p1 = new Point(2,2);
        def p2 = new Point( 1,2);
        expect:
        p1.compareTo(p2) == 1
    }
    def "CompareBiggerX"(){
        given:
        def p1 = new Point(1,2);
        def p2 = new Point( 1,3);
        expect:
        p1.compareTo(p2) == -1
    }
    def "CompareSmallerY"(){
        given:
        def p1 = new Point(1,3);
        def p2 = new Point( 1,2);
        expect:
        p1.compareTo(p2) == 1
    }
    def "CompareEqual"(){
        given:
        def p1 = new Point(1,2);
        def p2 = new Point( 1,2);
        expect:
        p1.compareTo(p2) == 0
    }

}
