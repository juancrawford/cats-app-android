package com.catsapp.android

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    DetailActivityTest::class
)
class ActivityTestSuite