package ca.pet.taipeizooplants.plants

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.pet.taipeizooplants.data.source.IPlantsRepository

class PlantsViewModel(
        private val plantsRepository: IPlantsRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {
}