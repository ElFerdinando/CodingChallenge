package coding.challenge.salesmansearch

import coding.challenge.salesmansearch.model.Salesman
import coding.challenge.salesmansearch.viewmodel.SalesmanSearchViewModel
import kotlinx.coroutines.flow.first
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class SalesmanSearchViewModelTest {

    private var searchViewModel = SalesmanSearchViewModel()

    @Before
    fun resetViewModel(){
        searchViewModel = SalesmanSearchViewModel()
    }
    @Test
    fun searchFieldTests() {
        //searchText should empty at the beginning an
        assertTrue(searchViewModel.searchText.value.isBlank())

        //entering some text into the field should adjust viewModel Data
        searchViewModel.onSearchTextChanged("test")
        assertEquals("test", searchViewModel.searchText.value)

        //removing the searchtext should also empty the list of salesmen
        searchViewModel.onSearchTextChanged("")
        assertTrue(searchViewModel.searchText.value.isBlank())
    }

    @Test
    fun expandCheck() {
        //the card of no salesman should be expanded
        searchViewModel.expandedMap.forEach {
            assertFalse(it.value)
        }

        val salesman1 = Salesman("Artem Titarenko", setOf("76133"))
        searchViewModel.onCardClicked(salesman1)
        //now card  of salesman1 should be expanded
        assertTrue(searchViewModel.expandedMap[salesman1] == true)

        //click on the same card
        searchViewModel.onCardClicked(salesman1)
        //now card  of salesman1 should be closed
        assertTrue(searchViewModel.expandedMap[salesman1] == false)

        //let's test reopening the card
        searchViewModel.onCardClicked(salesman1)
        //now card  of salesman1 should be expanded
        assertTrue(searchViewModel.expandedMap[salesman1] == true)

        val salesman2 = Salesman("Bernd Schmitt", setOf("7619*"))

        //click on another card, this should have two cards expanded
        searchViewModel.onCardClicked(salesman2)
        //now card  of salesman1 should be expanded
        assertTrue(searchViewModel.expandedMap[salesman1] == true)
        assertTrue(searchViewModel.expandedMap[salesman2] == true)

        //click on an expanded card, this should have only close the clicked card
        searchViewModel.onCardClicked(salesman1)
        //now card  of salesman1 should be expanded
        assertTrue(searchViewModel.expandedMap[salesman1] == false)
        assertTrue(searchViewModel.expandedMap[salesman2] == true)
    }


}