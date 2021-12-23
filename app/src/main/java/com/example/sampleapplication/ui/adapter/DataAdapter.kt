package com.example.sampleapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapplication.R
import com.example.sampleapplication.model.DetailsData
import com.squareup.picasso.Picasso

class DataAdapter(private var data: List<DetailsData>, var type: Int) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgUser: ImageView = itemView.findViewById(R.id.imgUser)
        private val desc: TextView = itemView.findViewById(R.id.txtDesc)
        private val title: TextView = itemView.findViewById(R.id.txtTitle)
        fun bind(detailsData: DetailsData, type: Int) {

            if (type == 1)
                desc.text = detailsData.description
            else
                desc.text = detailsData.city

            title.text = detailsData.name
            Picasso.get().load(detailsData.image).into(imgUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view: View = if (type == 1) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_first_list_data, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_second_list_data, parent, false)
        }

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], type)
    }

    override fun getItemCount(): Int =
        data.size

    //add data to list and update
    fun setListData(data: List<DetailsData>?) {
        if (data != null) {
            this.data = data
        }
        notifyDataSetChanged()
    }


}