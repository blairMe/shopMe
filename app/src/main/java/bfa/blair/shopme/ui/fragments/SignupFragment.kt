package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signinPgBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_signupFragment_to_signinFragment)
        }


        val userName = binding.tvUsername.text.toString()
        val email = binding.tvEmail.text.toString()
        val password = binding.tvPassword.text.toString()

        binding.signupBtn.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                try {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).await()

                    withContext(Dispatchers.Main) {
                            findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
                    }
                } catch (ex: Exception) {
                    Log.e("Error", ex.message.toString())
                }
            }
        }
    }
}