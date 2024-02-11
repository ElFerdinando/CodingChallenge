package coding.challenge.salesmansearch.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coding.challenge.salesmansearch.R
import coding.challenge.salesmansearch.model.Salesman
import coding.challenge.salesmansearch.view.theme.SalesmanSearchTheme
import coding.challenge.salesmansearch.view.theme.Typography
import coding.challenge.salesmansearch.view.theme.salesman_grey
import coding.challenge.salesmansearch.view.theme.salesman_lightGrey

const val EXPAND_ANIMATION_DURATION = 300
@Composable
fun SalesmanCard(
    salesman: Salesman,
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onCardClicked: (salesman: Salesman) -> Unit = {}
) {
    val transition = updateTransition(targetState = expanded, label = "cardExpandTransition")

    val iconRotationDeg by transition.animateFloat(label = "icon rotation") { state ->
        if(state){
            0f
        }else{
            90f
        }
    }

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(EXPAND_ANIMATION_DURATION)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(EXPAND_ANIMATION_DURATION)
        )
    }

    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(EXPAND_ANIMATION_DURATION)
        ) + fadeOut(
            animationSpec = tween(EXPAND_ANIMATION_DURATION)
        )
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .then(modifier)
            .clickable { onCardClicked(salesman) },
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(salesman_lightGrey)
        )
        {
            val initial = if (salesman.name.isBlank()) ""
            else salesman.name.first().toString()
            Text(
                text = initial,
                style = Typography.labelMedium,
            )
        }

        Column(
            modifier = Modifier
                .weight(1F)
        )
        {
            Text(
                text = salesman.name,
                style = Typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
            AnimatedVisibility(
                visible = expanded,
                enter = enterTransition,
                exit = exitTransition
            ) {
                val commaSeparatedAreas = salesman.areas.joinToString(separator = ", ")
                Text(
                    text = commaSeparatedAreas,
                    style = Typography.bodyMedium,
                    modifier = Modifier.padding(8.dp),
                    color = salesman_grey
                )
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = null,
            tint = salesman_grey,
            modifier = Modifier
                .size(22.dp)
                .rotate(iconRotationDeg)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun SalesmanCardPreview() {
    SalesmanSearchTheme {
        Column {
            SalesmanCard(
                Salesman(name = "Fernando Pastor Castro", areas = setOf("76133", "76185")),
                expanded = true
            )
            SalesmanCard(Salesman(name = "Lars de Bel", areas = setOf("7619*")), expanded = false)
            SalesmanCard(Salesman(name = "", areas = setOf("")), expanded = false)
        }
    }
}