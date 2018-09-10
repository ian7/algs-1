import spock.lang.Shared
import spock.lang.Specification

class RandomizedQueueTest extends Specification {
    @Shared
    def rq = new RandomizedQueue()

    def "initially size zero"() {
        expect:
        rq.size() == 0
    }

    def "initially empty"() {
        expect:
        rq.isEmpty() == true;
    }

    def "enqueue null arg"() {
        when:
        rq.enqueue(null)
        then:
        thrown(IllegalArgumentException)
    }

    def "failRemoveFirstIfEmpty"() {
        given:
        def rq2 = new Deque()
        when:
        rq2.removeFirst()
        then:
        thrown(NoSuchElementException)
    }

    def "failRemoveLastIfEmpty"() {
        given:
        def rq2 = new Deque()
        when:
        rq2.removeLast()
        then:
        thrown(NoSuchElementException)
    }

    def "failNextIfLast"() {
        given:
        def i1 = rq.iterator()
        when:
        i1.next()
        then:
        thrown(NoSuchElementException)
    }

    def "failIteratorRemove"() {
        given:
        def i1 = rq.iterator()
        when:
        i1.remove()
        then:
        thrown(UnsupportedOperationException)
    }

    def "queueElement"() {
        given:
        def rq2 = new RandomizedQueue<Integer>()
        rq2.enqueue(new Integer(1))
        expect:
        rq2.size() == 1
    }

    def "queueThreeElements"() {
        given:
        def rq2 = new RandomizedQueue<Integer>()
        rq2.enqueue(new Integer(1))
        rq2.enqueue(new Integer(2))
        rq2.enqueue(new Integer(3))
        expect:
        rq2.size() == 3
    }

}
