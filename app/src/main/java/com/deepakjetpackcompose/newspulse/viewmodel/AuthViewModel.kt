package com.deepakjetpackcompose.newspulse.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthViewModel() : ViewModel() {
    private val auth: FirebaseAuth= FirebaseAuth.getInstance()
    private val _authState= MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState
    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value= AuthState.Unauthenticated
        }else{
            _authState.value= AuthState.Authenticated
        }
    }

    fun login(email:String, password:String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value= AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value= AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    _authState.value= AuthState.Authenticated
                }else{
                    _authState.value= AuthState.Error(task.exception?.message?:"something went wrong")
                }

            }
    }


    fun signUp(email:String, password:String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value= AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value= AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    _authState.value= AuthState.Authenticated
                }else{
                    _authState.value= AuthState.Error(task.exception?.message?:"something went wrong")
                }

            }
    }

    fun signOut(){
        auth.signOut()
        _authState.value= AuthState.Unauthenticated
    }

    fun authWithGoogle(tokenId:String){
        val credential= GoogleAuthProvider.getCredential(tokenId,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    _authState.value= AuthState.Authenticated
                }else{
                    _authState.value= AuthState.Error(task.exception?.message?:"Google sign in failed")
                }

            }
    }

}

sealed class AuthState{
    object Authenticated: AuthState()
    object Unauthenticated: AuthState()
    object Loading : AuthState()
    data class Error(val message:String): AuthState()
}