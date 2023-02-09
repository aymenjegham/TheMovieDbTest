package io.dvlt.themoviedbtest.di.module

import com.aymenjegham.framework.di.dispatcher.DispatcherProvider
import com.aymenjegham.framework.di.module.DispatchersModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.dvlt.themoviedbtest.di.TestDispatcherProvider
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class]
)
abstract class TestDispatchersModule {

    @Binds
    @Singleton
    abstract fun bindTestDispatchers(dispatchers: TestDispatcherProvider): DispatcherProvider
}