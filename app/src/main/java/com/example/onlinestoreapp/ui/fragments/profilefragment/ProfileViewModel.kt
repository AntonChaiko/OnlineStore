package com.example.onlinestoreapp.ui.fragments.profilefragment

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlinestoreapp.domain.models.AuthListState
import com.example.onlinestoreapp.domain.models.UserProfile
import com.example.onlinestoreapp.domain.use_case.GetUserDataUseCase
import com.example.onlinestoreapp.domain.use_case.UpdateUserDataUseCase
import com.example.onlinestoreapp.utils.Constants
import com.example.onlinestoreapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val updateUser: UpdateUserDataUseCase,
    private val getData: GetUserDataUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _isEditable: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEditable: LiveData<Boolean>
        get() = _isEditable


    private val _authState = MutableLiveData(AuthListState())
    val authState: LiveData<AuthListState> = _authState

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    val firstName: MutableLiveData<String> = MutableLiveData("")
    val secondName: MutableLiveData<String> = MutableLiveData("")
    val number: MutableLiveData<String> = MutableLiveData("")

    val image: MutableLiveData<String> = MutableLiveData("")

    init {
        getUserData()
    }

    private fun getUserData() {
        val userId = sharedPreferences.getString(Constants.USER_ID, "")

        if (!userId.isNullOrEmpty()) {
            getData(userId = userId).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data.let {
                            email.value = it?.email
                            firstName.value = it?.firstName
                            secondName.value = it?.lastName
                            number.value = it?.number
                        }
                    }
                    else -> {
                    }
                }

            }.launchIn(viewModelScope)
        }

    }

    fun updateUserProfileData() {
        updateUser(setupUserProfile())
            .onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _authState.value =
                            AuthListState(error = result.message ?: "Something goes wrong")
                    }
                    is Resource.Success -> {
                        _authState.value = AuthListState(isRegistrationComplete = true)
                        _authState.value = AuthListState(isLoading = false)
                        _isEditable.value = false
                    }
                    is Resource.Loading -> {
                        _authState.value = AuthListState(isLoading = true)
                    }
                }

            }.launchIn(viewModelScope)
    }

    fun makeFieldsEditable() {
        _isEditable.value = !_isEditable.value!!
    }

    private fun setupUserProfile(): UserProfile {
        return UserProfile(
            firstName = firstName.value.toString(),
            lastName = secondName.value.toString(),
            number = number.value.toString(),
            email = email.value.toString(),
            image = image.value.toString()
        )
    }

}