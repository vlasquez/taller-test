package com.example.tt_test.Login.domain.usecase

import com.example.tt_test.Login.data.LoginRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    suspend operator fun invoke(username: String, password: String): Result<Boolean> =
        loginRepository.loginUser(username, password)
}