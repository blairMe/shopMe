package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding

    private var auth: FirebaseAuth = Firebase.auth

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

        binding.signinPgBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_signupFragment_to_signinFragment)
        }


        val userName = binding.tvUsername.text.toString()


        binding.signupBtn.setOnClickListener {

            auth = Firebase.auth

            val email = binding.tvEmail.text.toString()
            val password = binding.tvPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        view.let {
                            Navigation.findNavController(it)
                                .navigate(R.id.action_signupFragment_to_homeFragment)
                        }

                    } else {
                        Log.i("Cannot Signup", "Unable to signup")
                    }
                }
        }
    }


}