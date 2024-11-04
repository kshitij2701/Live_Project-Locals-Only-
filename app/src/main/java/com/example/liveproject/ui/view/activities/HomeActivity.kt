package com.example.liveproject.ui.view.activities

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.liveproject.data.local.PreferencesManager
import com.example.liveproject.R
import com.example.liveproject.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val isNightMode = resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController


        // Setup BottomNavigationView with NavController
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navController.navigate(
                        R.id.home2, null,
                        NavOptions.Builder()
                            .setPopUpTo(
                                R.id.nav_graph,
                                true
                            ) // Pops everything up to the start destination
                            .setEnterAnim(R.anim.pop_slide_in)
                            .setExitAnim(R.anim.pop_slide_out)
                            .build()
                    )
                    true
                }

                R.id.favourite -> {
                    navController.navigate(
                        R.id.favourite2, null,
                        NavOptions.Builder()
                            .setEnterAnim(R.anim.slide_in)  // Your custom enter animation
                            .setExitAnim(R.anim.slide_out)  // Your custom exit animation
                            .build()
                    )
                    true
                }

                R.id.categories -> {
                    navController.navigate(
                        R.id.categories2, null,
                        NavOptions.Builder()
                            .setEnterAnim(R.anim.slide_in)  // Your custom enter animation
                            .setExitAnim(R.anim.slide_out)  // Your custom exit animation
                            .build()
                    )
                    true
                }

                R.id.cart -> {
                    navController.navigate(
                        R.id.cart2, null,
                        NavOptions.Builder()
                            .setEnterAnim(R.anim.slide_in)  // Your custom enter animation
                            .setExitAnim(R.anim.slide_out)  // Your custom exit animation
                            .build()
                    )
                    true
                }

                R.id.profile -> {
                    navController.navigate(
                        R.id.profile2, null,
                        NavOptions.Builder()
                            .setEnterAnim(R.anim.slide_in)  // Your custom enter animation
                            .setExitAnim(R.anim.slide_out)  // Your custom exit animation
                            .build()
                    )
                    true
                }

                else -> false
            }
        }

//        binding.apply {
//
//            customToolbar.alertNotificationMenu.setOnClickListener {
//                // Display a toast message
//                Toast.makeText(this@HomeActivity, "Alert notification selected", Toast.LENGTH_SHORT)
//                    .show()
//                navController.navigate(
//                    R.id.notificationFragment, null,
//                    NavOptions.Builder()
//                        .setEnterAnim(R.anim.slide_in_top)  // Your custom enter animation
//                        .setExitAnim(R.anim.slide_out_top)  // Your custom exit animation
//                        .build()
//                )
//            }
//
//            customToolbar.cartMenu.setOnClickListener {
//                // Display a toast message
//                Toast.makeText(this@HomeActivity, "Cart selected", Toast.LENGTH_SHORT).show()
//                navController.navigate(
//                    R.id.checkOutFragment, null,
//                    NavOptions.Builder()
//                        .setEnterAnim(R.anim.slide_in_top)  // Your custom enter animation
//                        .setExitAnim(R.anim.slide_out_top)  // Your custom exit animation
//                        .build()
//                )
//            }
//
//            customToolbar.cabBackButton.setOnClickListener {
//                // Display a toast message
//                Toast.makeText(this@HomeActivity, "back icon pressed", Toast.LENGTH_SHORT).show()
//            }
//
//            customToolbar.cabDropdown.setOnClickListener {
//                // Display a toast message
//                Toast.makeText(this@HomeActivity, "Cart selected", Toast.LENGTH_SHORT).show()
//                navController.navigate(
//                    R.id.addressFragment, null,
//                    NavOptions.Builder()
//                        .setEnterAnim(R.anim.slide_in_top)  // Your custom enter animation
//                        .setExitAnim(R.anim.slide_out_top)  // Your custom exit animation
//                        .build()
//                )
//            }
//
//    }


        // Listen for destination changes to update BottomNavigationView Active indicator
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.home2 -> bottomNavigationView.menu.findItem(R.id.home).isChecked = true
                R.id.favourite2 -> bottomNavigationView.menu.findItem(R.id.favourite).isChecked =
                    true

                R.id.categories2 -> bottomNavigationView.menu.findItem(R.id.categories).isChecked =
                    true

                R.id.cart2 -> bottomNavigationView.menu.findItem(R.id.cart).isChecked = true
                R.id.profile2 -> bottomNavigationView.menu.findItem(R.id.profile).isChecked = true
            }
        }


        if (isNightMode) {
            bottomNavigationView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.dark_bottomnav
                )
            )
        } else {
            bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }


}