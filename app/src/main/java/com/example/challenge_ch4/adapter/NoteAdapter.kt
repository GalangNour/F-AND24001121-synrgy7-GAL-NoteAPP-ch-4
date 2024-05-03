package com.example.challenge_ch4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_ch4.databinding.ItemNotesBinding
import com.example.challenge_ch4.model.Note
import com.example.challenge_ch4.viewmodel.NoteViewModel


class NoteAdapter(notesLiveData: LiveData<List<Note>>, private val itemClickListener: OnNoteItemClickListener) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notes: List<Note> = emptyList()

    init {
        notesLiveData.observeForever { updatedNotes ->
            notes = updatedNotes
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(note)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NoteViewHolder(private val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.tvTitle.text = note.noteTitle
            binding.textView.text = note.noteContent

        }
    }

    interface OnNoteItemClickListener {
        fun onItemClick(note: Note)
    }
}
