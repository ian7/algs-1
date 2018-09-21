import edu.princeton.cs.algs4.Point2D
import edu.princeton.cs.algs4.RectHV
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

    def "check rectangle"() {
        given:
        def r = new RectHV(1, 1, 2, 2)

        expect:
        def p = new Point2D(x, y)
        r.contains(p) == v

        where:
        x | y | v
        0 | 0 | false
        3 | 3 | false
        0 | 3 | false
        3 | 0 | false
        2 | 0 | false
        0 | 2 | false
        2 | 2 | true
    }

    def "check range"() {
        given:
        def s = new PointSET()
        def p1 = new Point2D(0, 0)
        def p2 = new Point2D(3, 3)
        def p3 = new Point2D(0, 3)
        def p4 = new Point2D(3, 0)
        def p5 = new Point2D(2, 0)
        def p6 = new Point2D(0, 2)
        def p7 = new Point2D(2, 2)

        when:
        s.insert(p1)
        s.insert(p2)
        s.insert(p3)
        s.insert(p4)
        s.insert(p5)
        s.insert(p6)
        s.insert(p7)

        then:
        s.range(new RectHV(1, 1, 2, 2)).size() == 1
        s.range(new RectHV(1, 1, 2, 2)).iterator().next() == p7
    }

    def "check nearest"() {
        given:
        def s = new PointSET()
        def p1 = new Point2D(0, 0)
        def p2 = new Point2D(3, 3)
        def p3 = new Point2D(0, 3)
        def p4 = new Point2D(3, 0)
        def p5 = new Point2D(2, 0)
        def p6 = new Point2D(0, 2)
        def p7 = new Point2D(2, 2)

        when:
        s.insert(p1)
        s.insert(p2)
        s.insert(p3)
        s.insert(p4)
        s.insert(p5)
        s.insert(p6)
        s.insert(p7)

        then:
        s.nearest(new Point2D(x, y)) == new Point2D(a, b)

        where:
        x   | y   | a | b
        0   | 0.5 | 0 | 0
        10  | 10  | 3 | 3
        2.1 | 2.3 | 2 | 2
    }

}
