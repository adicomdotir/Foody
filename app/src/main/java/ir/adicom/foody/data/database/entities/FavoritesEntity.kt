package ir.adicom.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.adicom.foody.models.Result
import ir.adicom.foody.util.Constants

@Entity(tableName = Constants.FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
) {
}