package com.example.jetpacknav

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(
    val fragment: FirstFragment,
    var recipes: ArrayList<Recipe>,
    val listner: Listener
) :RecyclerView.Adapter<RecipeAdapter.AnimalViewHolder>()  {

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var textViewMeal : TextView = itemView.findViewById(R.id.nameOfMeal)
        var textViewDish : TextView = itemView.findViewById(R.id.nameOfDish)
        var image : ImageView = itemView.findViewById(R.id.imageOfDish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recipe_card,parent,false)
        return AnimalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }


    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listner.onClick(position)
        }
        Glide.with(fragment)
            .load(recipes.get(position).image).fitCenter().into(holder.image)
        holder.textViewMeal.text = recipes.get(position).meal
        holder.textViewDish.text = recipes.get(position).dish
    }

    interface Listener {
        fun onClick(itemView: Int)
    }
}