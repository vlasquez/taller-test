package com.example.tt_test.Login.data.remote

class LoginRemoteDataSource {
    fun loginUser(username: String, password: String): Result<Boolean> {
        return Result.success(true)
    }
}