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
//        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()

//                val btNavigateToRecipe1 = findViewById<Button>(R.id.adapterButton) // Referência ao botão de receita
//        btNavigateToRecipe1.setOnClickListener {
//            val intent = Intent(this, RecipeActivity::class.java)
//            startActivity(intent)
//        }

//        val btNavigateToRecipe2 = findViewById<Button>(R.id.button_to_recipe2) // Referência ao botão de receita
//        btNavigateToRecipe2.setOnClickListener {
//            val intent = Intent(this, RecipeActivity2::class.java)
//            startActivity(intent)
//        }

        val btNavigateToProfile = findViewById<ImageButton>(R.id.button_to_profile) // Referência ao botão de perfil
        btNavigateToProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }



        private fun initRecyclerView() {
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.adapter = Adapter(getList())
        }

        private fun getList() = listOf(
            Recipe("Croissant", "Delicioso croissant de chocolate com recheio cremoso.", R.drawable.croissant),
            Recipe("Bolo de cenoura", "Bolo de cenoura fofinho com cobertura de chocolate.", R.drawable.bolo),
            Recipe("Croissant", "Delicioso croissant de chocolate com recheio cremoso.", R.drawable.croissant),
            Recipe("Bolo de cenoura", "Bolo de cenoura fofinho com cobertura de chocolate.", R.drawable.bolo),
            Recipe("Croissant", "Delicioso croissant de chocolate com recheio cremoso.", R.drawable.croissant),
            Recipe("Bolo de cenoura", "Bolo de cenoura fofinho com cobertura de chocolate.", R.drawable.bolo)
        )


}
