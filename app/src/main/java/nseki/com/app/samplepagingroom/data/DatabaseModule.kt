package nseki.com.app.samplepagingroom.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(
            context
        )
    }

    @Provides
    fun provideDao(appDatabase: AppDatabase): RandomStringDao {
        return appDatabase.randomStringDao()
    }
}
