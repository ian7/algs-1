import edu.princeton.cs.algs4.Point2D
import spock.lang.Specification


class PointSETTest extends Specification {
    def "create empty"() {
        when:
        def s = new PointSET()
        then:
        s.isEmpty() == true
    }

    def "add one point"() {
        given:
        def s = new PointSET()
        def p = new Point2D(1, 2)

        when:
        s.insert(p)
        then:
        s.isEmpty() == false
        s.size() == 1
    }
    def "add two points"() {
        given:
        def s = new PointSET()
        def p1 = new Point2D(1, 2)
        def p2 = new Point2D(2, 2)

        when:
        s.insert(p1)
        s.insert(p2)
        then:
        s.isEmpty() == false
        s.size() == 2
    }
    def "check contains"() {
        given:
        def s = new PointSET()
        def p1 = new Point2D(1, 2)
        def p2 = new Point2D(2, 2)

        when:
        s.insert(p1)
        s.insert(p2)
        then:
        s.contains(p1) == true
        s.contains(p2) == true
    }
}
