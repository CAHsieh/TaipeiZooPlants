package ca.pet.taipeizooplants.exhibit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ca.pet.taipeizooplants.R
import ca.pet.taipeizooplants.data.Exhibit
import ca.pet.taipeizooplants.utils.DiffUtilCallbackImpl
import ca.pet.taipeizooplants.utils.load
import kotlinx.android.synthetic.main.item_exhibit.view.*

class ExhibitAdapter(
    private val exhibitList: MutableList<Exhibit> = mutableListOf()
) : RecyclerView.Adapter<ExhibitAdapter.ViewHolder>() {

    var onExhibitClickListener: ((View, Exhibit) -> Unit)? = null

    var needMoreDataCallback: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_exhibit, parent, false)
        )

    override fun getItemCount(): Int = exhibitList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exhibitList[position])

        if (position == exhibitList.size - 1) {
            needMoreDataCallback?.let { callback -> callback() }
        }
    }

    fun updateList(list: List<Exhibit>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallbackImpl(exhibitList, list))
        exhibitList.clear()
        exhibitList.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    inner class ViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(exhibit: Exhibit) {
            itemView.titleView.text = exhibit.E_Name
            itemView.desView.text = exhibit.E_Info
            itemView.desView.transitionName = "exhibitDes_${adapterPosition}"

            itemView.closedInfoView.text =
                if (exhibit.E_Memo.isNotBlank()) {
                    exhibit.E_Memo
                } else {
                    "無休館資訊"
                }

            itemView.thumbnail.load(exhibit.E_Pic_URL)
            itemView.thumbnail.transitionName = "thumbnailExhibit_${adapterPosition}"

            itemView.setOnClickListener {
                onExhibitClickListener?.let { callback -> callback(it, exhibit) }
            }
        }
    }

}