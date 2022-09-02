package com.geekbrains.myexamapp.ui.home_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.myexamapp.R
import com.geekbrains.myexamapp.databinding.ItemHomeworkLayoutBinding
import com.geekbrains.myexamapp.domain.HomeWork

class HomeworkRecyclerAdapter:
    RecyclerView.Adapter<HomeworkRecyclerAdapter.HomeworkViewHolder>() {

        private var dataList: MutableList<HomeWork> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkViewHolder {
            val binding = ItemHomeworkLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return HomeworkViewHolder(binding)
        }

        override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
            holder.bind(dataList[position])
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        inner class HomeworkViewHolder(private val binding: ItemHomeworkLayoutBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(data: HomeWork) {
                binding.apply {
                    title.text = data.title
                    when (data.title) {
                        "History" -> icon.setImageResource(R.drawable.history)
                        "Sport" -> icon.setImageResource(R.drawable.sports)
                        "Physics" -> icon.setImageResource(R.drawable.physics)
                        "Math" -> icon.setImageResource(R.drawable.math)
                        else -> icon.setImageResource(R.drawable.literature)
                    }
                    time.text = data.time
                    description.text = data.description
//                root.setOnClickListener {
//
//                }
                }
            }
        }

        fun setData(data: List<HomeWork>) {
            dataList = data.toMutableList()
            notifyDataSetChanged()
        }
}