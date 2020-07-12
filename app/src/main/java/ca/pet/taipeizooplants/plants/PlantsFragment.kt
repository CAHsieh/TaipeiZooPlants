package ca.pet.taipeizooplants.plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ca.pet.taipeizooplants.R
import ca.pet.taipeizooplants.utils.getViewModelFactory

class PlantsFragment : Fragment() {

    private val viewModel by viewModels<PlantsViewModel> { getViewModelFactory() }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plants, container, false)
    }

}