package ru.rsue.denis.adapter.studlife

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.studlife_item_row_layout.view.*
import ru.rsue.denis.R
import ru.rsue.denis.model.studlife.GetStudLife


class StudLifeAdapter(
    private val studLifeItem: List<GetStudLife>) :
    RecyclerView.Adapter<StudLifeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.studlife_item_row_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (studLifeItem.isNotEmpty())
            studLifeItem.size
        else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val studlifes = studLifeItem[position]
        holder.short_name_studLife.text = studlifes.short_name_studLife
        holder.description_studLife.text = studlifes.description_studLife
        holder.basement_studLife.text = studlifes.basement_studLife
        holder.phone_studLife.text = studlifes.phone_studLife

    }

    class ViewHolder(studlifeItemView: View) : RecyclerView.ViewHolder(studlifeItemView) {
        //val short_StudLiveCategory_name: TextView = itemView.short_StudLiveCategory_name
        val short_name_studLife: TextView = itemView.short_name_studLife
        val description_studLife: TextView = itemView.description_studLife
        val basement_studLife: TextView = itemView.basement_studLife
        val phone_studLife: TextView = itemView.phone_studLife
    }
}
