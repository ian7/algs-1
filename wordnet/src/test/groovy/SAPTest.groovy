import edu.princeton.cs.algs4.Digraph
import spock.lang.Specification

class SAPTest extends Specification {
    def "construction"(){
        given:
        def g = new Digraph(25)
        g.addEdge(13,7)
        g.addEdge(14,7)
        g.addEdge(7,3)
        g.addEdge(3,1)
        g.addEdge(1,0)
        g.addEdge(2,0)
        g.addEdge(8,3)
        g.addEdge(9,3)
        g.addEdge(4,1)
        g.addEdge(6,2)
        g.addEdge(5,2)
        g.addEdge(10,5)
        g.addEdge(17,10)
        g.addEdge(18,10)
        g.addEdge(11,5)
        g.addEdge(12,5)
        g.addEdge(19,12)
        g.addEdge(20,12)
        g.addEdge(24,20)
        g.addEdge(23,20)
        g.addEdge(15,9)
        g.addEdge(16,9)
        g.addEdge(21,16)
        g.addEdge(22,16)
        def sap = new SAP(g)
        expect:
        sap.ancestor(13,2) == 0
        sap.ancestor(13,4) == 1
        sap.ancestor(13,16) == 3
        sap.ancestor(17,24) == 5
    }
}
