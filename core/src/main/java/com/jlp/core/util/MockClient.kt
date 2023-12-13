package com.jlp.core.util

import android.content.Context
import android.util.Log
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

class MockClient @Inject constructor(private val context: Context, private val prefUtil: PrefUtil) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val url = chain.request().url
        Log.d("MockClient", "url=$url")

        val modifiedUrl = chain.request().url.newBuilder().apply {
            addQueryParameter("key", prefUtil.getString("api_key"))
        }.build()

        Log.d("MockClient", "Modified URL=$modifiedUrl")

        val newRequest = chain.request().newBuilder().url(url).build()

        when (url.encodedPath) {
            Constant.API_SEARCH -> {
                val response: String? = readJsonFromAssets(context,"mockData/product_list.json")

                return Response.Builder()
                    .code(200)
                    .message(response!!)
                    .request(newRequest)
                    .protocol(Protocol.HTTP_1_1)
                    .body(response.toByteArray().toResponseBody())
                    .addHeader("content-type", "application/json")
                    .build()
            }

            else->{

                val response = "{}"

                return Response.Builder()
                    .code(200)
                    .message(response)
                    .request(newRequest)
                    .protocol(Protocol.HTTP_1_1)
                    .body(response.toByteArray().toResponseBody())
                    .addHeader("content-type", "application/json")
                    .build()
            }
        }
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
