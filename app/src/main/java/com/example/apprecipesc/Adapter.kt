package com.example.apprecipesc

import Recipe
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val myList: List<Recipe>
) : RecyclerView.Adapter<Adapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_adapter, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = myList[position]
        holder.adapterName.text = recipe.name
        holder.adapterDesc.text = recipe.description
        holder.image.setImageResource(recipe.imageResId)
    }

    override fun getItemCount() = myList.size

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val adapterName: TextView = itemView.findViewById(R.id.adapterName)
        val adapterDesc: TextView = itemView.findViewById(R.id.adapterDesc)
        val image: android.widget.ImageView = itemView.findViewById(R.id.adapterImage)  // ImageView para a imagem da receita

    }
}
