package ca.pet.taipeizooplants.plants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.pet.taipeizooplants.data.Plants
import ca.pet.taipeizooplants.data.source.IPlantsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlantsViewModel(
    private val plantsRepository: IPlantsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _plantsListLiveData: MutableLiveData<List<Plants>> = MutableLiveData()
    val plantsListLiveData: LiveData<List<Plants>> = _plantsListLiveData

    fun start(location: String) {
        GlobalScope.launch(Dispatchers.IO) {
            plantsRepository.retrievePlants(location) { plants ->
                _plantsListLiveData.postValue(plants.toMutableList())
            }
        }
    }
}