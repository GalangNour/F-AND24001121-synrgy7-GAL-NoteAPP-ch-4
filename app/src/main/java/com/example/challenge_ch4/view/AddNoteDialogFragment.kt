package com.example.challenge_ch4.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.challenge_ch4.SharedPreferences.username
import com.example.challenge_ch4.ViewModelFactory
import com.example.challenge_ch4.databinding.FragmentDialogBinding
import com.example.challenge_ch4.model.Note
import com.example.challenge_ch4.viewmodel.NoteViewModel

class AddNoteDialogFragment : DialogFragment() {

    private val viewModelNote: NoteViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBinding = FragmentDialogBinding.inflate(requireActivity().layoutInflater)
        val dialogView = dialogBinding.root


        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Tambah Note")

        dialogBinding.buttonSubmit.setOnClickListener {
            val title = dialogBinding.editTextTitle.text.toString()
            val content = dialogBinding.editTextContent.text.toString()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                viewModelNote.addNote(
                    Note(
                        noteTitle = title,
                        noteContent = content,
                        noteCreator = username
                    )
                )
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Note Title dan Note Content tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        return dialogBuilder.create()
    }

}
