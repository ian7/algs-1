import edu.princeton.cs.algs4.Point2D
import edu.princeton.cs.algs4.RectHV
import spock.lang.Specification

class KdTreeTest extends Specification {
    def "create empty"() {
        when:
        def s = new KdTree()
        then:
        s.isEmpty() == true
    }

    def "add one point"() {
        given:
        def s = new KdTree()
        def p = new Point2D(1, 2)

        when:
        s.insert(p)
        then:
        s.isEmpty() == false
        s.size() == 1
    }

    def "add one point twice"() {
        given:
        def s = new KdTree()
        def p = new Point2D(1, 2)

        when:
        s.insert(p)
        s.insert(p)
        then:
        s.isEmpty() == false
        s.size() == 1
    }

    def "add two points"() {
        given:
        def s = new KdTree()
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
        def s = new KdTree()
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
        def s = new KdTree()
        def p1 = new Point2D(0, 0)
        def p2 = new Point2D(0.3, 0.3)
        def p3 = new Point2D(0, 0.3)
        def p4 = new Point2D(0.3, 0)
        def p5 = new Point2D(0.2, 0.0)
        def p6 = new Point2D(0, 0.2)
        def p7 = new Point2D(0.2, 0.2)

        when:
        s.insert(p1)
        s.insert(p2)
        s.insert(p3)
        s.insert(p4)
        s.insert(p5)
        s.insert(p6)
        s.insert(p7)

        then:
        s.range(new RectHV(0.1, 0.1, 0.2, 0.2)).size() == 1
        s.range(new RectHV(0.1, 0.1, 0.2, 0.2)).iterator().next() == p7
    }

    def "check nearest"() {
        given:
        def t = new KdTree()
        def p1 = new Point2D(0.7, 0.2)
        def p2 = new Point2D(0.5, 0.4)
        def p3 = new Point2D(0.2, 0.3)
        def p4 = new Point2D(0.4, 0.7)
        def p5 = new Point2D(0.9, 0.6)
        t.insert(p1)
        t.insert(p2)
        t.insert(p3)
        t.insert(p4)
        t.insert(p5)

        expect:
        t.nearest(new Point2D(x, y)) == new Point2D(a, b)

        where:
        x     | y     | a   | b
        0.535 | 0.431 | 0.5 | 0.4
        0.1   | 0.1   | 0.2 | 0.3
        0.9   | 0.1   | 0.7 | 0.2
        0.1   | 0.9   | 0.4 | 0.7
        0.9   | 0.9   | 0.9 | 0.6
    }

    def "check nearest 2"() {
        given:
        def t = new KdTree()
        def p1 = new Point2D(0.875, 0.625)
        def p2 = new Point2D(0.5, 1)
        def p3 = new Point2D(0.0, 0.125)
        def p4 = new Point2D(0.75, 0.375)
        def p5 = new Point2D(0.125, 0.875)
        t.insert(p1)
        t.insert(p2)
        t.insert(p3)
        t.insert(p4)
        t.insert(p5)

        expect:
        t.nearest(new Point2D(x, y)) == new Point2D(a, b)

        where:
        x     | y | a    | b
        0.625 | 0 | 0.75 | 0.375
    }


    def "get root rectangle"() {
        given:
        def t = new KdTree()
        def p1 = new Point2D(0.2, 0.2)
        t.insert(p1)
        expect:
        t.getRoot().getRectangle().equals(new RectHV(0, 0, 1, 1))
        t.getRoot().isHorizontal() == false
    }

    def "get left rectangle"() {
        given:
        def t = new KdTree()
        def p1 = new Point2D(0.2, 0.2)
        def p2 = new Point2D(0.1, 0.1)
        t.insert(p1)
        t.insert(p2)
        expect:
        t.getRoot().left != null
        t.getRoot().right == null
        t.getRoot().left.getRectangle().equals(new RectHV(0, 0, 0.2, 1))
    }

    def "get right rectangle"() {
        given:
        def t = new KdTree()
        def p1 = new Point2D(0.2, 0.2)
        def p2 = new Point2D(0.3, 0.1)
        t.insert(p1)
        t.insert(p2)
        expect:
        t.getRoot().left == null
        t.getRoot().right != null
        t.getRoot().right.getRectangle().equals(new RectHV(0.2, 0, 1, 1))
    }

    def "get bottom rectangle"() {
        given:
        def t = new KdTree()
        def p1 = new Point2D(0.7, 0.2)
        def p2 = new Point2D(0.5, 0.4)
        def p3 = new Point2D(0.2, 0.3)
        t.insert(p1)
        t.insert(p2)
        t.insert(p3)
        expect:
        t.getRoot().left != null
        t.getRoot().right == null
        t.getRoot().left.left != null
        t.getRoot().left.left.getRectangle().equals(new RectHV(0.0, 0, 0.7, 0.4))
    }

    def "get top rectangle"() {
        given:
        def t = new KdTree()
        def p1 = new Point2D(0.7, 0.2)
        def p2 = new Point2D(0.5, 0.4)
        def p3 = new Point2D(0.2, 0.3)
        def p4 = new Point2D(0.4, 0.7)
        t.insert(p1)
        t.insert(p2)
        t.insert(p3)
        t.insert(p4)
        expect:
        t.getRoot().left != null
        t.getRoot().right == null
        t.getRoot().left.left != null
        t.getRoot().left.right != null
        t.getRoot().left.right.getRectangle().equals(new RectHV(0.0, 0.4, 0.7, 1))
    }


}
