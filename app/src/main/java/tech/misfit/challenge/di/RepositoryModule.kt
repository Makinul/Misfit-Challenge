package tech.misfit.challenge.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import tech.misfit.challenge.data.repository.StoreRepository
import tech.misfit.challenge.data.repository.StoreRepositoryImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(impl: StoreRepositoryImpl): StoreRepository

}