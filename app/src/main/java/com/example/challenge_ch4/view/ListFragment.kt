package com.example.challenge_ch4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge_ch4.SharedPreferences
import com.example.challenge_ch4.ViewModelFactory
import com.example.challenge_ch4.adapter.NoteAdapter
import com.example.challenge_ch4.databinding.FragmentListBinding
import com.example.challenge_ch4.model.Note
import com.example.challenge_ch4.viewmodel.NoteViewModel
import com.example.challenge_ch4.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class ListFragment : Fragment(), NoteAdapter.OnNoteItemClickListener {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModelNote: NoteViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    private val viewModelUser: UserViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        SharedPreferences.init(requireContext())

        val userName = SharedPreferences.username


        if (!SharedPreferences.isLogin) {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToLoginFragment())
        }else{
            viewModelUser.destroyAuthenticatedUser()
        }



        viewLifecycleOwner.lifecycleScope.launch {
            val adapter = NoteAdapter(viewModelNote.getNotesByCreator(userName),this@ListFragment)

            binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
            binding.rvNotes.adapter = adapter

        }


        binding.toolbar.tvWelcome.text = "Welcome, $userName"

        binding.toolbar.tvLogout.setOnClickListener {
            SharedPreferences.isLogin = false
            viewModelUser.destroyAuthenticatedUser()

            findNavController().navigate(ListFragmentDirections.actionListFragmentToLoginFragment())
        }

        binding.fabAddNote.setOnClickListener {
            val dialogFragment = AddNoteDialogFragment()
            dialogFragment.show(childFragmentManager, "AddNoteDialogFragment")

        }
    }

    override fun onItemClick(note: Note) {
        viewModelNote.selectNote(note)
        val action = ListFragmentDirections.actionListFragmentToDetailFragment()
        findNavController().navigate(action)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
