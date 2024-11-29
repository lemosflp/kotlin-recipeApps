package com.example.apprecipesc


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apprecipesc.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    companion object {
        private const val TAG = "RegisterActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.buttonRegister.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val confirmPassword = binding.confirmPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    Toast.makeText(this, "usuario criado.", Toast.LENGTH_SHORT).show()
                    createUser(email, password)
                } else {
                    Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "createUserWithEmailAndPassword: Sucesso")
                Toast.makeText(this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Log.w(TAG, "createUserWithEmailAndPassword: Falha", task.exception)
                Toast.makeText(this, "Erro ao criar conta: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
