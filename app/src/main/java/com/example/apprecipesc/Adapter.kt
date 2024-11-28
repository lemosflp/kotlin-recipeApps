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
    private val recipeList: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_adapter, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]

        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipeList.size

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.adapterImage)
        private val recipeName: TextView = itemView.findViewById(R.id.adapterName)
        private val recipeDesc: TextView = itemView.findViewById(R.id.adapterDesc)
        private val buttonAccessRecipe: Button = itemView.findViewById(R.id.adapterButton)

        fun bind(recipe: Recipe) {
            recipeName.text = recipe.name
            recipeDesc.text = recipe.description
            recipeImage.setImageResource(recipe.imageResId)

            // Associe o clique ao botão, e não à imagem
            buttonAccessRecipe.setOnClickListener {
                onItemClick(recipe)
            }
        }
    }
}

