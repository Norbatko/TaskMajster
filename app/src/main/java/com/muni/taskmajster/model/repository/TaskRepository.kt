package com.muni.taskmajster.model.repository

import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.muni.taskmajster.model.data.Task

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

    // Fetch Tasks by a list of IDs
    fun fetchTasksByIds(
        ids: List<String>,
        onResult: (List<Task>) -> Unit
    ) {
        if (ids.isEmpty()) {
            onResult(emptyList())
            return
        }
        val batchSize = 10
        val tasks = mutableListOf<Task>()
        val batches = ids.chunked(batchSize)
        var completedBatches = 0

        for (batch in batches) {
            collection.whereIn(FieldPath.documentId(), batch)
                .get()
                .addOnSuccessListener { result ->
                    val batchTasks = result.documents.mapNotNull { doc ->
                        doc.toObject(Task::class.java)?.copy(id = doc.id)
                    }
                    tasks.addAll(batchTasks)
                    completedBatches++
                    if (completedBatches == batches.size) {
                        onResult(tasks)
                    }
                }
                .addOnFailureListener {
                    completedBatches++
                    if (completedBatches == batches.size) {
                        onResult(tasks)
                    }
                }
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
            .addOnSuccessListener {
                // after deleting the task -> update all gameplans (remove deleted id from list)
                val gameplanRepo = GameplanRepository()
                gameplanRepo.fetchGameplans { gameplans ->
                    val affectedGameplans = gameplans.filter { it.listOfTaskIds.contains(taskId) }
                    if (affectedGameplans.isEmpty()) {
                        onResult(true)
                        return@fetchGameplans
                    }

                    var completed = 0
                    for (gameplan in affectedGameplans) {
                        val updated = gameplan.copy(listOfTaskIds = gameplan.listOfTaskIds.filter { it != taskId })
                        gameplanRepo.updateGameplan(updated) {
                            completed++
                            if (completed == affectedGameplans.size) {
                                onResult(true)
                            }
                        }
                    }
                }
            }
            .addOnFailureListener {
                onResult(false)
            }
    }
}
