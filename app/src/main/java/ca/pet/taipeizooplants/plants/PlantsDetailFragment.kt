package ca.pet.taipeizooplants.plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ca.pet.taipeizooplants.R
import ca.pet.taipeizooplants.utils.getViewModelFactory
import kotlinx.android.synthetic.main.fragment_plants_detail.*

class PlantsDetailFragment : Fragment() {

    private val viewModel by viewModels<PlantsDetailViewModel> { getViewModelFactory() }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plants_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_exhibitFragment)
        }
    }
}