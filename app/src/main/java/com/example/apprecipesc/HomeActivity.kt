package com.example.apprecipesc

import Recipe
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprecipesc.databinding.ActivityHomeBinding
import com.example.apprecipesc.databinding.ActivityMainBinding

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

        initRecyclerView()

        val btNavigateToProfile = findViewById<ImageButton>(R.id.button_to_profile)
        btNavigateToProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        val recipeList = getList()
        val adapter = RecipeAdapter(recipeList) { recipe ->
            val intent = Intent(this, RecipeActivity::class.java)
            intent.putExtra("RECIPE_KEY", recipe)  // Passando a receita
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
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