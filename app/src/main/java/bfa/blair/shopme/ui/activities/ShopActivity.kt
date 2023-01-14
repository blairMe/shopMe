package bfa.blair.shopme.ui.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.ActivityShopBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(R.style.Theme_ShopMe)
        supportActionBar?.hide()

        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_shop_activity)
        val bottomNav = binding.bottomNavView
        // setupActionBarWithNavController(navController, null)
        NavigationUI.setupWithNavController(bottomNav, navController)

        navController.addOnDestinationChangedListener { _ , destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> bottomNav.visibility = View.VISIBLE
                R.id.favoriteFragment -> bottomNav.visibility = View.VISIBLE
                R.id.cartFragment -> bottomNav.visibility = View.VISIBLE
                R.id.profileFragment -> bottomNav.visibility = View.VISIBLE
                else -> {bottomNav.visibility = View.GONE}
            }
        }
    }
}