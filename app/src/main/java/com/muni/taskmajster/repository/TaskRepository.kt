package com.muni.taskmajster.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.muni.taskmajster.data.Task

class TaskRepository {
    private val db = Firebase.firestore
    private val collection = db.collection("tasks")

    // Add a new Task
    fun addTask(task: Task, onResult: (Boolean) -> Unit) {
        collection.add(task.copy(id = "")) // Add without id
            .addOnSuccessListener { docRef ->
                // Update the Task with the generated ID
                docRef.update("id", docRef.id)
                    .addOnSuccessListener { onResult(true) }
                    .addOnFailureListener { onResult(false) }
            }
            .addOnFailureListener { onResult(false) }
    }

    // Fetch all Tasks
    fun fetchTasks(onResult: (List<Task>) -> Unit) {
        collection.get()
            .addOnSuccessListener { result ->
                val tasks = result.documents.mapNotNull { doc ->
                    doc.toObject(Task::class.java)?.copy(id = doc.id)
                }
                onResult(tasks)
            }
    }

    // Update a Task
    fun updateTask(task: Task, onResult: (Boolean) -> Unit) {
        collection.document(task.id).set(task)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    // Delete a Task
    fun deleteTask(taskId: String, onResult: (Boolean) -> Unit) {
        collection.document(taskId).delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
}
