package com.example.todosappmongodb.daggerHilt

import com.example.todosappmongodb.api.TodoApi
import com.example.todosappmongodb.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule
{


    @Singleton
    @Provides
    fun  provideInterceptor() : Interceptor = object : Interceptor
    {
        override fun intercept(chain: Interceptor.Chain): Response =
            chain.run {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader("Content-Type","application/json")
                        .build()
                )
            }
    }




    @Singleton
    @Provides
    fun provideHttpClient(interceptor: Interceptor) : OkHttpClient = OkHttpClient.Builder()
       // .retryOnConnectionFailure(true)
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()


    @Singleton
    @Provides
    fun provideMoshiConverterFactory() : MoshiConverterFactory = MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory) : Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(moshiConverterFactory)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): TodoApi = retrofit .create(TodoApi::class.java)


}