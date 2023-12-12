package com.jlp.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jlp.core.R

@Composable
fun InfoMessageAndReload(message: String, onClickEvent: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onClickEvent, onClickLabel = message),
        contentAlignment = Alignment.Center
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.testTag("reloadViewHolder")
        ) {

            Text(
                text = message,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),

                    ),
                modifier = Modifier
                    .testTag("errorMessage")
                    .clickable(onClick = onClickEvent)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_refresh),
                modifier = Modifier
                    .testTag("reloadIcon")
                    .padding(8.dp)
                    .clickable(onClick = onClickEvent),
                contentDescription = "Reload product"
            )
        }
    }

}