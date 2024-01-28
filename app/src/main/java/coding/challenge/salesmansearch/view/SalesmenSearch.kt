package coding.challenge.salesmansearch.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coding.challenge.salesmansearch.model.Salesman
import coding.challenge.salesmansearch.view.theme.SalesmanSearchTheme
import coding.challenge.salesmansearch.view.theme.salesman_lightGrey
import coding.challenge.salesmansearch.viewmodel.SalesmanSearchViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coding.challenge.salesmansearch.R
import coding.challenge.salesmansearch.view.theme.salesman_grey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesmanSearch(viewModel: SalesmanSearchViewModel = viewModel()) {
    val searchText by viewModel.searchText.collectAsState()
    val salesmen by viewModel.salesmen.collectAsState()
    val expandedMap = remember { viewModel.expandedMap }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChanged,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_field_placeholder)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                    tint = salesman_grey
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_action_mic),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable {
                            //TODO implement Mic support
                        },
                    tint = salesman_grey
                )
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),

        )

        Spacer(modifier = Modifier.height(16.dp))

        SalesmanCardList(
            salesmen = salesmen,
            expandedMap = expandedMap,
            onCardClicked = viewModel::onCardClicked
        )
    }

}

@Preview(showBackground = true)
@Composable
fun SalesmanSearchPreview() {
    SalesmanSearchTheme {
        SalesmanSearch()
    }
}
