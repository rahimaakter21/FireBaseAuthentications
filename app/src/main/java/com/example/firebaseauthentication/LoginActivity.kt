package com.example.firebaseauthentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseauthentication.databinding.ActivityLoginBinding
import com.example.firebaseauthentication.veiwmodel.AuthViewModels
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private  lateinit var  viewModels: AuthViewModels
    private  lateinit var  binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModels = ViewModelProvider(this).get(AuthViewModels::class.java)

        binding.Login.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            if(email.isEmpty() && password.isEmpty()){
                Toast.makeText(this@LoginActivity,"please fill the fields", Toast.LENGTH_SHORT).show()

                viewModels.signUp(email, password)

            }else if(!password.equals(password)) {

                Toast.makeText(this@LoginActivity, "password does not match", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModels.signIn(email, password).observe(this,{result->

                    Toast.makeText(this@LoginActivity,result, Toast.LENGTH_SHORT).show()


                    if(result.equals("SignIn Success")){
                        startActivity(Intent(this@LoginActivity,LoginActivity::class.java))
                    }

                })

            }
        }
        binding.dontHaveAnAccountTxt.setOnClickListener{
            startActivity(Intent(this@LoginActivity,RegistrationActivity::class.java))
        }
    }
    override  fun onStart() {
    super.onStart()
    if (FirebaseAuth.getInstance().currentUser != null) {
        startActivity(Intent(this@LoginActivity,HomeActivity::class.java))

    }

    }
}