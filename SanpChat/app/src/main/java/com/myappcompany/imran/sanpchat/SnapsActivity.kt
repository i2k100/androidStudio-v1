package com.myappcompany.imran.sanpchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_snap.*

class SnapsActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    var createSnapImageView: ImageView? = null
    var messageEditText: EditText? = null

    var snapsListView: ListView? = null
    var emails: ArrayList<String> = ArrayList()

    var snaps: ArrayList<DataSnapshot> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snaps)

        //set uo create snap image view
        //createSnapImageView = findViewById(R.id.createSnapImageView)
        //messageEditText = findViewById(R.id.messageEditText)

        snapsListView = findViewById(R.id.snapsListView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,emails)
        snapsListView?.adapter = adapter

        Log.i("info", "SnapsActivity")

        //fetech from firebase
        auth.currentUser?.uid?.let {
            FirebaseDatabase.getInstance().getReference().child("users").child(it).child("snaps").addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    //TODO("Not yet implemented")
                    emails.add(snapshot.child("from").value as String)
                    //Log.i("email", emails.add(snapshot.child("from").value as String).toString())
                    snaps.add(snapshot!!)
                    adapter.notifyDataSetChanged()

                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    //TODO("Not yet implemented")
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    //TODO("Not yet implemented")
                    //to delete snaps
                    var index = 0
                    for (snap: DataSnapshot in snaps) {
                        if (snap.key == snapshot.key) {
                            snaps.removeAt(index)
                            emails.removeAt(index)
                        }
                        index++
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    //TODO("Not yet implemented")
                }

                override fun onCancelled(error: DatabaseError) {
                    //TODO("Not yet implemented")
                }

            })

            snapsListView?.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val snapShot = snaps.get(i)

                var intent = Intent(this, ViewSnapActivity::class.java)

                intent.putExtra("imageName", snapShot.child("imageName").value as String )
                intent.putExtra("imageURL", snapShot.child("imageURL").value as String)
                intent.putExtra("message", snapShot.child("message").value as String)
                intent.putExtra("snapKey", snapShot.key)

                startActivity(intent)
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflator = menuInflater
        inflator.inflate(R.menu.snaps, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.createSnap) {
            val intent = Intent(this, CreateSnapActivity::class.java)
            startActivity(intent)
        } else if (item?.itemId == R.id.logout){
            auth.signOut()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        auth.signOut()
    }
}