package com.example.tt_test.Login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tt_test.Login.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {
    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val viewState: StateFlow<ViewState> get() = _viewState

    fun login(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            loginUserUseCase(
                username = username,
                password = password
            ).onSuccess { isLoggedUser ->
                _viewState.value = ViewState.LoginSuccess(isLogged = isLoggedUser)
            }.onFailure {
                _viewState.value =
                    ViewState.LoginError(errorMessage = it.message ?: "Error logging in")
            }
        }
    }

    sealed class ViewState {
        data class LoginSuccess(val isLogged: Boolean) : ViewState()
        data class LoginError(val errorMessage: String) : ViewState()
        data object Loading : ViewState()
    }
}