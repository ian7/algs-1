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
        brt.set(1,"a");
        then:
        brt.size() == 1
        brt.get(1) == "a"
    }

    def "adding three nodes"(){
        given:
        def brt = new BRT<Integer, String>()
        brt.set(1,"a")
        brt.set(2,"b")
        brt.set(3,"c")
        expect:
        brt.size() == 3
        brt.get(1)=="a"
        brt.get(2)=="b"
        brt.get(3)=="c"
        brt.size()==3
        brt.maxDepth() == 3
    }

    def "adding 100 sequential elements"(){
        given:
        def brt = new BRT<Integer, String>()
        def size = 100
        for( int i=0; i<size; i++ ){
            brt.set(i,"a")
        }
        expect:
        brt.size() == size
        brt.maxDepth() == size
    }


    def "adding 1k random elements"(){
        given:
        def brt = new BRT<Integer, String>()
        def r = new Random()
        brt.set(3,"a")
        10000000.times{
            brt.set(r.nextInt(),"a")
        }
        expect:
        brt.contains(3)
    }
}
