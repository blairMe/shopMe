package bfa.blair.shopme.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.FragmentCheckoutBinding
import com.google.firebase.auth.FirebaseAuth

class CheckoutFragment : Fragment() {

    private var binding : FragmentCheckoutBinding? = null

    private lateinit var firebase : FirebaseAuth

    private val args: CheckoutFragmentArgs by navArgs<CheckoutFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckoutBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //firebase.currentUser!!.email

        // val itemPrice = args.itemPrice
        binding!!.usersEmail.setText(firebase.currentUser!!.email.toString())



//        binding!!.clickaaa.setOnClickListener {
//            Toast.makeText(requireContext(), itemPrice, Toast.LENGTH_SHORT).show()
//        }



    }


}