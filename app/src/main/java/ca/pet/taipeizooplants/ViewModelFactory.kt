package ca.pet.taipeizooplants

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ca.pet.taipeizooplants.data.source.IExhibitRepository
import ca.pet.taipeizooplants.data.source.IPlantsRepository
import ca.pet.taipeizooplants.exhibit.ExhibitViewModel
import ca.pet.taipeizooplants.plants.PlantsDetailViewModel
import ca.pet.taipeizooplants.plants.PlantsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
        private val exhibitsRepository: IExhibitRepository,
        private val plantsRepository: IPlantsRepository,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle) = with(modelClass) {
        when {
            isAssignableFrom(ExhibitViewModel::class.java) ->
                ExhibitViewModel(exhibitsRepository, handle)
            isAssignableFrom(PlantsViewModel::class.java) ->
                PlantsViewModel(plantsRepository, handle)
            isAssignableFrom(PlantsDetailViewModel::class.java) ->
                PlantsDetailViewModel(plantsRepository, handle)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}