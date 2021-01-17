package nseki.com.app.samplepagingroom.data

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface RandomStringDao {

    @Query("SELECT * FROM random_strings ORDER BY string")
    fun selectAll(): PagingSource<Int, RandomStringEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(randomStringEntity: List<RandomStringEntity>)

    @Query("UPDATE random_strings SET score = :score WHERE string = :randomString")
    suspend fun updateScore(randomString: String, score: Int)

    @Query("DELETE FROM random_strings WHERE string = :randomString")
    suspend fun deleteByString(randomString: String)
}
