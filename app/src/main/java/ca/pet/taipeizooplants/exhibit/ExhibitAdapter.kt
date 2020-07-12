package ca.pet.taipeizooplants.exhibit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ca.pet.taipeizooplants.R
import ca.pet.taipeizooplants.data.Exhibit
import ca.pet.taipeizooplants.utils.DiffUtilCallbackImpl

class ExhibitAdapter(
        private val exhibitList: MutableList<Exhibit> = mutableListOf()
) : RecyclerView.Adapter<ExhibitAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_exhibit, parent, false))

    override fun getItemCount(): Int = exhibitList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exhibitList[position])
    }

    fun updateList(list: List<Exhibit>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallbackImpl<Exhibit>(exhibitList, list))
        exhibitList.clear()
        exhibitList.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    inner class ViewHolder constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

        fun bind(exhibit: Exhibit) {

        }
    }
}