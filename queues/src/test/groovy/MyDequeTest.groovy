import org.spockframework.gentyref.TypeToken
import spock.lang.Shared
import spock.lang.Specification

class MyDequeTest extends Specification {
    @Shared
    def d1 = new MyDeque()

    def "initially size zero"() {
        expect:
        d1.size() == 0
    }

    def "initially empty"() {
        expect:
        d1.isEmpty() == true;
    }

    def "addFirst null arg"() {
        when:
        d1.addFirst(null)
        then:
        thrown(IllegalArgumentException)
    }

    def "addLast null arg"() {
        when:
        d1.addFirst(null)
        then:
        thrown(IllegalArgumentException)
    }

    def "failRemoveFirstIfEmpty"() {
        given:
        def d2 = new MyDeque()
        when:
        d2.removeFirst()
        then:
        thrown(NoSuchElementException)
    }

    def "failRemoveLastIfEmpty"() {
        given:
        def d2 = new MyDeque()
        when:
        d2.removeLast()
        then:
        thrown(NoSuchElementException)
    }

    def "failNextIfLast"() {
        given:
        def i1 = d1.iterator()
        when:
        i1.next()
        then:
        thrown(NoSuchElementException)
    }

    def "failIteratorRemove"() {
        given:
        def i1 = d1.iterator()
        when:
        i1.remove()
        then:
        thrown(UnsupportedOperationException)
    }

    def "addFist"() {
        given:
        def d2 = new MyDeque<Integer>()
        when:
        d1.addFirst(new Integer(1))
        then:
        d1.size() == 1
    }

    def "addFistTwice"() {
        given:
        def d2 = new MyDeque<Integer>()
        when:
        d2.addFirst(new Integer(1))
        d2.addFirst(new Integer(2))
        then:
        d2.size() == 2
    }

    def "addLastTwice"() {
        given:
        def d2 = new MyDeque<Integer>()
        when:
        d2.addLast(new Integer(1))
        d2.addLast(new Integer(2))
        then:
        d2.size() == 2
    }

    def "iteratorTwoEl"() {
        given:
        def d2 = new MyDeque<Integer>()
        d2.addLast(new Integer(1))
        d2.addLast(new Integer(2))
        def i2 = d2.iterator();
        expect:
        i2.hasNext() == true
        i2.next() == 1
        i2.hasNext() == true
        i2.next() == 2
        i2.hasNext() == false
    }

    def "iteratorTwoElAddFirst"() {
        given:
        def d2 = new MyDeque<Integer>()
        d2.addFirst(new Integer(1))
        d2.addFirst(new Integer(2))
        def i2 = d2.iterator();
        expect:
        i2.hasNext() == true
        i2.next() == 2
        i2.hasNext() == true
        i2.next() == 1
        i2.hasNext() == false
    }


}
