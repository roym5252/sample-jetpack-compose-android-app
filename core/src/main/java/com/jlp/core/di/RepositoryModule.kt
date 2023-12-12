package com.jlp.core.di

import com.jlp.core.datasource.ProductRepository
import com.jlp.core.datasource.ProductRepositoryImpl
import com.jlp.core.datasource.remote.APIInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Module for providing repository objects.
 */
@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideImageRepository(apiInterface: APIInterface): ProductRepository {
        return ProductRepositoryImpl(apiInterface)
    }
}