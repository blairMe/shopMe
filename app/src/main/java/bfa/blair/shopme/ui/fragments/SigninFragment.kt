package bfa.blair.shopme.ui.fragments

import android.app.Dialog
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
import bfa.blair.shopme.databinding.FragmentSigninBinding
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.io.IOException

class SigninFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog : Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            view?.let {
                Navigation.findNavController(it)
                    .navigate(R.id.action_signinFragment_to_homeFragment)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signuPgBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_signinFragment_to_signupFragment)
        }

        binding.signinBtn.setOnClickListener {

            val email = binding.usersEmail.text.toString()
            val password = binding.usersPassword.text.toString()



            if(email.isEmpty()) {
                Toast.makeText(requireActivity(), "Please enter your email", Toast.LENGTH_SHORT).show()
            } else if(password.isEmpty()) {
                Toast.makeText(requireActivity(), "Please enter a valid password", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    showProgressDialogBox()
                    try {
                        firebaseAuth.signInWithEmailAndPassword(email, password).await()

                        withContext(Dispatchers.Main) {
                            if(firebaseAuth.currentUser != null) {
                                dismissProgressDialogBox()
                                findNavController().navigate(R.id.action_signinFragment_to_homeFragment)
                            } else {
                                dismissProgressDialogBox()
                                Toast.makeText(requireActivity(),
                                    "There was an error signing in, please try again",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (ex: Exception) {
                        Log.e("Error", ex.message.toString())
                        dismissProgressDialogBox()
                    }
                }
            }
        }
    }

    private fun showProgressDialogBox() {
        progressDialog = Dialog(requireActivity())
        progressDialog.let {
            it.setContentView(R.layout.loading_dialog)
            it.show()
        }
    }

    private fun dismissProgressDialogBox() {
        progressDialog.let {
            it.dismiss()
        }
    }
}