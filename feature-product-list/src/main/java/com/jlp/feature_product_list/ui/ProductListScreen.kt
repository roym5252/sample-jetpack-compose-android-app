package com.jlp.feature_product_list.ui

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

@Preview
@Composable
fun ProductListScreen(@PreviewParameter(SampleProductProvider::class) products:List<String>){


    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp), Modifier.testTag("productList"), content = {

        items(products) { product ->

            Text(text = product)
        }
    })
}


