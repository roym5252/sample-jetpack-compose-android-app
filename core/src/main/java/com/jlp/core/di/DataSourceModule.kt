package com.jlp.core.di

import android.content.Context
import com.jlp.core.datasource.remote.APIInterface
import com.jlp.core.util.Constant.BASE_URL
import com.jlp.core.util.MockClient
import com.jlp.core.util.PrefUtil
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return interceptor
    }

    @Provides
    fun provideMockClientClient(@ApplicationContext appContext: Context,prefUtil: PrefUtil): MockClient {
        return MockClient(appContext,prefUtil)
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor:HttpLoggingInterceptor,mockClient: MockClient): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor(mockClient).build()
    }

    @Provides
    fun provideApiClient(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): APIInterface {

        val apiClient = Retrofit.Builder()
            .baseUrl(BASE_URL)
            //.addConverterFactory(MoshiConverterFactory.create(moshi))
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

        return apiClient.create(
            APIInterface::class.java
        )
    }

    @Provides
    fun provideMoshi():Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    fun provideGsonConverterFactory():GsonConverterFactory = GsonConverterFactory.create()
}