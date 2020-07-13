package ca.pet.taipeizooplants.exhibit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.pet.taipeizooplants.IViewModel
import ca.pet.taipeizooplants.data.Exhibit
import ca.pet.taipeizooplants.data.source.IExhibitRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExhibitViewModel(
    private val exhibitRepository: IExhibitRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), IViewModel {

    private val _exhibitListLiveData: MutableLiveData<List<Exhibit>> = MutableLiveData()
    val exhibitListLiveData: LiveData<List<Exhibit>> = _exhibitListLiveData


    override fun start() {

        GlobalScope.launch {
            exhibitRepository.retrieveExhibits { exhibits ->
                _exhibitListLiveData.postValue(exhibits.toMutableList())
            }
        }

    }

    override fun loadMoreData() {
        GlobalScope.launch {
            exhibitRepository.retrieveMoreExhibits { exhibits ->
                _exhibitListLiveData.postValue(exhibits.toMutableList())
            }
        }
    }
}