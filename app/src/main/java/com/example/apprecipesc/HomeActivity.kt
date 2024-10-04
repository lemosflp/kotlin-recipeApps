package com.example.apprecipesc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btNavigateToRecipe1 = findViewById<Button>(R.id.button_to_recipe) // Referência ao botão de receita
        btNavigateToRecipe1.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }

        val btNavigateToRecipe2 = findViewById<Button>(R.id.button_to_recipe2) // Referência ao botão de receita
        btNavigateToRecipe2.setOnClickListener {
            val intent = Intent(this, RecipeActivity2::class.java)
            startActivity(intent)
        }

        val btNavigateToProfile = findViewById<ImageButton>(R.id.button_to_profile) // Referência ao botão de perfil
        btNavigateToProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
