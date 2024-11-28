package com.example.apprecipesc


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apprecipesc.databinding.ActivityMainBinding
//import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var binding: ActivityMainBinding? = null
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        auth = Firebase.auth


        binding?.buttonNv?.setOnClickListener {
            val email = binding?.email?.text.toString()
            val password = binding?.password?.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInWithEmailAndPassword(email, password)

                val recipesMap = hashMapOf(
                    "name" to "croassaint",
                    "description" to "croassaint macio e recheado",
                    "ingredients" to "massa, presunto",
                    "preparationSteps" to "Misture e frite",
                    "image" to "..."
                )

                db.collection("recipe").document("02")
                    .set(recipesMap).addOnCompleteListener {
                        Log.d("db", "sucesso ao salvar os dados")
                    }
            } else {
                Toast.makeText(this, "Por favor, preencha os campos.", Toast.LENGTH_SHORT).show()
            }
        }


        // Navegar para a tela de registro
        binding?.textCreateAccount?.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Falha na autenticação.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
