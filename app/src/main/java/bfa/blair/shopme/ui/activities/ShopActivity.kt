package bfa.blair.shopme.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import bfa.blair.shopme.R
import bfa.blair.shopme.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {

    private lateinit var binding : ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(R.style.Theme_ShopMe)

        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_shop_activity)
        setupActionBarWithNavController(navController, null)

    }
}