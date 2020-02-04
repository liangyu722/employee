package com.github.liangyu.employee.di.network

import com.github.liangyu.employee.data.remote.network.EmployeeService
import com.github.liangyu.employee.data.remote.network.ServiceFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ServiceModule {

    companion object {
        private const val ENDPOINT = "https://s3.amazonaws.com/sq-mobile-interview/"
        private const val READ_TIMEOUT_SECONDS = 5L
        private const val CONNECTION_TIMEOUT_SECONDS = 5L
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesServiceFactory(gson: Gson, okHttp: OkHttpClient): ServiceFactory {
        return ServiceFactory(gson, okHttp)
    }

    @Provides
    fun providesGeoNameEarthquakeService(factory: ServiceFactory): EmployeeService {
        return factory.createService(EmployeeService::class.java, ENDPOINT)
    }
}
