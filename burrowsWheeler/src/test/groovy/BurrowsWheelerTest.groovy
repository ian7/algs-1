import spock.lang.Specification

class BurrowsWheelerTest extends Specification {
    def "abracadabra"(){
        expect:
        BurrowsWheeler.transform()
    }
    def "find array of next"(){
        expect:
        BurrowsWheeler.findNext(3,(char[])String.valueOf("ARD!RCAAAABB").getBytes()) == [3,0,6,7,8,9,10,11,5,2,1,4]
    }
    def "inverseTransform"(){
        expect:
        BurrowsWheeler.inverseTransform(3,(char[])String.valueOf("ARD!RCAAAABB").getBytes()) == "ABRACADABRA!"
    }
}
