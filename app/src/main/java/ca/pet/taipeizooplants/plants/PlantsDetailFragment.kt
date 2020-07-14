package ca.pet.taipeizooplants.plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import ca.pet.taipeizooplants.R
import ca.pet.taipeizooplants.utils.load
import kotlinx.android.synthetic.main.fragment_plants_detail.*

class PlantsDetailFragment : Fragment() {

    private val args: PlantsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.transition_thumbnail)
        return inflater.inflate(R.layout.fragment_plants_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plants = args.plants

        thumbnail.load(plants.F_Pic01_URL)

        nameZh.text = plants.F_Name_Ch
        nameEn.text = plants.F_Name_En
        nickName.text = plants.F_AlsoKnown
        brief.text = plants.F_Brief
        feature.text = plants.F_Feature
        functional.text = plants.F_FunctionApplication
        lastUpdateDate.text = plants.F_Update
    }
}