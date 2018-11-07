import spock.lang.Specification

class MoveToFrontTest extends Specification {
    def "move"(){
        when:
        def mtf = new MoveToFront()
        then:
        mtf.move('a' as char)==158
        mtf.move('a' as char)==0
        mtf.move('a' as char)==0
        mtf.move('b' as char)==158
        mtf.move('b' as char)==0
        mtf.move('a' as char)==1
    }
    def "deMove"(){
        when:
        def mtf = new MoveToFront()
        then:
        mtf.deMove(158)=='a' as char
        mtf.deMove(0)=='a' as char
        mtf.deMove(0)=='a' as char
        mtf.deMove(158)=='b' as char
        mtf.deMove(0)=='b' as char
        mtf.deMove(1)=='a' as char
    }
}
