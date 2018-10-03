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
        sc.removeHorizontalSeam(null)
        then:
        thrown IllegalArgumentException
    }
    def "faulty removeVerticalSeam"(){
        when:
        def sc = new SeamCarver(new Picture(3,3))
        sc.removeVerticalSeam(null)
        then:
        thrown IllegalArgumentException
    }
    def "load from file 3x4.png"(){
        when:
        def sc = new SeamCarver(new Picture("3x4.png"))
        then:
        sc.width()==3
        sc.height()==4
    }
    def "edge gradient 1000"(){
        when:
        def sc = new SeamCarver(new Picture("3x4.png"))
        then:
        sc.energy(0,0) == 1000d
        sc.energy(0,1) == 1000d
        sc.energy(0,2) == 1000d
        sc.energy(0,3) == 1000d
        sc.energy(2,0) == 1000d
        sc.energy(2,1) == 1000d
        sc.energy(2,2) == 1000d
        sc.energy(2,3) == 1000d
        sc.energy(1,0) == 1000d
        sc.energy(1,3) == 1000d
    }
    def "gradientX"(){
        when:
        def sc = new SeamCarver(new Picture("3x4.png"))
        then:
        sc.gradientX(1,2) == 41620d
    }
    def "gradientY"(){
        when:
        def sc = new SeamCarver(new Picture("3x4.png"))
        then:
        sc.gradientY(1,2) == 10404d
    }
    def "non-trivial gradient"(){
        when:
        def sc = new SeamCarver(new Picture("3x4.png"))
        then:
        sc.energy(1,2) == Math.sqrt(52024d)
        sc.energy(1,1) == Math.sqrt(52225d)
    }
    def "vertical seam"(){
        when:
        def sc = new SeamCarver(new Picture("6x5.png"))
        then:
        sc.findVerticalSeam() == [4,4,3,2,1] as int[]
    }

}
