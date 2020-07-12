package ca.pet.taipeizooplants.exhibit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.pet.taipeizooplants.data.Exhibit
import ca.pet.taipeizooplants.data.source.local.IExhibitRepository

class ExhibitViewModel(
        private val exhibitRepository: IExhibitRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _exhibitListLiveData: MutableLiveData<List<Exhibit>> = MutableLiveData()
    val exhibitListLiveData: LiveData<List<Exhibit>> = _exhibitListLiveData


}