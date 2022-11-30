package bfa.blair.shopme.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.ActivityShopBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ShopActivity : AppCompatActivity() {

    private lateinit var binding : ActivityShopBinding

    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(R.style.Theme_ShopMe)

        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_shop_activity)
        setupActionBarWithNavController(navController, null)

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            val navContoll = findNavController(R.id.action_signupFragment_to_homeFragment)
            setupActionBarWithNavController(navContoll)
//            view?.let {
//                Navigation.findNavController(it)
//                    .navigate(R.id.action_signinFragment_to_signupFragment)
//            }
        }
    }



}