package com.example.firebaseauthentication.veiwmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModels: ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    fun getCurrentUser() = auth.currentUser
    fun signUp(email: String, password: String): LiveData<String> {
        val result = MutableLiveData<String>()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    result.value = " SignUp Success"
                } else {
                    result.value = it.exception?.message
                }
            }
        return result
    }

    fun signIn(email: String, password: String): LiveData<String>{

    val result = MutableLiveData<String>()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    result.value = "SignIn Success"
                } else {
                    result.value = it.exception?.message
                }
            }
        return result
    }

     fun signOut() {


         auth.signOut()
     }
}