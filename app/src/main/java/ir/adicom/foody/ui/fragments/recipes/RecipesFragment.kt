package ir.adicom.foody.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.todkars.shimmer.ShimmerRecyclerView
import ir.adicom.foody.R

class RecipesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)

        view.findViewById<ShimmerRecyclerView>(R.id.recyclerview).showShimmer()

        return view
    }
}