import edu.princeton.cs.algs4.Picture
import spock.lang.Specification

class SeamCarverTest extends Specification {
    def "faulty construction"(){
        when:
        def sc = new SeamCarver(null)
        then:
        thrown IllegalArgumentException
    }
    def "faulty removeHorizontalSeam"(){
        when:
        def sc = new SeamCarver(new Picture(3,3))
        sc.removeHorizontalSeam(null);
        then:
        thrown IllegalArgumentException
    }
    def "faulty removeVerticalSeam"(){
        when:
        def sc = new SeamCarver(new Picture(3,3))
        sc.removeVerticalSeam(null);
        then:
        thrown IllegalArgumentException
    }
}
