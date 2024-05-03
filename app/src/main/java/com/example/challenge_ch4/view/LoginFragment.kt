package com.example.challenge_ch4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.challenge_ch4.R
import com.example.challenge_ch4.SharedPreferences
import com.example.challenge_ch4.ViewModelFactory
import com.example.challenge_ch4.databinding.FragmentListBinding
import com.example.challenge_ch4.databinding.FragmentLoginBinding
import com.example.challenge_ch4.viewmodel.UserViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val pageTitle = "Login"


    private val viewModel : UserViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SharedPreferences.isLogin = false
        SharedPreferences.username = ""

        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.editText?.text.toString()
            val password = binding.editTextPassword.editText?.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.loginUser(username, password)
            } else {
                // Handle Form Kosong
                Toast.makeText(requireContext(), "Silahkan masukkan Usernam dan Password anda", Toast.LENGTH_SHORT).show()
            }

            viewModel.authenticatedUser.observe(viewLifecycleOwner) { user ->
                if (user != null) {
                    Toast.makeText(requireContext(), "Login Berhasil", Toast.LENGTH_SHORT).show()
                    SharedPreferences.isLogin = true
                    SharedPreferences.username = user.userName

                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToListFragment())

                }else{
                    Toast.makeText(requireContext(), "Info Salah", Toast.LENGTH_SHORT).show()
                }
            }

        }


        binding.btnRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.toolbar.tvWelcome.visibility = View.GONE
        binding.toolbar.tvLogout.visibility = View.GONE
        binding.toolbar.tvPageTitle.text = pageTitle

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}