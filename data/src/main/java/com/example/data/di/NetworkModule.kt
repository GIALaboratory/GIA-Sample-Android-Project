package com.example.data.di

import android.content.Context
import com.example.data.remote.interceptors.MockInterceptor
import com.example.data.remote.services.ReportService
import com.example.data.utils.adapters.ReportSerialAdapter
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMockInterceptor(@ApplicationContext context: Context): MockInterceptor {
        return MockInterceptor(context)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mockInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://mock-api.gia.edu/") // Base URL doesn't matter with interceptor
            .client(okHttpClient)
            .addConverterFactory(provideGsonBuilder())
            .build()
    }

    @Singleton
    @Provides
    fun provideReportService(retrofit: Retrofit): ReportService {
        return retrofit.create(ReportService::class.java)
    }

    @Provides
    fun provideGsonBuilder(): GsonConverterFactory {
        val builder = GsonBuilder().apply {
            registerTypeAdapter(com.example.data.remote.models.ReportResponse::class.java, ReportSerialAdapter())
        }
        return GsonConverterFactory.create(builder.create())
    }
}