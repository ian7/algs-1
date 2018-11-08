import spock.lang.Specification

class MoveToFrontTest extends Specification {
    def "move"(){
        when:
        def mtf = new MoveToFront()
        then:
        mtf.move('a' as char)==97
        mtf.move('a' as char)==0
        mtf.move('a' as char)==0
        mtf.move('b' as char)==98
        mtf.move('b' as char)==0
        mtf.move('a' as char)==1
    }
    def "deMove"(){
        when:
        def mtf = new MoveToFront()
        then:
        mtf.deMove(97)=='a' as char
        mtf.deMove(0)=='a' as char
        mtf.deMove(0)=='a' as char
        mtf.deMove(98)=='b' as char
        mtf.deMove(0)=='b' as char
        mtf.deMove(1)=='a' as char
    }
}
