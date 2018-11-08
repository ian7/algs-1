import spock.lang.Specification

class BurrowsWheelerTest extends Specification {
    def "find array of next"(){
        expect:
        BurrowsWheeler.findNext((char[])String.valueOf("ARD!RCAAAABB").getBytes()) == [3,0,6,7,8,9,10,11,5,2,1,4]
    }
    def "transform"(){
        given:
        def s = "ABRACADABRA!" as String
        def csa = new CircularSuffixArray(s)
        expect:
        BurrowsWheeler.getT(s,csa) == "ARD!RCAAAABB"
    }
    def "inverseTransform"(){
        expect:
        BurrowsWheeler.inverseTransform(3,(char[])String.valueOf("ARD!RCAAAABB").getBytes()) == "ABRACADABRA!"
    }
    def "bucketSort"(){
        given:
        def s = "ABRACADABRA!"
        def c = s.toCharArray()
        when:
        def output = new String(BurrowsWheeler.bucketSort(c));
        then:
        output == "!AAAAABBCDRR"
    }
}
