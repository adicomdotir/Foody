package ir.adicom.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.adicom.foody.models.FoodRecipe
import ir.adicom.foody.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}