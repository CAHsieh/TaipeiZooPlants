package ca.pet.taipeizooplants.plants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ca.pet.taipeizooplants.R
import ca.pet.taipeizooplants.data.Plants
import ca.pet.taipeizooplants.utils.DiffUtilCallbackImpl
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_plants.view.*

class PlantsAdapter(
    private val plantsList: MutableList<Plants> = mutableListOf()
) : RecyclerView.Adapter<PlantsAdapter.ViewHolder>() {

    var onPlantsClickListener: ((Plants) -> Unit)? = null

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
            itemView.nickNameView.text = plants.F_AlsoKnown

            Glide.with(itemView.context)
                .load(plants.F_Pic01_URL)
                .placeholder(R.drawable.loading)
                .fitCenter()
                .into(itemView.thumbnail)

            itemView.setOnClickListener {
                onPlantsClickListener?.let { callback -> callback(plants) }
            }
        }
    }
}