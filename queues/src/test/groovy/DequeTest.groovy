import spock.lang.Specification

class DequeTest extends Specification {
    def "initially empty"() {
        when:
        def d1 = new Deque()
        then:
        d1.size() == 0
    }
}
