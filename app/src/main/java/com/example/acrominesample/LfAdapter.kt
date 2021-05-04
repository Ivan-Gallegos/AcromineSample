package com.example.acrominesample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.acrominesample.databinding.ListItemBinding
import com.example.acrominesample.models.LfsItem

class LfAdapter(private val list: List<LfsItem?>?) :
    RecyclerView.Adapter<LfAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: ListItemBinding? = DataBindingUtil.bind(view)

        fun bind(lfsItem: LfsItem) {
            binding?.lfItem = lfsItem
            binding?.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list?.get(position)?.let { holder.bind(it) }
    }
}