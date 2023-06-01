package com.aap.toast.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.aap.toast.todo.data.TodoItem
import com.aap.toast.todo.databinding.ActivityMainBinding
import com.aap.toast.todo.databinding.AddTodoDialogBinding
import com.aap.toast.todo.viewmodel.TodoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TodoAdapter
    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initList()
        initAction()
        todoViewModel.todoList.observe(this) {
            addItems(it)
        }
    }

    private fun addItems(todos: List<TodoItem>) {
        adapter.submitList(todos)
    }

    private fun initAction() {
        binding.createTodo.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogBinding = AddTodoDialogBinding.inflate(layoutInflater)
        dialogBuilder.setView(dialogBinding.root)
        val dialog = dialogBuilder.create()


        dialogBinding.ok.setOnClickListener {
            val title = dialogBinding.title.text.toString()
            todoViewModel.addToDoItem(TodoItem(title, false))
            dialog.dismiss()
        }
        dialog.show()

    }

    private fun initList() {
        binding.todoList.layoutManager = LinearLayoutManager(this)
        adapter = TodoAdapter(object: ChangeListener {
            override fun onChanged(index: Int, isChecked: Boolean) {
                todoViewModel.updateItem(index, isChecked)
            }
        })


        binding.todoList.adapter = adapter

    }
}