package org.mydaily.ui.viewmodel

import org.mydaily.data.model.network.request.ReqPassword
import org.mydaily.data.model.network.request.ReqSignIn
import org.mydaily.data.model.network.request.ReqSignUp
import org.mydaily.data.model.network.response.*
import org.mydaily.data.repository.UserRepo
import org.mydaily.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback

class SignInViewModel(private val repo: UserRepo): BaseViewModel() {

    fun signIn(email: String, password: String) {
        //sign In
    }

    /* Example */
    fun postSignUp(email: String, password: String){
        repo.postSignIn(ReqSignIn(email = email, password = password))
            .enqueue(object : Callback<ResSignIn>{
                override fun onResponse(
                    call: Call<ResSignIn>,
                    response: retrofit2.Response<ResSignIn>
                ) {
                    //통신 성공
                }
                override fun onFailure(call: Call<ResSignIn>, t: Throwable) {
                    //통신 실패 시
                }
            })
    }
}