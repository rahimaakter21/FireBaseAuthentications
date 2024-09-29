package com.example.firebaseauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.databinding.ActivityRegistrationBinding
import com.example.firebaseauthentication.veiwmodel.AuthViewModels

class RegistrationActivity : AppCompatActivity() {
    private  lateinit var  viewModel: AuthViewModels
    private  lateinit var  binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityRegistrationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(AuthViewModels::class.java)
        binding.registerBtn.setOnClickListener{

            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confirmPassword = binding.ConfirmPasswordEt.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                Toast.makeText(this@RegistrationActivity,"register success",Toast.LENGTH_SHORT).show()

                viewModel.signUp(email, password)

            }else if(!password.equals(confirmPassword)) {

                Toast.makeText(this@RegistrationActivity, "password does not match", Toast.LENGTH_SHORT).show()
            }
            else{
            viewModel.signUp(email, password).observe(this,{result->

                Toast.makeText(this@RegistrationActivity,result,Toast.LENGTH_SHORT).show()


                 if(result.equals("SignUp Success")){
                     startActivity(Intent(this@RegistrationActivity,LoginActivity::class.java))
                 }

            })

            }

        }
        binding.alreadyHaveAnAccount.setOnClickListener{
            startActivity(Intent(this@RegistrationActivity,LoginActivity::class.java))
        }


    }
}