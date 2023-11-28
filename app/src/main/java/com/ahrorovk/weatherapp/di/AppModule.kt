package com.ahrorovk.weatherapp.di

import android.app.Application
import com.ahrorovk.weatherapp.core.Constants
import com.ahrorovk.weatherapp.data.network.WeatherRepositoryImpl
import com.ahrorovk.weatherapp.data.network.remote.WeatherApi
import com.ahrorovk.weatherapp.domain.repository.WeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi =
        Retrofit
            .Builder()
            .baseUrl(Constants.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .readTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .build()
            )
            .build()
            .create(WeatherApi::class.java)


    @Singleton
    @Provides
    fun provideWeatherRepository(
        apiWeather: WeatherApi
    ): WeatherRepository = WeatherRepositoryImpl(apiWeather)

    /* TestRepository
     @Singleton
     @Provides
     fun provideTestWeatherRepository(): WeatherRepository = TestWeatherRepositoryImpl()
     */
    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }
}