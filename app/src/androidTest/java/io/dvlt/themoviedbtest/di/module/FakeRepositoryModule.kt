package io.dvlt.themoviedbtest.di.module


import com.aymenjegham.core.data.repository.movie.MovieRepository
import com.aymenjegham.framework.di.module.RepositoryModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.dvlt.themoviedbtest.FakeMovieRepository
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class FakeRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsFakeUserRepository(movieRepository: FakeMovieRepository): MovieRepository
}