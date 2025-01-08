package com.example.tt_test.Login.data

import com.example.tt_test.Login.data.local.LoginLocalDataSource
import com.example.tt_test.Login.data.remote.LoginRemoteDataSource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: LoginLocalDataSource
) : LoginRepository {

    override suspend fun loginUser(username: String, password: String): Result<Boolean> {
        return localDataSource.loginUser(username = username, password = password).onSuccess {
            if (it) {
                Result.success(true)
            } else {
                Result.success(false)
                remoteDataSource.loginUser(username = username, password = password)
            }
        }.onFailure {
            Result.failure<Exception>(Exception("Error logging in"))
        }

    }
}