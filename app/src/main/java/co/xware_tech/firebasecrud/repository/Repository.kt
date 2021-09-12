package co.xware_tech.firebasecrud.repository

import android.content.ContentValues.TAG
import android.util.Log
import arrow.core.Either
import co.xware_tech.firebasecrud.StudentData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class Repository {
    private val db= FirebaseFirestore.getInstance()

    private val docRef=db.collection("students")
    fun addStudentName(name:String){
        docRef.add(hashMapOf("name" to name)).addOnSuccessListener {
            Log.d(TAG, "DocumentSnapshot successfully written!")
        }
            .addOnFailureListener {
                    e -> Log.w(TAG, "Error writing document", e)
            }
    }

    suspend fun getName():Either<Throwable,List<StudentData>>{
        return try {
            val result = db.collection("students")
                .get()
                .await().documents.map {
                    StudentData(
                        id=it.id,
                        name = it.get("name").toString()
                    )
                }
            Either.right(result)
        }catch (e:Exception) {
            Either.left(e)
        }
    }
    fun deleteName(id:String){
        db.collection("students").document(id)
            .delete()
    }
    fun updateName(id:String,updatedName:String){
        db.collection("students").document(id)
            .update("name",updatedName)
    }
}