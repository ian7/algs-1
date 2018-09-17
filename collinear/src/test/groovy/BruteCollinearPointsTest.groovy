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
    }

    def "Segments"() {
    }
}
