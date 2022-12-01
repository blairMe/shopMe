package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentSigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SigninFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding

    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
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

        binding.signuPgBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_signinFragment_to_signupFragment)
        }

        binding.signinBtn.setOnClickListener {
            val email = binding.usersEmail.text.toString()
            val password = binding.usersPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        view.let {
                            Navigation.findNavController(it)
                                .navigate(R.id.action_signupFragment_to_homeFragment)
                        }

                    }
                }

        }








    }

}