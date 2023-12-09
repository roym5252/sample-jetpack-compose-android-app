package com.jlp.feature_product_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.jlp.core.model.Product
import com.jlp.core.ui.theme.CustomColor
import com.jlp.feature_product_list.ui.sampledata.SampleProductProvider

@Preview
@Composable
fun ProductListScreen(@PreviewParameter(SampleProductProvider::class) products:List<Product>){
    
    Box(modifier = Modifier
        .background(color = CustomColor.Grey)
        .fillMaxSize()){

        Column {

            Text(text = "Dishwasher", modifier = Modifier.testTag("productListTitle"))

            LazyVerticalGrid(columns = GridCells.Adaptive(200.dp), Modifier.testTag("productList"), content = {

                items(products) { product ->
                    ProductGridItem(product = product)
                }
            })
        }

    }

}


