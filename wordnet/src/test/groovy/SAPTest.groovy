import edu.princeton.cs.algs4.Digraph
import spock.lang.Shared
import spock.lang.Specification

class SAPTest extends Specification {
    @Shared sap
    @Shared sap2

    def setup() {
        def g = new Digraph(25)
        g.addEdge(13, 7)
        g.addEdge(14, 7)
        g.addEdge(7, 3)
        g.addEdge(3, 1)
        g.addEdge(1, 0)
        g.addEdge(2, 0)
        g.addEdge(8, 3)
        g.addEdge(9, 3)
        g.addEdge(4, 1)
        g.addEdge(6, 2)
        g.addEdge(5, 2)
        g.addEdge(10, 5)
        g.addEdge(17, 10)
        g.addEdge(18, 10)
        g.addEdge(11, 5)
        g.addEdge(12, 5)
        g.addEdge(19, 12)
        g.addEdge(20, 12)
        g.addEdge(24, 20)
        g.addEdge(23, 20)
        g.addEdge(15, 9)
        g.addEdge(16, 9)
        g.addEdge(21, 16)
        g.addEdge(22, 16)
        def g2 = new Digraph(15)
        g2.addEdge(1,2)
        g2.addEdge(2,3)
        g2.addEdge(3,4)
        g2.addEdge(5,6)
        g2.addEdge(6,1)
        g2.addEdge(7,8)
        g2.addEdge(8,9)
        g2.addEdge(9,10)
        g2.addEdge(10,11)
        g2.addEdge(11,12)
        g2.addEdge(12,8)
        g2.addEdge(13,14)
        g2.addEdge(14,0)
        g2.addEdge(0,11)
        sap = new SAP(g)
        sap2 = new SAP(g2)
    }

    def "individual ancestors"() {
        expect:
        sap.ancestor(13, 4) == 1
        sap.ancestor(13, 2) == 0
        sap.ancestor(13, 16) == 3
        sap.ancestor(17, 24) == 5
    }
    def "individual length"() {
        expect:
        sap.length(13, 16) == 4
    }
    def "any ancestor"() {
        given:
        def left = [13,23,24]
        def right = [6,16,17]
        expect:
        sap.ancestor(left, right) == 3
    }
    def "any length"() {
        given:
        def left = [13,23,24]
        def right = [6,16,17]
        expect:
        sap.length(left, right) == 4
    }
    def "individual length2"() {
        expect:
        sap2.length(7, 14) == 5
    }
    def "individual length3"() {
        expect:
        sap2.length(10, 7) == 3
    }

}
