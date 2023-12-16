package com.jlp.core.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jlp.core.datasource.remote.model.productdetail.ProductDetailResponse
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.buffer
import okio.source
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject


class MockClient @Inject constructor(private val context: Context, private val prefUtil: PrefUtil) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val url = chain.request().url

        val modifiedUrl = chain.request().url.newBuilder().apply {
            addQueryParameter("key", prefUtil.getString("api_key"))
        }.build()

        val newRequest = chain.request().newBuilder().url(modifiedUrl).build()

        when (url.encodedPath) {
            Constant.API_SEARCH -> {
                val response: String? = readJsonFromAssets(context, "mockData/product_list.json")

                return Response.Builder()
                    .code(200)
                    .message(response!!)
                    .request(newRequest)
                    .protocol(Protocol.HTTP_1_1)
                    .body(response.toByteArray().toResponseBody())
                    .addHeader("content-type", "application/json")
                    .build()
            }

            else -> {

                val pathSegment = url.pathSegments
                val jsonResponse = readJsonAndExtractResult("mockData/product_detail.json")

                val productDetail = jsonResponse?.detailsData?.filter {
                    it.productId.contentEquals(pathSegment[3])
                }

                val response = ProductDetailResponse(productDetail!!)

                return Response.Builder()
                    .code(200)
                    .message(response.toString())
                    .request(newRequest)
                    .protocol(Protocol.HTTP_1_1)
                    .body(Gson().toJson(response).toByteArray().toResponseBody())
                    .addHeader("content-type", "application/json")
                    .build()

            }
        }
    }


    private fun readJsonAndExtractResult(filePath: String): ProductDetailResponse? {
        val json: String? = try {
            // Read the JSON file from the assets folder
            readJsonFromAssets(context, filePath)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        // Use Gson to parse the JSON string
        val type = object : TypeToken<ProductDetailResponse>() {}.type

        // Extract the desired result (e.g., the first item)
        return Gson().fromJson(json, type)
    }

    private fun readJsonFromAssets(context: Context, filePath: String): String? {
        try {
            val source = context.assets.open(filePath).source().buffer()
            return source.readByteString().string(Charset.forName("utf-8"))

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}
