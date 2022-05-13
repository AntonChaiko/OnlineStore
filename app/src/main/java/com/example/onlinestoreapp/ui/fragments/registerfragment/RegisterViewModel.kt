package com.example.onlinestoreapp.ui.fragments.registerfragment

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.models.AuthListState
import com.example.onlinestoreapp.domain.models.UserCredentials
import com.example.onlinestoreapp.domain.models.UserProfile
import com.example.onlinestoreapp.domain.use_case.GetUserIdUseCase
import com.example.onlinestoreapp.domain.use_case.RegisterUseCase
import com.example.onlinestoreapp.domain.use_case.SetupUserDataUseCase
import com.example.onlinestoreapp.utils.Constants
import com.example.onlinestoreapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val setupUserDataUseCase: SetupUserDataUseCase,
    private val getUserId: GetUserIdUseCase,
    private val sharedPrefs: SharedPreferences
) : ViewModel() {
    private val _authState = MutableLiveData(AuthListState())
    val authState: LiveData<AuthListState> = _authState

    private val _userCredentials: MutableLiveData<UserCredentials> = MutableLiveData()

    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()


    fun registerUser() {
        setupUser()
        registerUseCase(_userCredentials.value!!)
            .onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _authState.value =
                            AuthListState(error = result.message ?: "Something goes wrong")
                    }
                    is Resource.Success -> {
                        _authState.value = AuthListState(isRegistrationComplete = true)
                        saveUserId()
                    }
                    is Resource.Loading -> {
                        _authState.value = AuthListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun setupUser() {
        _userCredentials.value = UserCredentials(
            email.value.toString().trim(),
            password.value.toString().trim()
        )
    }

    private fun saveUserId() {
        getUserId().onEach {
            when (it) {
                is Resource.Success -> {
                    sharedPrefs.edit().putString(Constants.USER_ID, it.data)
                        .apply()
                    setupUserData()
                }
                is Resource.Error -> Log.d("asd", "saveUserId: ${it.message}")

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    private fun setupUserData() {
        setupUserDataUseCase(userProfile = UserProfile()).launchIn(viewModelScope)
    }

}