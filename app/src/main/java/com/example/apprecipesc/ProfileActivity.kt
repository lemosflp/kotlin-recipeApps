package com.example.apprecipesc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val edtRecipeId = findViewById<EditText>(R.id.edt_recipe_id)
        val edtName = findViewById<EditText>(R.id.edt_recipe_name)
        val edtDescription = findViewById<EditText>(R.id.edt_recipe_description)
        val edtIngredients = findViewById<EditText>(R.id.edt_recipe_ingredients)
        val edtPreparationSteps = findViewById<EditText>(R.id.edt_recipe_preparation_steps)
        val btnSaveRecipe = findViewById<Button>(R.id.button_save_recipe)

        btnSaveRecipe.setOnClickListener {
            val recipeId = edtRecipeId.text.toString().trim()
            val name = edtName.text.toString().trim()
            val description = edtDescription.text.toString().trim()
            val ingredients = edtIngredients.text.toString().trim()
            val preparationSteps = edtPreparationSteps.text.toString().trim()

            if (recipeId.isNotEmpty() && name.isNotEmpty() && description.isNotEmpty() && ingredients.isNotEmpty() && preparationSteps.isNotEmpty()) {
                val recipeMap = hashMapOf(
                    "name" to name,
                    "description" to description,
                    "ingredients" to ingredients,
                    "preparationSteps" to preparationSteps,
                )

                val userId = auth.currentUser?.uid
                if (userId != null) {
                    // Save recipe in Firestore
                    db.collection("recipe")
                        .document(recipeId)
                        .set(recipeMap)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Receita salva com sucesso!", Toast.LENGTH_SHORT).show()

                            edtRecipeId.text.clear()
                            edtName.text.clear()
                            edtDescription.text.clear()
                            edtIngredients.text.clear()
                            edtPreparationSteps.text.clear()

                            val intent = Intent()
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Falha ao salvar a receita.", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }

        val btNavigateToHome = findViewById<ImageButton>(R.id.button_to_home)
        btNavigateToHome.setOnClickListener {
            finish()
        }
    }
}
