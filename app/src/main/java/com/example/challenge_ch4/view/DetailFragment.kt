package com.example.challenge_ch4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.challenge_ch4.ViewModelFactory
import com.example.challenge_ch4.databinding.FragmentDetailBinding
import com.example.challenge_ch4.viewmodel.NoteViewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NoteViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedNote.observe(viewLifecycleOwner, Observer { note ->
            binding.tvTitle.setText(note?.noteTitle ?: "No Note Selected")
            binding.tvContent.setText(note?.noteContent ?: "No Note Selected")
        })

        binding.button.setOnClickListener {
            val updatedTitle = binding.tvTitle.text.toString()
            val updatedContent = binding.tvContent.text.toString()

            viewModel.selectedNote.value?.let { currentNote ->
                val updatedNote = currentNote.copy(noteTitle = updatedTitle, noteContent = updatedContent)
                viewModel.updateNote(updatedNote)
                findNavController().navigateUp()
            }
        }
        binding.buttonDelete.setOnClickListener {
            viewModel.selectedNote.value?.let { currentNote ->
                viewModel.deleteNote(currentNote)
            }
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
