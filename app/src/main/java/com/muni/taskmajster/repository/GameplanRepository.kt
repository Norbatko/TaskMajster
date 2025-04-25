import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.muni.taskmajster.data.Gameplan

class GameplanRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("gameplans")

    // Add a new Gameplan
    fun addGameplan(gameplan: Gameplan, onResult: (Boolean) -> Unit) {
        // Add without the ID first
        collection.add(gameplan.copy(id = "")) // id is empty for new docs
            .addOnSuccessListener { documentRef ->
                // Now update the Gameplan with the generated ID
                documentRef.update("id", documentRef.id)
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
                    val gp = doc.toObject(Gameplan::class.java)
                    gp?.copy(id = doc.id) // Set the id from Firestore
                }
                onResult(gameplans)
            }
    }


    // Update Gameplan's name or listOfTasks
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
