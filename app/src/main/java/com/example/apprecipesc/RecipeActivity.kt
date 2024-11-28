package com.example.apprecipesc

import Recipe
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        val recipe: Recipe = intent.getSerializableExtra("RECIPE_KEY") as Recipe

        // Verificando se a receita foi recebida corretamente
        if (recipe != null) {
            val recipeName: TextView = findViewById(R.id.tv_recipe_name)
            val recipeImage: ImageView = findViewById(R.id.iv_recipe_image)
            val recipeDescription: TextView = findViewById(R.id.tv_recipe_description)
            val ingredientsList: TextView = findViewById(R.id.tv_ingredients_list)
            val preparationSteps: TextView = findViewById(R.id.tv_preparation_steps)

            // Preenchendo os campos com os dados da receita
            recipeName.text = recipe.name
            recipeImage.setImageResource(recipe.imageResId)
            recipeDescription.text = recipe.description
            ingredientsList.text = recipe.ingredients.joinToString("\n") { "- $it" }
            preparationSteps.text = recipe.preparationSteps.joinToString("\n") { "$it" }
        }

        // Configurando o botão de retorno
        val returnButton: ImageButton = findViewById(R.id.button_return_recipe)
        returnButton.setOnClickListener {
            finish()  // Voltar para a HomeActivity
        }
    }
}

