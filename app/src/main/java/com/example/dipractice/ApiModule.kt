package com.example.dipractice

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    private const val BASE_URL = "https://dapi.kakao.com"
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideTestInterceptor() : Interceptor {
        val interceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        "KakaoAK %s".format(
                            "c2e35e66f144e5d544ec6782c56b342b"
                        )
                    ).build()

                return chain.proceed(request)
            }
        }
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(provideTestInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSearch(retrofit: Retrofit) : GetSearchDataResource {
       return retrofit.create(GetSearchDataResource::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(getSearchDataResource: GetSearchDataResource) = SearchRepository(getSearchDataResource)

}