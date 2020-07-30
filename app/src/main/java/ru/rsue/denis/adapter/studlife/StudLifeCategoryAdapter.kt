package ru.rsue.denis.adapter.studlife

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.studlifecategory_item_row_layout.view.*
import ru.rsue.denis.R
import ru.rsue.denis.model.studlife.GetStudLifeCategory


class StudLifeCategoryAdapter(
    private val studLifeCategoryItem: List<GetStudLifeCategory>,
    private val itemClick: (GetStudLifeCategory) -> Unit) :
    RecyclerView.Adapter<StudLifeCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.studlifecategory_item_row_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (studLifeCategoryItem.isNotEmpty())
            studLifeCategoryItem.size
        else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val category = studentlivecategory_item[position]
        //holder.short_StudLiveCategory_name.text=category.short_StudLiveCategory_name
        Log.d("-----------------------------", "$+")
        holder.bind(studLifeCategoryItem[position], itemClick)

    }

    class ViewHolder(studlifecategoryItemView: View) : RecyclerView.ViewHolder(studlifecategoryItemView) {
        //val short_StudLiveCategory_name: TextView = itemView.short_StudLiveCategory_name
        fun bind(item: GetStudLifeCategory, itemClick: (GetStudLifeCategory) -> Unit){
            itemView.name_studLifeCategory.text = item.name_studLifeCategory
            itemView.setOnClickListener(){
                itemClick(item)
            }
        }
    }
}
