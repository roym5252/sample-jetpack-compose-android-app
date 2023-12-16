package com.jlp.core.datasource.remote

import com.jlp.core.datasource.remote.model.productdetail.ProductDetailResponse
import com.jlp.core.datasource.remote.model.productlist.RemoteProductResponse
import com.jlp.core.util.Constant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET(Constant.API_SEARCH)
    fun getProducts(@Query("q")title:String="dishwasher"): retrofit2.Call<RemoteProductResponse?>

    @GET(Constant.API_DETAIL+"/{productId}")
    fun getProductDetail(@Path("productId") id: Long?): retrofit2.Call<ProductDetailResponse?>
}