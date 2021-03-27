package ua.uswitch.testwork20.models

import androidx.room.*
import ua.uswitch.testwork20.BuildConfig

@Entity
data class Film(
    @PrimaryKey(autoGenerate = true) var ID: Int,
    var title: String,
    var description: String,
    var createDate: Long) {
    @Ignore
    var idPos: Int =0
}

@Dao
interface FilmDao {
    @get:Query("SELECT * FROM Film")
    val  all: List<Film>

    @Insert
    fun insert(film: Film)

    @Update
    fun update(film: Film)

    @Delete
    fun delete(film: Film)
}

@Database(entities = [Film::class], version = BuildConfig.VERSION_CODE)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun filmDao() : FilmDao
}