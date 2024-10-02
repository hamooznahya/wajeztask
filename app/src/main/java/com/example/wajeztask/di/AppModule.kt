package com.example.wajeztask.di

import android.content.Context
import com.example.wajeztask.constant.Constants
import com.example.wajeztask.data.APIEndpoints
import com.example.wajeztask.data.datasource.OfflineDataSource
import com.example.wajeztask.data.datasource.RemoteDataSource
import com.example.wajeztask.data.network.ResponseHandlerInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.wajeztask.domain.datasource.OfflineDataSourceImpl
import com.example.wajeztask.domain.datasource.RemoteDataSourceImpl
import com.example.wajeztask.dispatcher.DispatchersImpl
import com.example.wajeztask.dispatcher.IDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    companion object {
        const val TIMEOUT_SECONDS = 30 * 1000L
    }




    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext appContext: Context,
    ): OfflineDataSource {
        return OfflineDataSourceImpl()
    }

    @Provides
    fun RemoteDataSource(apiEndpoints: APIEndpoints): RemoteDataSource =
        RemoteDataSourceImpl(apiEndpoints)

    @Provides
    fun provideDispatcher(): IDispatchers = DispatchersImpl()


    @Provides
    @Singleton
    fun getRetrofit(): APIEndpoints {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient.Builder()
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor(ResponseHandlerInterceptor())
            .addInterceptor(interceptor)
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .baseUrl(Constants.BASE_URL)
            .client(builder.build())
            .build()
        return retrofit.create(APIEndpoints::class.java)
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.setLenient()
        return gsonBuilder.create()
    }


}