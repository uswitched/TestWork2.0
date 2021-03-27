package ua.uswitch.testwork20.storage

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.uswitch.testwork20.models.Film
import ua.uswitch.testwork20.models.FilmDao
import java.util.*
import javax.inject.Inject

class FilmRepository @Inject constructor(private val filmDao: FilmDao){

    private suspend fun initDB() {
        withContext(Dispatchers.IO) {
            if(filmDao.all.isEmpty())
                for(i in 1..20)
                    filmDao.insert(
                        Film(
                            i,
                            "Film $i",
                            "Description $i",
                            createDate = Date().time
                        )
                    )
        }
    }

    suspend fun fromDBFilms(): List<Film> = withContext(Dispatchers.IO) {
        initDB()
        filmDao.all
    }
}