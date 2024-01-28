package coding.challenge.salesmansearch

import coding.challenge.salesmansearch.model.Salesman
import coding.challenge.salesmansearch.model.repositories.FakeSalesmanRepository
import org.junit.Test

import org.junit.Assert.*

class FakeSalesmanRepositoryTest {

    private val salesmanRepository = FakeSalesmanRepository()
    @Test
    fun queryWithoutAsterisk() {

        var resultList = salesmanRepository.getSalesman("76133")
        assertTrue(resultList.contains(Salesman("Artem Titarenko", setOf("76133"))))

        resultList = salesmanRepository.getSalesman("76199")
        assertTrue(resultList.contains(Salesman("Bernd Schmitt", setOf("7619*"))))

        resultList = salesmanRepository.getSalesman("99999")
        assertTrue(resultList.isEmpty())

        resultList = salesmanRepository.getSalesman("")
        assertTrue(resultList.isEmpty())
    }

    @Test
    fun queryWithAsterisk() {

        //only asterisk matches all salesman
        var resultList = salesmanRepository.getSalesman("*")
        assertEquals(resultList.size, 4)

        //the postcode of 3 salesman start with 76
        resultList = salesmanRepository.getSalesman("76*")
        assertEquals(resultList.size, 3)

        //the postcode of only one salesman start with 8
        resultList = salesmanRepository.getSalesman("8*")
        assertEquals(resultList.size, 1)

        //no postcode starts with 7614, so result should be an empty list
        resultList = salesmanRepository.getSalesman("7614*")
        assertEquals(resultList.size, 0)


    }


}