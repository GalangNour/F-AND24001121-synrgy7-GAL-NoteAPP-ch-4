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
import com.example.challenge_ch4.ViewModelFactory
import com.example.challenge_ch4.databinding.FragmentRegisterBinding
import com.example.challenge_ch4.model.User
import com.example.challenge_ch4.viewmodel.UserViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val username = binding.editTextUsername.editText?.text.toString()
            val password = binding.editTextPassword.editText?.text.toString()
            val passwordConfirm = binding.editTextPasswordConfirm.editText?.text.toString()
            val email = binding.editTextEmail.editText?.text.toString()

            if (password == passwordConfirm){
                if (username.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty() && email.isNotEmpty()){
                    viewModel.registerUser(
                        User(
                            userName = username,
                            userPassword = password,
                            userEmail = email
                        )
                    )
                    Toast.makeText(requireContext(), "User berhasil didaftarkan", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }else{
                    Toast.makeText(requireContext(), "Silahkan isi Form anda dengan benar", Toast.LENGTH_SHORT).show()
                }
            }
            // Handling if Password and PasswordConfirm ga sama
            else{
                Toast.makeText(requireContext(), "Password dan Konfirmasi Password tidak sama", Toast.LENGTH_SHORT).show()
            }
        }
        binding.toolbar.tvWelcome.visibility = View.GONE
        binding.toolbar.tvLogout.visibility = View.GONE
        val pageTitle = "Register"
        binding.toolbar.tvPageTitle.text = pageTitle
    }

}