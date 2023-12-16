import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jlp.core.R
import com.jlp.core.ui.theme.CustomColor

const val MINIMIZED_MAX_LINES = 3

@Composable
fun ExpandingText(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    isExpanded: MutableState<Boolean>,
    isClickable: MutableState<Boolean>
) {

    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val finalText = remember { mutableStateOf(text) }

    val textLayoutResult = textLayoutResultState.value
    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded.value -> {
                finalText.value = buildAnnotatedString {
                    append("$text")
                }
            }

            !isExpanded.value && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(MINIMIZED_MAX_LINES - 1)
                val showMoreString = "..."
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                finalText.value = buildAnnotatedString {
                    append(adjustedText + showMoreString)
                }

                isClickable.value = true
            }
        }
    }

    /*Text(
        text = finalText,
        maxLines = if (isExpanded) Int.MAX_VALUE else 3,
        onTextLayout = {

        },
        modifier = modifier
            .clickable(enabled = isClickable) { isExpanded = !isExpanded }
            .animateContentSize(),
    )*/

    Column {

        Text(text = text,
            maxLines = if (isExpanded.value) Int.MAX_VALUE else 3,
            onTextLayout = {
                textLayoutResultState.value = it
            },
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_light)),
            modifier = modifier
                .clickable(enabled = isClickable.value) { isExpanded.value = !isExpanded.value }
                .animateContentSize())

        if (isExpanded.value) {
            ExpandedTextShowState(isExpanded, "Read less", "readLess")
        } else {
            ExpandedTextShowState(isExpanded, "Read more", "readMore")
        }
    }
}

@Composable
fun ExpandedTextShowState(isExpanded: MutableState<Boolean>, text: String, testTag: String) {

    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .clickable(onClick = {
            isExpanded.value = !isExpanded.value
        }, onClickLabel = "Click to $text")) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Box(
                modifier = Modifier
                    .height(2.dp)
                    .padding(start = 8.dp)
                    .background(CustomColor.Grey)
                    .fillMaxWidth()
            )

            Text(
                text = text,
                modifier = Modifier
                    .testTag(testTag)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .semantics { heading() },
                fontSize = 20.sp,
                fontWeight = FontWeight(800),
                fontFamily = FontFamily(Font(R.font.montserrat_medium))
            )


            Box(
                modifier = Modifier
                    .height(2.dp)
                    .padding(start = 8.dp)
                    .background(CustomColor.Grey)
                    .fillMaxWidth()
            )
        }
    }
}