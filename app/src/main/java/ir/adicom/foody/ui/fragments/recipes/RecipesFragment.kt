package ir.adicom.foody.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ir.adicom.foody.viewmodels.MainViewModel
import ir.adicom.foody.R
import ir.adicom.foody.adapters.RecipesAdapter
import ir.adicom.foody.util.Constants.Companion.API_KEY
import ir.adicom.foody.util.NetworkResult
import ir.adicom.foody.viewmodels.RecipesViewModel

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_recipes, container, false)

        setupRecyclerView()
        requestApiData()

        return mView
    }

    private fun setupRecyclerView() {
        val recyclerview = mView.findViewById<ShimmerRecyclerView>(R.id.recyclerview)
        recyclerview.adapter = mAdapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        mView.findViewById<ShimmerRecyclerView>(R.id.recyclerview).showShimmer()
    }

    private fun hideShimmerEffect() {
        mView.findViewById<ShimmerRecyclerView>(R.id.recyclerview).hideShimmer()
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }
}
