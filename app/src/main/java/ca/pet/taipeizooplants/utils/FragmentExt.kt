package ca.pet.taipeizooplants.utils

import androidx.fragment.app.Fragment
import ca.pet.taipeizooplants.TaipeiZooPlantsApplication
import ca.pet.taipeizooplants.ViewModelFactory

fun Fragment.getViewModelFactory():ViewModelFactory{
    val application = (requireContext().applicationContext as TaipeiZooPlantsApplication)
    return ViewModelFactory(
            application.exhibitsRepository,
            application.plantsRepository,
            this
    )
}