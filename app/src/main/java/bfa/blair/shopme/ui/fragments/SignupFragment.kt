package bfa.blair.shopme.ui.fragments

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding

    lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog : Dialog

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

        binding.signupBtn.setOnClickListener {

            val userName = binding.tvUsername.text.toString()
            val email = binding.tvEmail.text.toString()
            val password = binding.tvPassword.text.toString()

            showProgressDialogBox()

            if(userName.isEmpty()) {
                Toast.makeText(requireActivity(),
                    "Kindly enter a username",
                    Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Toast.makeText(requireActivity(),
                    "Kindly enter a email",
                    Toast.LENGTH_SHORT).show()
            } else if(password.isEmpty()){
                Toast.makeText(requireActivity(),
                    "Kindly enter a valid password",
                    Toast.LENGTH_SHORT).show()
            } else {
                showProgressDialogBox()
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        firebaseAuth.createUserWithEmailAndPassword(email, password).await()

                        withContext(Dispatchers.Main) {
                            if (firebaseAuth.currentUser != null) {
                                dismissProgressDialogBox()
                                findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
                            }
                        }
                    } catch (ex: Exception) {
                        Log.e("Error Signing Up", ex.message.toString())
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