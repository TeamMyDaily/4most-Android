package org.mydaily.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.local.FourMostPreference
import org.mydaily.data.model.network.request.ReqSignIn
import org.mydaily.data.model.network.request.ReqSignUp
import org.mydaily.data.model.network.response.*
import org.mydaily.data.repository.UserRepo
import org.mydaily.ui.base.BaseViewModel
import org.mydaily.util.Event
import retrofit2.Call
import retrofit2.Callback

class UserViewModel(private val repo: UserRepo): BaseViewModel() {

    private val _signInEvent = MutableLiveData<Event<Boolean>>()
    val signInEvent: LiveData<Event<Boolean>> = _signInEvent

    private val _signUpEvent = MutableLiveData<Event<Boolean>>()
    val signUpEvent: LiveData<Event<Boolean>> = _signUpEvent

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage

    fun postSignIn(email: String, password: String){
        repo.postSignIn(ReqSignIn(email = email, password = password))
            .enqueue(object : Callback<ResSignIn>{
                override fun onResponse(
                    call: Call<ResSignIn>,
                    response: retrofit2.Response<ResSignIn>
                ) {
                    if(response.isSuccessful){
                        _signInEvent.postValue(Event(true))
                        FourMostPreference.setUserToken(response.body()?.data?.accessToken!!)
                        FourMostPreference.setUserName(response.body()?.data?.userName!!)
                        FourMostPreference.setUserEmail(response.body()?.data?.email!!)
                    }else {
                        _toastMessage.postValue(Event("로그인에 실패하였습니다"))
                    }
                }
                override fun onFailure(call: Call<ResSignIn>, t: Throwable) {
                    Log.e(TAG, "postSignUp", t)
                    _toastMessage.postValue(Event("로그인에 실패하였습니다"))
                }
            })
    }

    fun postSignUp(email: String, password: String, passwordConfirm: String, userName: String){
        repo.postSignUp(ReqSignUp(email, password, passwordConfirm, userName))
            .enqueue(object : Callback<ResSignUp>{
                override fun onResponse(
                    call: Call<ResSignUp>,
                    response: retrofit2.Response<ResSignUp>
                ) {
                    if(response.isSuccessful){
                        _signUpEvent.postValue(Event(true))
                    }else {
                        _toastMessage.postValue(Event("회원가입에 실패하였습니다"))
                    }
                }
                override fun onFailure(call: Call<ResSignUp>, t: Throwable) {
                    Log.e(TAG, "postSignUp", t)
                    _toastMessage.postValue(Event("회원가입에 실패하였습니다"))
                }
            })
    }

    companion object{
        private val TAG = UserViewModel::class.java.simpleName

    }
}