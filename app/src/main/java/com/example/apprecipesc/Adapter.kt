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
    private var recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var filterRecipes: List<Recipe> = recipes.toList()

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.adapterImage)
        private val recipeName: TextView = itemView.findViewById(R.id.adapterName)
        private val recipeDesc: TextView = itemView.findViewById(R.id.adapterDesc)
        private val buttonAccessRecipe: Button = itemView.findViewById(R.id.adapterButton)

        fun bind(recipe: Recipe) {
            recipeName.text = recipe.name
            recipeDesc.text = recipe.description
            recipeImage.setImageResource(recipe.imageResId)

            buttonAccessRecipe.setOnClickListener {
                onItemClick(recipe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_adapter, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(filterRecipes[position])
    }

    override fun getItemCount(): Int = filterRecipes.size

    fun filter(query: String?) {
        filterRecipes = if (query.isNullOrEmpty()) {
            recipes
        } else {
            recipes.filter { it.name.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}