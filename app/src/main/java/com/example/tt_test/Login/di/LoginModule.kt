package com.example.tt_test.Login.di

import com.example.tt_test.Login.data.LoginRepository
import com.example.tt_test.Login.data.LoginRepositoryImpl
import com.example.tt_test.Login.data.local.LoginDao
import com.example.tt_test.Login.data.local.LoginLocalDataSource
import com.example.tt_test.Login.data.remote.LoginRemoteDataSource
import com.example.tt_test.Login.domain.usecase.LoginUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Provides
    @Singleton
    fun provideLoginUserUseCase(loginRepository: LoginRepository): LoginUserUseCase {
        return LoginUserUseCase(loginRepository)
    }


    @Provides
    @Singleton
    fun provideLoginRepository(
        localDataSource: LoginLocalDataSource,
        remoteDataSource: LoginRemoteDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @Provides
    @Singleton
    fun providesLoginRemoteDataSource(): LoginRemoteDataSource {
        return LoginRemoteDataSource()
    }

    @Provides
    @Singleton
    fun providesLoginLocalDataSource(): LoginLocalDataSource {
        return LoginLocalDataSource(
            loginDao = LoginDao()
        )
    }
}