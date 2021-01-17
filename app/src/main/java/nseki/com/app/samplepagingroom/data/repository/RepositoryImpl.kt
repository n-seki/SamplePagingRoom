package nseki.com.app.samplepagingroom.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nseki.com.app.samplepagingroom.data.RandomStringDao
import nseki.com.app.samplepagingroom.data.RandomStringEntity
import nseki.com.app.samplepagingroom.domain.RandomString
import nseki.com.app.samplepagingroom.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val randomStringDao: RandomStringDao
) : Repository {

    override fun randomStringPage(): Flow<PagingData<RandomString>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = true)) {
            randomStringDao.selectAll()
        }.flow.map {
            it.map { entity ->
                RandomString(
                    string = entity.string,
                    score = entity.score
                )
            }
        }
    }

    override suspend fun update(randomString: RandomString) {
        randomStringDao.updateScore(randomString.string, randomString.score)
    }
}
