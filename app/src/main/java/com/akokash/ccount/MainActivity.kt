package com.akokash.ccount

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {

    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

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
        if (savedInstanceState == null) {
            if (prefs.getBoolean(SHOW_INSTRUCTIONS_AT_START, false)) {
                welcomeAlert()
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

    private fun welcomeAlert() {
        val msg = resources.getString(R.string.message_body)
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(R.string.welcome)
            setMessage(msg)

            setPositiveButton(R.string.ok, null)
            show()
        }
    }

    companion object {
        const val SHOW_INSTRUCTIONS_AT_START = "show_instructions_at_start"
        const val SCALE_IMAGE = "scale_image"
        const val BUDGET_SELECTION = "budget_selection"
    }
}