package com.example.cinemax.firestore

import android.util.Log
import com.example.cinemax.models.User
import com.example.cinemax.ui.Signup
import com.example.cinemax.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore

class Firestore {

    private var mFirestore = FirebaseFirestore.getInstance()

    fun storeUserDetailsToFirestore(activity: Signup, user: User){
        mFirestore.collection(Constants.USERS_COLLECTION)
            .document().set(user)
            .addOnSuccessListener {
                activity.storeUserDataToFirestoreSuccess()
            }
            .addOnFailureListener {
                Log.e("Error", "Error while saving user data to firestore")
            }
    }
}