import spock.lang.Specification

class WordNetTest extends Specification {
    def "faulty construction"(){
        when:
        def wn = new WordNet(null,"abc")
        then:
        thrown IllegalArgumentException
    }

    def "faulty construction2"(){
        when:
        def wn = new WordNet("abc",null)
        then:
        thrown IllegalArgumentException
    }

    def "file loading"(){
        given:
        def wn = new WordNet("synsets.txt","hypernyms.txt")
        expect:
        wn.nounHash.size() > 0
    }
    def "distance"(){
        given:
        def wn = new WordNet("synsets.txt","hypernyms.txt")
        expect:
        wn.distance("optimist","Crocodylia") == 11
    }
    def "distance2"(){
        given:
        def wn = new WordNet("synsets.txt","hypernyms.txt")
        expect:
        wn.distance("whole unit","person individual someone somebody mortal soul") == 3
    }
    def "distance3"(){
        given:
        def wn = new WordNet("synsets.txt","hypernyms.txt")
        expect:
        wn.distance("Carlos_Fuentes","rocker") == 6
    }
}
