package coding.challenge.salesmansearch.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coding.challenge.salesmansearch.model.Salesman
import coding.challenge.salesmansearch.model.allSalesman
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


    private val _salesmen = MutableStateFlow(allSalesman)
    val salesmen = searchText
        .debounce(1000L)
        .combine(_salesmen) { text, salesmen ->
            if (text.isBlank()) {
                salesmen
            } else {
                salesmen.filter {
                    it.doesMatchSearchQuery(text)
                }
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