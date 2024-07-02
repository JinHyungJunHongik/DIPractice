package com.example.dipractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dipractice.databinding.ItemDataBinding


class StudentPagingAdapter : PagingDataAdapter<Student, StudentPagingAdapter.StudentViewHolder>(
    object : DiffUtil.ItemCallback<Student>(){
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem == newItem
        }

    }
) {

    class StudentViewHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Student){
            binding.itemId.text = data.id.toString()
            binding.itemName.text = data.name
        }
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

}