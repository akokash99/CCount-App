package com.akokash.ccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {


    private lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Ccount)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.let {
                it.title = when (destination.id) {

                    R.id.settingsFragment -> getString(R.string.settings)
                    R.id.factsFragment -> getString(R.string.Cool_facts)
                    R.id.infoFragment -> getString(R.string.info)
                    else -> getString(R.string.app_name)
                }
            }
        }


    }


    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings_item -> {
                navHostFragment.navController.navigate(R.id.action_mainFragment_to_settingsFragment)
                true
            }
            R.id.info_item ->{
                navHostFragment.navController.navigate(R.id.action_mainFragment_to_infoFragment)
                true
            }
            R.id.facts_item ->{
                navHostFragment.navController.navigate(R.id.action_mainFragment_to_factsFragment)
                true
            }
            R.id.home ->{


                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }
}