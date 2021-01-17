package nseki.com.app.samplepagingroom.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import nseki.com.app.samplepagingroom.domain.Repository

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsRepository(repository: RepositoryImpl): Repository
}
