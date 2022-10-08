package ir.adicom.foody.models

import com.google.gson.annotations.SerializedName

class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>
)