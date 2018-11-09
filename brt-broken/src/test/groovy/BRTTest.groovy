import spock.lang.Specification

class BRTTest extends Specification {
    def "simple construction"(){
        when:
        def brt = new BRT<Integer, String>()
        then:
        brt.size() == 0
    }
}
