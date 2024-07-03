package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db:NotesDatabaseHelper
    private  var noteId : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if (noteId == -1){
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.UpdateetitleEditText.setText(note.title)
        binding.UpdatecontentEditText.setText(note.content)

        binding.UpdatesaveButton.setOnClickListener{
            val newTitle = binding.UpdateetitleEditText.text.toString()
            val newContent = binding.UpdatecontentEditText.text.toString()
            val updateNote = Note(noteId,newTitle,newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()
        }
    }
}