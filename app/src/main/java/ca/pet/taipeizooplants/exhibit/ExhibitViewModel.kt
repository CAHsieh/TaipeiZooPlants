package ca.pet.taipeizooplants.exhibit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.pet.taipeizooplants.data.Exhibit
import ca.pet.taipeizooplants.data.source.IExhibitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExhibitViewModel(
    private val exhibitRepository: IExhibitRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _exhibitListLiveData: MutableLiveData<List<Exhibit>> = MutableLiveData()
    val exhibitListLiveData: LiveData<List<Exhibit>> = _exhibitListLiveData


    fun start() {

        GlobalScope.launch(Dispatchers.IO) {
            exhibitRepository.retrieveExhibits { exhibits ->
                _exhibitListLiveData.postValue(exhibits.toMutableList())
            }
        }

    }

    fun loadMoreData() {
        GlobalScope.launch(Dispatchers.IO) {
            exhibitRepository.retrieveMoreExhibits { exhibits ->
                _exhibitListLiveData.postValue(exhibits.toMutableList())
            }
        }
    }
}