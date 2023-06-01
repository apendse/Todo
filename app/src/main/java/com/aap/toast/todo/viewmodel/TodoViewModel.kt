package com.aap.toast.todo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aap.toast.todo.data.TodoItem

class TodoViewModel: ViewModel() {
    private val _todoList = MutableLiveData<List<TodoItem>>(mutableListOf())

    val todoList: LiveData<List<TodoItem>>
        get() = _todoList

    init {
        val list = _todoList.value as? MutableList ?: mutableListOf()
        list.add(TodoItem("Work0", false))
        list.add(TodoItem("Work1", false))
        list.add(TodoItem("Work2", false))
        list.add(TodoItem("Work3", false))
        list.add(TodoItem("Work4", false))
        list.add(TodoItem("Work5", false))
        list.add(TodoItem("Work10", false))
        list.add(TodoItem("Work11", false))
        list.add(TodoItem("Work12", false))
        list.add(TodoItem("Work13", false))
        list.add(TodoItem("Work14", false))
        list.add(TodoItem("Work15", false))
        list.add(TodoItem("Work20", false))
        list.add(TodoItem("Work21", false))
        list.add(TodoItem("Work22", false))
        list.add(TodoItem("Work23", false))
    }
    fun addToDoItem(item: TodoItem) {
        val old = _todoList.value ?: emptyList()
        val newList = ArrayList<TodoItem>()
        newList.addAll(old)
        newList.add(item)
        _todoList.value = newList
    }

    fun updateItem(index: Int, isChecked: Boolean) {
        val old = _todoList.value ?: emptyList()
        val newList = ArrayList<TodoItem>(old)
        newList[index].isDone = isChecked
        _todoList.value = newList
    }
}