package ca.pet.taipeizooplants.exhibit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ca.pet.taipeizooplants.R
import ca.pet.taipeizooplants.utils.getViewModelFactory
import kotlinx.android.synthetic.main.fragment_exhibit.*

class ExhibitFragment : Fragment() {

    private val viewModel by viewModels<ExhibitViewModel> { getViewModelFactory() }

    private lateinit var adapter: ExhibitAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exhibit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exhibitRecyclerView.layoutManager = LinearLayoutManager(context)
        exhibitRecyclerView.setHasFixedSize(true)
        adapter = ExhibitAdapter()
        exhibitRecyclerView.adapter = adapter

        viewModel.exhibitListLiveData.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
    }
}