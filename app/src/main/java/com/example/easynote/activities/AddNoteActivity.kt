package com.example.easynote.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import com.example.easynote.R

class AddNoteActivity : AppCompatActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var priorityNumberPicker: NumberPicker

    companion object {
        const val EXTRA_TITLE_KEY = "title_key"
        const val EXTRA_DESCRIPTION_KEY = "description_key"
        const val EXTRA_KEY_PRIORITY = "priority_key"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        priorityNumberPicker = findViewById(R.id.priorityNumberPicker)

        setNumberPickerValues()


        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_24)
        title = "Add Note"
    }
    private fun setNumberPickerValues() {
        priorityNumberPicker.maxValue = 10
        priorityNumberPicker.minValue = 1
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_menu, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun saveNote() {
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val priority = priorityNumberPicker.value

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            val toast = Toast.makeText(
                this@AddNoteActivity,
                "Inset the empty fields",
                Toast.LENGTH_LONG

            )
            toast.show()
            return
        } else {
            val dataIntent = Intent()
            dataIntent.putExtra(EXTRA_TITLE_KEY, title)
            dataIntent.putExtra(EXTRA_DESCRIPTION_KEY, description)
            dataIntent.putExtra(EXTRA_KEY_PRIORITY, priority)

            setResult(RESULT_OK, dataIntent)
            finish()
        }


    }
}