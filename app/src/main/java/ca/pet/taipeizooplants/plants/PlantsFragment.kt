package ca.pet.taipeizooplants.plants

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import ca.pet.taipeizooplants.MainActivity
import ca.pet.taipeizooplants.R
import ca.pet.taipeizooplants.data.Exhibit
import ca.pet.taipeizooplants.utils.getViewModelFactory
import ca.pet.taipeizooplants.utils.load
import kotlinx.android.synthetic.main.fragment_plants.*
import kotlinx.android.synthetic.main.item_plants.view.*

class PlantsFragment : Fragment() {

    private val viewModel by viewModels<PlantsViewModel> { getViewModelFactory() }

    private val args: PlantsFragmentArgs by navArgs()

    private lateinit var adapter: PlantsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.transition_thumbnail)
        return inflater.inflate(R.layout.fragment_plants, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initExhibitInfo(args.exhibit)

        initView()

        setCallback()

        viewModel.plantsListLiveData.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
            (activity as MainActivity).dismissLoading()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.start(args.dynamicTitle)
    }

    private fun initExhibitInfo(exhibit: Exhibit) {

        thumbnail.load(exhibit.E_Pic_URL)

        desView.text = exhibit.E_Info
        closedInfoView.text =
            if (exhibit.E_Memo.isNotBlank()) {
                exhibit.E_Memo
            } else {
                "無休館資訊"
            }
        categoryView.text = exhibit.E_Category
        linkView.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(exhibit.E_URL)
            startActivity(intent)
        }

    }

    private fun initView() {
        //Init View
        plantsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        plantsRecyclerView.setHasFixedSize(true)
        adapter = PlantsAdapter()
        plantsRecyclerView.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(plantsRecyclerView, false)
    }

    private fun setCallback() {
        adapter.onPlantsClickListener = { view, plants ->
            val action = PlantsFragmentDirections.actionPlantsFragmentToPlantsDetailFragment(
                plants.F_Name_Ch,
                plants
            )
            val extras = FragmentNavigatorExtras(
                view.thumbnail to "thumbnailPlants",
                view.nameView to "plantsName"
            )
            findNavController().navigate(action, extras)
        }
    }
}