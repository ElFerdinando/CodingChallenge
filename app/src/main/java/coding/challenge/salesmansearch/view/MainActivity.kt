package coding.challenge.salesmansearch.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coding.challenge.salesmansearch.R
import coding.challenge.salesmansearch.model.Salesman
import coding.challenge.salesmansearch.view.theme.SalesmanSearchTheme
import coding.challenge.salesmansearch.view.theme.Typography
import coding.challenge.salesmansearch.viewmodel.SalesmanSearchViewModel
import coding.challenge.salesmansearch.view.theme.salesman_darkBlue
import coding.challenge.salesmansearch.view.theme.salesman_lightGrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SalesmanSearchTheme {
                val viewModel = viewModel<SalesmanSearchViewModel>()
                Column(modifier = Modifier.fillMaxSize()) {
                    MyAppbar()
                    SalesmanSearch(viewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppbar() {

    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_topbar_title))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = salesman_darkBlue,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = { /*TODO go Back*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )

}


@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    SalesmanSearchTheme {
        MyAppbar()
    }
}