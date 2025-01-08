package com.example.tt_test.Login.data

interface LoginRepository {
    suspend fun loginUser(username: String, password: String): Result<Boolean>
}