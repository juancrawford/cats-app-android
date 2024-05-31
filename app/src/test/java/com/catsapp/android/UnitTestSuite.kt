package com.catsapp.android

import com.catsapp.android.adapter.CatsAdapterTest
import com.catsapp.android.adapter.LoadMoreAdapterTest
import com.catsapp.android.domain.BreedUseCaseTest
import com.catsapp.android.paging.CatsPagingSourceTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    BreedUseCaseTest::class,
    CatsAdapterTest::class,
    CatsPagingSourceTest::class,
    LoadMoreAdapterTest::class
)
class UnitTestSuite