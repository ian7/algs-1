import spock.lang.Specification

class BruteCollinearPointsTest extends Specification {
    def "ConstructorNullArg"(){
        when:
        new BruteCollinearPoints(null)
        then:
        thrown IllegalArgumentException
    }

    def "ConstructorNullPoint"(){
        given:
        def p1 = new Point(0,0)
        def Point[] ps1 = [p1,null]
        when:
        new BruteCollinearPoints(ps1)
        then:
        thrown IllegalArgumentException
    }

    def "ConstructorDuplicatePoints"(){
        given:
        def p1 = new Point(0,0)
        def Point[] ps1 = [p1,p1]
        when:
        new BruteCollinearPoints(ps1)
        then:
        thrown IllegalArgumentException
    }


    def "NumberOfSegments"() {
        given:
        def p1 = new Point(0,0)
        def p2 = new Point(1,1)
        def p3 = new Point(2,2)
        def p4 = new Point(3,3)
        def Point[] ps1 = [p1,p2,p3,p4]
        when:
        def bcp = new BruteCollinearPoints(ps1)
        then:
        bcp.segments().size()==1
    }

    def "SegmentsCount1"() {
        given:
        def p1 = new Point(0,0)
        def p2 = new Point(1,1)
        def p3 = new Point(2,2)
        def p4 = new Point(3,3)
        def Point[] ps1 = [p1,p2,p3,p4]
        when:
        def bcp = new BruteCollinearPoints(ps1)
        then:
        bcp.numberOfSegments() == 1
    }
    def "SegmentsCount0"() {
        given:
        def p1 = new Point(0,1)
        def p2 = new Point(1,1)
        def p3 = new Point(2,2)
        def p4 = new Point(3,3)
        def Point[] ps1 = [p1,p2,p3,p4]
        when:
        def bcp = new BruteCollinearPoints(ps1)
        then:
        bcp.numberOfSegments() == 0
    }
    // not sure how to test segments now...
}
