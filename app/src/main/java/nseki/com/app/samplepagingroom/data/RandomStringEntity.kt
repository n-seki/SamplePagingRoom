package nseki.com.app.samplepagingroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "random_strings"
)
data class RandomStringEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val string: String,
    val score: Int
)
