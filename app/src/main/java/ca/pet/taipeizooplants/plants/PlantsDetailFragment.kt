package ca.pet.taipeizooplants.plants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ca.pet.taipeizooplants.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_plants_detail.*

class PlantsDetailFragment : Fragment() {

    private val args: PlantsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plants_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plants = args.plants

        Glide.with(requireContext())
            .load(plants.F_Pic01_URL)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .into(thumbnail)

        nameZh.text = plants.F_Name_Ch
        nameEn.text = plants.F_Name_En
        nickName.text = plants.F_AlsoKnown
        brief.text = plants.F_Brief
        feature.text = plants.F_Feature
        functional.text = plants.F_FunctionApplication
        lastUpdateDate.text = plants.F_Update
    }
}