package coding.challenge.salesmansearch.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coding.challenge.salesmansearch.model.Salesman
import coding.challenge.salesmansearch.view.theme.SalesmanSearchTheme
import coding.challenge.salesmansearch.view.theme.salesman_lightGrey

@Composable
fun SalesmanCardList(
    salesmen: List<Salesman>,
    expandedMap: Map<Salesman, Boolean>,
    onCardClicked: (salesman: Salesman) -> Unit = {}
) {

    LazyColumn() {
        items(salesmen) { salesman ->
            val isExpanded: Boolean = expandedMap[salesman] == true
            SalesmanCard(salesman = salesman, expanded = isExpanded, onCardClicked = onCardClicked)

            Divider(
                color = salesman_lightGrey,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(11.dp)
                    .padding(5.dp)
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SalesmanListPreview() {
    val salesman1 = Salesman("Fernando Pastor Castro", setOf("76133", "76185"))
    val salesman2 = Salesman("Lars de Bel", setOf("7619*"))
    val salesman3 = Salesman("", setOf())

    val salesmen = listOf(salesman1, salesman2, salesman3)

    val expandedMap = HashMap<Salesman, Boolean>()
    expandedMap[salesman1] = true
    expandedMap[salesman2] = false
    expandedMap[salesman3] = false

    SalesmanSearchTheme {
        SalesmanCardList(salesmen = salesmen, expandedMap = expandedMap)
    }
}