import spock.lang.Specification

class CircularSuffixArrayTest extends Specification {
    def "abracadabra"(){
        when:
        def sa = new CircularSuffixArray("ABRACADABRA!");
        then:
        sa.index(3)==0
    }
}
