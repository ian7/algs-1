import spock.lang.Specification

class BRTTest extends Specification {
    def "simple construction"(){
        when:
        def brt = new BRT<Integer, String>()
        then:
        brt.size() == 0
    }
    def "adding one node"(){
        given:
        def brt = new BRT<Integer, String>()
        when:
        brt.add(1,"a");
        then:
        brt.size() == 1
        brt.get(1) == "a"
    }

    def "adding three nodes"(){
        given:
        def brt = new BRT<Integer, String>()
        brt.add(1,"a")
        brt.add(2,"b")
        brt.add(3,"c")
        expect:
        brt.size() == 3
        brt.get(1)=="a"
        brt.get(2)=="b"
        brt.get(3)=="c"
    }
}
