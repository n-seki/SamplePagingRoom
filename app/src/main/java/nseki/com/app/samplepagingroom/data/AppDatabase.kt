package nseki.com.app.samplepagingroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    version = 1,
    entities = [
        RandomStringEntity::class
    ],
    exportSchema = false  // this is sample app
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun randomStringDao(): RandomStringDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "app-database"

        fun getInstance(context: Context): AppDatabase {
            val temp =
                INSTANCE
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val database = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(CALL_BACK)
                    .build()
                INSTANCE = database
                return database
            }
        }

        private val CALL_BACK = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                fun getRandomString(length: Int = 10) : String {
                    val allowedChars = ('A'..'Z') + ('a'..'z')
                    return (1..length)
                        .map { allowedChars.random() }
                        .joinToString("")
                }
                try {
                    db.beginTransaction()
                    repeat(1000) {
                        val randomString = getRandomString(length = 7)
                        db.execSQL(
                            "INSERT INTO random_strings VALUES (NULL, ?, ?)",
                            arrayOf(randomString, 0)
                        )
                    }
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }
            }
        }
    }
}
