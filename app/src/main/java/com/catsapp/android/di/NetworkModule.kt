package com.catsapp.android.di

import android.content.Context
import com.catsapp.android.data.APIs
import com.catsapp.android.presentation.App
import com.catsapp.android.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /**
     * Provides the application instance.
     *
     * @param app The application context.
     * @return The application instance cast to [App].
     */
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    /**
     * Provides a [Retrofit] instance configured with a base URL, an [OkHttpClient],
     * and a Gson converter.
     *
     * @param client The [OkHttpClient] to be used by Retrofit.
     * @return A [Retrofit] instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    /**
     * Provides an [OkHttpClient] configured with a header interceptor and cache settings.
     *
     * @param headerInterceptor The interceptor for adding headers to requests.
     * @param cache The cache configuration for the client.
     * @return An [OkHttpClient] instance.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(Constants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(Constants.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(Constants.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor(headerInterceptor)

        return okHttpClientBuilder.build()
    }

    /**
     * Provides a header [Interceptor] that adds headers to every request.
     *
     * @return An [Interceptor] instance.
     */
    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            it.proceed(requestBuilder.build())
        }
    }

    /**
     * Provides a [Cache] instance for HTTP responses.
     *
     * @param context The application context.
     * @return A [Cache] instance.
     */
    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, Constants.CACHE_SIZE_BYTES)
    }

    /**
     * Provides the application context.
     *
     * @param application The application instance.
     * @return The application context.
     */
    @Provides
    @Singleton
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    /**
     * Provides the API service implementation.
     *
     * @param retrofit The [Retrofit] instance.
     * @return An implementation of the [APIs] interface.
     */
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): APIs {
        return retrofit.create(APIs::class.java)
    }
}