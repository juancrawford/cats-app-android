package com.catsapp.android

import androidx.test.filters.LargeTest
import com.catsapp.android.presentation.detail.DetailActivityTest
import com.catsapp.android.presentation.main.MainActivityTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@LargeTest
@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    DetailActivityTest::class
)
class ActivityTestSuite