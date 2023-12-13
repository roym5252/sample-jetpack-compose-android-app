package com.jlp.featire_product_detail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jlp.core.ui.theme.CustomColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId:Long,productTitle:String?,productDetailViewModel: ProductDetailViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = productTitle ?: run { "" },style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight(800),
                        fontFamily = FontFamily(Font(com.jlp.core.R.font.montserrat_medium)),
                        color = Color(0xFF000000),

                        ), modifier = Modifier.testTag("productDetailTitle"))
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = CustomColor.Grey,
                ), modifier = Modifier.testTag("productListToolBar"),
                navigationIcon = {
                    IconButton(
                        onClick = { /* do something */ },
                        modifier = Modifier.testTag("productDetailBackButton")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->

        Box(Modifier.padding(innerPadding)) {

        }
    }
}