package ca.pet.taipeizooplants.plants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ca.pet.taipeizooplants.R
import ca.pet.taipeizooplants.data.Plants
import ca.pet.taipeizooplants.utils.DiffUtilCallbackImpl
import ca.pet.taipeizooplants.utils.load
import kotlinx.android.synthetic.main.item_plants.view.*

class PlantsAdapter(
    private val plantsList: MutableList<Plants> = mutableListOf()
) : RecyclerView.Adapter<PlantsAdapter.ViewHolder>() {

    var onPlantsClickListener: ((View, Plants) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_plants, parent, false)
        )

    override fun getItemCount(): Int = plantsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(plantsList[position])

    fun updateList(list: List<Plants>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallbackImpl(plantsList, list))
        plantsList.clear()
        plantsList.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(plants: Plants) {
            itemView.nameView.text = plants.F_Name_Ch
            itemView.nameView.transitionName = "plantName_${adapterPosition}"
            itemView.nickNameView.text = plants.F_AlsoKnown

            itemView.thumbnail.load(plants.F_Pic01_URL)
            itemView.thumbnail.transitionName = "thumbnailPlants_${adapterPosition}"

            itemView.setOnClickListener {
                onPlantsClickListener?.let { callback -> callback(it, plants) }
            }
        }
    }
}