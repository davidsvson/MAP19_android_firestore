package com.example.firestoreintro

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        val item = Item("lök", false, "grönsak")

        // lägger till vår item mde ett autogeneretat unikt document namn
      //  db.collection("items").add(item)

        //lägger till eller ändrar på documentet som heter "onion"
      //  db.collection("items").document("onion").set(item)

        val shoppingItems = mutableListOf<Item>()

        val itemsRef = db.collection("items")

        // läser vår data _en_ gång
     /*   itemsRef.get().addOnSuccessListener { documentSnapshot ->
            for(document in documentSnapshot.documents) {
                val newItem = document.toObject(Item::class.java)
                if (newItem != null)
                    shoppingItems.add(newItem!!)
                println("David : ${newItem}")
            }
            println("David : listlength2: ${shoppingItems.size}")
        } */

        itemsRef.addSnapshotListener { snapshot, e ->
            if( snapshot != null ) {
                shoppingItems.clear()
                for(document in snapshot.documents) {
                    val newItem = document.toObject(Item::class.java)
                    if (newItem != null)
                        shoppingItems.add(newItem!!)
                    println("David : ${newItem}")
                }
            }
        }


        println("David : listlength ${shoppingItems.size}")




        //---------------------- kod från exempel från firebase guiden

        // Create a new user with a first and last name
     /*   val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815


        // Add a new document with a generated ID
        db.collection("users").add(user)
                .addOnSuccessListener {
                    documentReference -> Log.d("David", "DocumentSnapshot added with ID: " + documentReference.id)
                }
                .addOnFailureListener { e -> Log.w("David", "Error adding document", e) }
    */

/*
        db.collection("users")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d(
                            "David",
                            document.id + " => " + document.data
                        )
                    }
                } else {
                    Log.w(
                        "David",
                        "Error getting documents.",
                        task.exception
                    )
                }
            }
            */

    }
}
