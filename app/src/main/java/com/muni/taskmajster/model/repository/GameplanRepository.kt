package com.muni.taskmajster.model.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.muni.taskmajster.model.data.Gameplan

class GameplanRepository {
    private val db = Firebase.firestore
    private val collection = db.collection("gameplans")

    // Add a new Gameplan
    fun addGameplan(gameplan: Gameplan, onResult: (Boolean) -> Unit) {
        collection.add(gameplan.copy(id = "")) // Add without id
            .addOnSuccessListener { docRef ->
                // Update the Gameplan with the generated ID
                docRef.update("id", docRef.id)
                    .addOnSuccessListener { onResult(true) }
                    .addOnFailureListener { onResult(false) }
            }
            .addOnFailureListener { onResult(false) }
    }

    // Fetch all Gameplans
    fun fetchGameplans(onResult: (List<Gameplan>) -> Unit) {
        collection.get()
            .addOnSuccessListener { result ->
                val gameplans = result.documents.mapNotNull { doc ->
                    doc.toObject(Gameplan::class.java)?.copy(id = doc.id)
                }
                onResult(gameplans)
            }
    }

    // Fetch desired Gameplan
    fun fetchGameplanById(id: String, onResult: (Gameplan?) -> Unit) {
        collection.document(id)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Convert document to Gameplan and set its id
                    val gameplan = document.toObject(Gameplan::class.java)?.copy(id = document.id)
                    onResult(gameplan)
                } else {
                    onResult(null)
                }
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    // Update a Gameplan
    fun updateGameplan(gameplan: Gameplan, onResult: (Boolean) -> Unit) {
        collection.document(gameplan.id).set(gameplan)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    // Delete a Gameplan
    fun deleteGameplan(gameplanId: String, onResult: (Boolean) -> Unit) {
        collection.document(gameplanId).delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
}
