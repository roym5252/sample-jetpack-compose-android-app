package com.jlp.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.sp
import com.jlp.core.R

@Composable
fun ErrorMessageAndReload(message: String) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column {

            Text(
                text = message,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_light)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),

                    ),
                modifier = Modifier.testTag("errorMessage")
            )

            Image(painter = painterResource(id = androidx.core.R.drawable.ic_call_answer), modifier = Modifier.testTag("reloadIcon"),contentDescription = "Reload product")
        }
    }

}