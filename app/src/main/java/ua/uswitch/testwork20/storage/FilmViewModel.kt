package ua.uswitch.testwork20.storage

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.uswitch.testwork20.models.Film
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmRepository: FilmRepository): ViewModel() {

    private val _response = MutableLiveData<List<Film>>()
    val response: LiveData<List<Film>> = _response

    init {

        getFilm()
    }

    private fun getFilm() {

        viewModelScope.launch() {
            val list = filmRepository.fromDBFilms()

            _response.value = list

        }
    }
}