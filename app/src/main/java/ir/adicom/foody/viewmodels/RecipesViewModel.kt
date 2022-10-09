package ir.adicom.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ir.adicom.foody.util.Constants
import ir.adicom.foody.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import ir.adicom.foody.util.Constants.Companion.QUERY_API_KEY
import ir.adicom.foody.util.Constants.Companion.QUERY_DIET
import ir.adicom.foody.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import ir.adicom.foody.util.Constants.Companion.QUERY_NUMBER
import ir.adicom.foody.util.Constants.Companion.QUERY_TYPE

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = "5"
        queries[QUERY_API_KEY] = Constants.API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}