package ir.adicom.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.adicom.foody.data.DataStoreRepository
import ir.adicom.foody.util.Constants
import ir.adicom.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import ir.adicom.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import ir.adicom.foody.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import ir.adicom.foody.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import ir.adicom.foody.util.Constants.Companion.QUERY_API_KEY
import ir.adicom.foody.util.Constants.Companion.QUERY_DIET
import ir.adicom.foody.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import ir.adicom.foody.util.Constants.Companion.QUERY_NUMBER
import ir.adicom.foody.util.Constants.Companion.QUERY_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    val readMealAndDietType = dataStoreRepository.readMealAndDietType

    fun saveMealAndDietType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
        }
    }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = Constants.API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}