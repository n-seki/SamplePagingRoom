package nseki.com.app.samplepagingroom.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.launch
import nseki.com.app.samplepagingroom.domain.RandomString
import nseki.com.app.samplepagingroom.domain.Repository

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel()  {

    val randomStrings = repository.randomStringPage()
        .cachedIn(viewModelScope)
        .asLiveData()

    fun update(updatedRandomString: RandomString) {
        viewModelScope.launch {
            repository.update(updatedRandomString)
        }
    }
}
