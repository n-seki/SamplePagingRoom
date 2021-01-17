package nseki.com.app.samplepagingroom.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun randomStringPage(): Flow<PagingData<RandomString>>
    suspend fun update(randomString: RandomString)
}
