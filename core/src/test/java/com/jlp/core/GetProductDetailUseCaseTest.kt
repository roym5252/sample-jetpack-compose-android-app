package com.jlp.core

import com.jlp.core.datasource.remote.APIInterface
import com.jlp.core.datasource.remote.model.productdetail.RemoteProductDetailResponse
import com.jlp.core.testutil.MainCoroutineRule
//import com.jlp.core.testutil.validProductDetailJsonResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
@OptIn(ExperimentalCoroutinesApi::class)
class GetProductDetailUseCaseTest {

    /*@ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: APIInterface

    private val productID:Long= 3218074

    @Before
    fun setUp() {

        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            //.addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIInterface::class.java)
    }

    @Test
    fun get_product_detail_valid_response_test() = runTest {

        val mockResponse: MockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(validProductDetailJsonResponse)

        mockWebServer.enqueue(mockResponse)

        val call = getProductDetailApiCall(1L)
        val response: Response<RemoteProductDetailResponse?> = call.execute()

        Assert.assertEquals(
            true,
            (response.body() != null && (response.body()?.remoteDetailsData != null && response.body()?.remoteDetailsData!!.isNotEmpty()))
        )
    }

    @Test
    fun get_products_empty_body_test() = runTest {

        val mockResponse: MockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("{}")

        mockWebServer.enqueue(mockResponse)

        val call = getProductDetailApiCall(productID)
        val response: Response<RemoteProductDetailResponse?> = call.execute()

        Assert.assertEquals(
            true,
            (response.body() != null && response.body()?.remoteDetailsData == null)
        )
    }

    @Test
    fun get_products_malformed_json_test() = runTest {

        val mockResponse: MockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("{,[}")

        mockWebServer.enqueue(mockResponse)

        val call = getProductDetailApiCall(productID)

        val response = try {
            call.execute()
        } catch (exception: Exception) {
            exception
        }

        Assert.assertEquals(
            true,
            (response is com.google.gson.stream.MalformedJsonException)
        )
    }

    @Test
    fun get_products_non_200_response_code_test() = runTest {

        val mockResponse: MockResponse = MockResponse()
            .setResponseCode(404)
            .setBody("[]")

        mockWebServer.enqueue(mockResponse)

        val call = getProductDetailApiCall(productID)
        val response = call.execute()

        Assert.assertEquals(true, response.body() == null)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun getProductDetailApiCall(productId:Long): Call<RemoteProductDetailResponse?> {
        return apiClient.getProductDetail(productId)
    }*/
}
