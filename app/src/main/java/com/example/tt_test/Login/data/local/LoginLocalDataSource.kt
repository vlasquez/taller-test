package com.example.tt_test.Login.data.local

class LoginLocalDataSource(private val loginDao: LoginDao) {

    suspend fun loginUser(username: String, password: String): Result<Boolean> {
        return try {
            if (username == loginDao.provideUsername() && password == loginDao.providePassword()) {
                Result.success(true)
            } else {
                Result.success(false)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

