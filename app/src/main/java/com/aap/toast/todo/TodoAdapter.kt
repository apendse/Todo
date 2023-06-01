package com.aap.toast.todo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aap.toast.todo.data.TodoItem
import com.aap.toast.todo.databinding.TodoRowBinding

val  DIFF_CALLBACK =
              object: DiffUtil.ItemCallback<TodoItem>() {
                  override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                      return oldItem == newItem
                  }

                  override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
                      return oldItem == newItem
                  }

              }

interface ChangeListener {
    fun onChanged(index: Int, isChecked: Boolean)
}
class TodoAdapter(private val changeListener: ChangeListener): ListAdapter<TodoItem, TodoViewHolder>(DIFF_CALLBACK), ChangeListener {

    override fun onChanged(index: Int, isChecked: Boolean) {
        changeListener.onChanged(index, isChecked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(this, binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.title.text = item.title
        if (position % 2 == 1) {
            holder.binding.root.background = ColorDrawable(Color.LTGRAY)
        } else {
            holder.binding.root.background = ColorDrawable(Color.WHITE)
        }
        holder.binding.doneStatus.isChecked = item.isDone
    }

}


class TodoViewHolder(val changeListener: ChangeListener, val binding: TodoRowBinding): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.doneStatus.setOnCheckedChangeListener { buttonView, isChecked ->
            changeListener.onChanged(adapterPosition, isChecked)
        }
    }
}