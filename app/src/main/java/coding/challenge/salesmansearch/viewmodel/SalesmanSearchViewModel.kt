package coding.challenge.salesmansearch.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coding.challenge.salesmansearch.model.Salesman
import coding.challenge.salesmansearch.model.allSampleSalesman
import coding.challenge.salesmansearch.model.repositories.FakeSalesmanRepository
import coding.challenge.salesmansearch.model.repositories.SalesmanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn

class SalesmanSearchViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val expandedMap = mutableStateMapOf<Salesman, Boolean>()

    //TODO remove FakeSalesmanRepository as soon as real one is implemented
    private val salesmanRepository: SalesmanRepository = FakeSalesmanRepository()


    private val _salesmen = MutableStateFlow(listOf<Salesman>())
    val salesmen = _searchText
        .debounce(1000L)
        .combine(_salesmen) { text, _ ->
            if (text.isBlank()) {
                //empty list if search field is empty
                listOf()
            } else {
                salesmanRepository.getSalesman(text)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed((5000)),
            _salesmen.value
        )


    fun onCardClicked(salesman: Salesman) {
        //flip current value
        expandedMap[salesman] = expandedMap[salesman] == null || expandedMap[salesman] == false
    }

    fun onSearchTextChanged(text: String) {
        _searchText.value = text
        expandedMap.clear()
    }

}