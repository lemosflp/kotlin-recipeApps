package com.example.apprecipesc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        // Recebe os dados da Intent
        val recipeName = intent.getStringExtra("RECIPE_NAME")
        val recipeDescription = intent.getStringExtra("RECIPE_DESCRIPTION")

        // Atualiza a interface com os dados
        val tvRecipeName = findViewById<TextView>(R.id.tv_recipe_name)
        val tvRecipeDescription = findViewById<TextView>(R.id.tv_recipe_description)

        tvRecipeName.text = recipeName
        tvRecipeDescription.text = recipeDescription

        // Configura o botão de retorno
        val btNavigate = findViewById<ImageButton>(R.id.button_return_recipe)
        btNavigate.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}

