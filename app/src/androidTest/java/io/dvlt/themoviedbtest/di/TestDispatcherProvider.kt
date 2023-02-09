package io.dvlt.themoviedbtest.di

import com.aymenjegham.framework.di.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Inject

class TestDispatcherProvider @Inject constructor() : DispatcherProvider {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val main: CoroutineDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val mainImmediate: CoroutineDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val default: CoroutineDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val io: CoroutineDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val unconfined: CoroutineDispatcher = UnconfinedTestDispatcher()
}