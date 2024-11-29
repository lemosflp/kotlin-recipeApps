package com.example.apprecipesc

import Recipe
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apprecipesc.databinding.ActivityHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var recipeAdapter: RecipeAdapter
    private val recipes = mutableListOf<Recipe>()
    private var listenerRegistration: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btNavigateToProfile = findViewById<ImageButton>(R.id.button_to_profile)
        btNavigateToProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        recipeAdapter = RecipeAdapter(recipes) { selectedRecipe ->
            val intent = Intent(this, RecipeActivity::class.java)
            intent.putExtra("RECIPE_KEY", selectedRecipe)
            startActivity(intent)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = recipeAdapter
        }

        // Call function to get recipes
        getRecipesFromFirestore()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                recipeAdapter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipeAdapter.filter(newText)
                return false
            }
        })
    }

    // Func to get recipes of Firestore with SnapshotListener
    private fun getRecipesFromFirestore() {
        val db = FirebaseFirestore.getInstance()
        val recipeRef = db.collection("recipe")

        listenerRegistration = recipeRef.addSnapshotListener { result, exception ->
            if (exception != null) {
                Log.d("HomeActivity", "Error getting documents: ", exception)
                return@addSnapshotListener
            }

            recipes.clear()

            // Add recipes on Adapter
            result?.forEach { document ->
                val name = document.getString("name") ?: ""
                val description = document.getString("description") ?: ""
                val ingredients = document.getString("ingredients") ?: ""
                val preparationSteps = document.getString("preparationSteps") ?: ""

                // Add recipes in the list
                recipes.add(
                    Recipe(
                        name = name,
                        description = description,
                        imageResId = R.drawable.bolo, // Defina uma imagem padrão
                        ingredients = ingredients,
                        preparationSteps = preparationSteps
                    )
                )
            }

            recipeAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listenerRegistration?.remove()
    }
}
