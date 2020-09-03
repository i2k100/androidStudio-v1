package com.myappcompany.imran.sanpchat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null
    val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        if (auth.currentUser != null) {

        }
    }

    fun signUp () {
        auth.createUserWithEmailAndPassword(
            emailEditText?.text.toString(),
            passwordEditText?.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //Add to database
                    FirebaseDatabase.getInstance().getReference().child("users").child(task.result!!.user!!.uid).child("email").setValue(emailEditText?.text.toString())
                    //login
                    logIn()
                } else {
                    Toast.makeText(
                        this, "Sign Up Failed, Try Again!", Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun goClicked(view: View) {
        if (emailEditText?.text.toString().isNotEmpty() && passwordEditText?.text.toString().isNotEmpty() ) {
            //Check if we can log in the user
            auth.signInWithEmailAndPassword(
                emailEditText?.text.toString(),
                passwordEditText?.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        logIn()
                    } else {

                        AlertDialog.Builder(this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Cannot find the user")
                            .setMessage("Would you rather like to Sign Up or Try Again?")
                            .setPositiveButton("Sign Up") { dialogInterface, i -> //Set the language to English
                                signUp()
                            }
                            .setNegativeButton(
                                "Try Again?"
                            ) { dialogInterface, i -> //Do nothing

                            }
                            .show()

                        //Else sign up the user
//                        auth.createUserWithEmailAndPassword(
//                            emailEditText?.text.toString(),
//                            passwordEditText?.text.toString()
//                        )
//                            .addOnCompleteListener(this) { task ->
//                                if (task.isSuccessful) {
//                                    //Add to database
//                                    logIn()
//                                } else {
//                                    Toast.makeText(
//                                        this, "Login Failed, Try Again", Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                            }
                    }
                }
        } else {
            Toast.makeText(this, "Please enter Email id and Password", Toast.LENGTH_SHORT).show()
        }
    }

    fun logIn() {
        //Move to next Activity
        val intent = Intent(this, SnapsActivity::class.java)
        startActivity(intent)

    }
}