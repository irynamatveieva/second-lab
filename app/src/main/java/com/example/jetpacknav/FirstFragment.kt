package com.example.jetpacknav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpacknav.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), RecipeAdapter.Listener {

    private var _binding : FragmentFirstBinding? = null
    private val binding get() = _binding!!

    var recipes = ArrayList<Recipe>()
    lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var breakfast = Recipe("Breakfast", "Omelette",getString(R.string.OmeletteRecipe) , getString(R.string.omelette))
        var dinner = Recipe("Dinner", "Mushroom soup",getString(R.string.SoupRecipe), getString(R.string.soup))
        var supper = Recipe ("Supper", "Lasagna", getString(R.string.LasagnaRecipe),getString(R.string.lasagna))

        recipes.add(breakfast)
        recipes.add(dinner)
        recipes.add(supper)
        adapter = RecipeAdapter(this ,recipes, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.rV.layoutManager = layoutManager
        binding.rV.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(itemView: Int) {
        val bundle = bundleOf(SecondFragment.numberInArray to itemView,
            SecondFragment.meal to recipes.get(itemView).meal,
            SecondFragment.dish to recipes.get(itemView).dish,
            SecondFragment.recipeOfDish to recipes.get(itemView).recipeOfDish,
            SecondFragment.url to recipes.get(itemView).image)
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
    }


}

