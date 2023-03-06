package com.example.easynote.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easynote.R
import com.example.easynote.activities.AddNoteActivity.Companion.EXTRA_DESCRIPTION_KEY
import com.example.easynote.activities.AddNoteActivity.Companion.EXTRA_KEY_PRIORITY
import com.example.easynote.activities.AddNoteActivity.Companion.EXTRA_TITLE_KEY
import com.example.easynote.adapters.NoteAdapter
import com.example.easynote.database.Note
import com.example.easynote.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var notesRecyclerView: RecyclerView
    private lateinit var addNoteButton: FloatingActionButton

    private var notesAdapter: NoteAdapter = NoteAdapter()

    companion object {
        const val ADD_NOTE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        notesRecyclerView = findViewById(R.id.mainRecyclerView)
        addNoteButton = findViewById(R.id.addFloatingActionButton)

        useAddNoteButton()

        notesRecyclerView.layoutManager = LinearLayoutManager(this)
        notesAdapter = NoteAdapter()
        notesRecyclerView.adapter = notesAdapter


        noteViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            )[NoteViewModel::class.java]
        noteViewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                notesAdapter.updateList(it)
            }
        })

    }

    private fun useAddNoteButton() {
        addNoteButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            val title = data?.getStringExtra(EXTRA_TITLE_KEY).toString()
            val description = data?.getStringExtra(EXTRA_DESCRIPTION_KEY).toString()
            val priority = data?.getIntExtra(EXTRA_KEY_PRIORITY, 0)?.toInt()

            val note: Note = Note(title, description, priority)


            val toast = Toast.makeText(
                this@MainActivity,
                "Note saved",
                Toast.LENGTH_LONG

            )

            toast.show()
        } else {
            val toast = Toast.makeText(
                this@MainActivity,
                "Note not saved",
                Toast.LENGTH_LONG

            )
            toast.show()

        }
    }


}