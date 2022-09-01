package com.geekbrains.myexamapp.ui.classes_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.myexamapp.R
import com.geekbrains.myexamapp.databinding.ItemClassesFragmentLayoutBinding
import com.geekbrains.myexamapp.domain.Class

class ClassesRecyclerAdapter :
    RecyclerView.Adapter<ClassesRecyclerAdapter.ClassesViewHolder>() {

    private var dataList: MutableList<Class> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesViewHolder {
        val binding = ItemClassesFragmentLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ClassesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassesViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ClassesViewHolder(private val binding: ItemClassesFragmentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Class, position: Int) {
            binding.apply {
                title.text = data.title
                time.text = data.time
                if (position == 0) openInBtn.visibility = View.VISIBLE
                when (data.title) {
                    "History" -> icon.setImageResource(R.drawable.history)
                    "Sport" -> icon.setImageResource(R.drawable.sports)
                    "Physics" -> icon.setImageResource(R.drawable.physics)
                    "Math" -> icon.setImageResource(R.drawable.math)
                    else -> icon.setImageResource(R.drawable.literature)
                }
            }
        }
    }

    fun setData(data: List<Class>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }
}