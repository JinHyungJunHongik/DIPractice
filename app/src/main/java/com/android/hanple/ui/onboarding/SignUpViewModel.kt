package com.android.hanple.ui.onboarding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hanple.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth //firebase auth 가져오기.

    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> get() = _signUpState

    fun signUp(localUser: User): Int {
        val email = localUser.email
        val password = localUser.password
        if (email == null || email == "") return 1
        else if (password == null || password == "") return 2
        else {
            Log.d("SignUpViewModel", email)
            Log.d("SignUpViewModel", password)
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { signup ->
                    if(signup.isSuccessful) {
                        _signUpState.value = SignUpState.Success(localUser)
                    } else {
                        _signUpState.value = SignUpState.Failure(signup.exception)
                    }
                }
            return 0
        }
    }

    sealed class SignUpState {
        data class Success(val localUser: User) : SignUpState()
        data class Failure(val exception: Exception?) : SignUpState()

    }
//
//    private val _signupSuccess = MutableLiveData<Boolean>()
//    val signupSuccess: LiveData<Boolean> = _signupSuccess
//
//    //View가 ViewModel을 관찰한다면, ViewModel은 View에게 데이터 변경, 즉 UI 내용 변경을 알려준다.
//    fun updateSignupData(field: Field, value: String) { //UI update. input이 들어올 때마다 갱신
//        val currentData = _signupData.value ?: SignupData()
//        val updatedData = when (field) {
//            Field.NAME -> currentData.copy(name = value)
//            Field.ID -> currentData.copy(id = value)
//            Field.PASSWORD -> currentData.copy(password = value)
//            Field.PHONE -> if (ConvertUtils.phoneNumberRegex.containsMatchIn(value)) currentData.copy(
//                phoneNumber = value
//            ) else currentData.copy(phoneNumber = null)
//        }
//        _signupData.value = updatedData
//    }
//
//    fun checkStatus(): Boolean { //
//        return (_signupData.value?.checkStatus() == true)
//    }
//
//    fun registerUser() {
//        val newUser = _signupData.value?.asUser()
//        if (newUser != null) {
//            UserSampleData.addUser(newUser)
//            _signupSuccess.value = true
//        } else {
//            _signupSuccess.value = false
//        }
//    }
//
//
//    enum class Field {
//        NAME, ID, PASSWORD, PHONE
//    }
//
//    data class SignupData(
//        val phoneNumber: String? = null,
//        val name: String? = null,
//        val id: String? = null,
//        val password: String? = null,
//    ) {
//        fun checkStatus(): Boolean = //null 체크.
//            !phoneNumber.isNullOrBlank() && !name.isNullOrBlank() && !id.isNullOrBlank() && !password.isNullOrBlank()
//
//        fun asUser(): User {
//            return User(
//                id = id ?: "",
//                name = name ?: "",
//                password = password ?: "",
//                phoneNumber = phoneNumber ?: ""
//            )
//        }
//    }
}

