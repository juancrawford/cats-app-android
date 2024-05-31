package com.catsapp.android.di


import com.catsapp.android.data.TestData.cat
import com.catsapp.android.data.APIs
import com.catsapp.android.domain.BreedUseCase
import com.catsapp.android.model.Cat
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Response
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideFakeAPIs(): APIs {
        return FakeAPIs()
    }

    @Provides
    @Singleton
    fun provideBreedUseCase(api: APIs): BreedUseCase {
        return BreedUseCase(api)
    }
}

// Fake implementation of the APIs interface
class FakeAPIs : APIs {
    override suspend fun getCats(page: Int, limit: Int, hasBreeds: Int): Response<List<Cat>> {
        val fakeCats = listOf(cat)
        return Response.success(fakeCats)
    }
}


