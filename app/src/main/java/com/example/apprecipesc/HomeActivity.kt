package com.example.apprecipesc

import Recipe
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apprecipesc.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btNavigateToProfile = findViewById<ImageButton>(R.id.button_to_profile)
        btNavigateToProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val recipes = getList()

        val recipeAdapter = RecipeAdapter(recipes) { selectedRecipe ->
            val intent = Intent(this, RecipeActivity::class.java)
            intent.putExtra("RECIPE_KEY", selectedRecipe)
            startActivity(intent)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = recipeAdapter
        }

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

    private fun getList() = listOf(
        Recipe(
            "Croissant",
            "Delicioso croissant de chocolate com recheio cremoso.",
            R.drawable.croissant,
            listOf("Farinha", "Manteiga", "Chocolate", "Água"),
            listOf("Passo 1: Misture a farinha...", "Passo 2: Adicione o chocolate...", "Passo 3: Modele...", "Passo 4: Asse no forno")
        ),
        Recipe(
            "Bolo de Cenoura",
            "Bolo de cenoura fofinho com cobertura de chocolate.",
            R.drawable.bolo,
            listOf("Cenoura", "Farinha", "Açúcar", "Ovos"),
            listOf("Passo 1: Bata as cenouras...", "Passo 2: Misture os ingredientes...", "Passo 3: Asse por 40 minutos...")
        )
    )
}