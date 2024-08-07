package com.example.notessql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessql.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseHelper: NotesDatabaseHelper
    private  var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if(noteId == -1){
            finish()
            return
        }

        val note = databaseHelper.getNoteById(noteId)
        binding.updateEditTitleText.setText(note.title)
        binding.updateEditContext.setText(note.content)


        binding.updateSaveBtn.setOnClickListener {
            val newTitle = binding.updateEditTitleText.text.toString()
            val newContent = binding.updateEditContext.text.toString()
            val updatedNote = Note(noteId, newTitle, newContent)

            databaseHelper.updateNote(updatedNote)
            finish()
            Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
        }
    }
}