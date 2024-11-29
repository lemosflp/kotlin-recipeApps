package com.example.apprecipesc

import Recipe
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    var filteredRecipes = recipes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_adapter, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = filteredRecipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = filteredRecipes.size

    fun filter(query: String?) {
        filteredRecipes = if (query.isNullOrEmpty()) {
            recipes
        } else {
            recipes.filter { it.name.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val recipeImage: ImageView = view.findViewById(R.id.adapterImage)
        private val recipeName: TextView = itemView.findViewById(R.id.adapterName)
        private val recipeDescription: TextView = itemView.findViewById(R.id.adapterDesc)
        private val navigateButton: Button = itemView.findViewById(R.id.adapterButton)

        fun bind(recipe: Recipe) {
            recipeName.text = recipe.name
            recipeDescription.text = recipe.description
            recipeImage.setImageResource(recipe.imageResId)

            navigateButton.setOnClickListener {
                onRecipeClick(recipe)
            }
        }
    }
}





