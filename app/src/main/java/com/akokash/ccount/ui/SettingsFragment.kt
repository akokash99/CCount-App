package com.akokash.ccount.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import com.akokash.ccount.R
import com.akokash.ccount.databinding.FragmentSettingsBinding


class SettingsFragment : PreferenceFragmentCompat() {

    private var binding: FragmentSettingsBinding? = null
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setHasOptionsMenu(true)
        setPreferencesFromResource(R.xml.preference_fragment, rootKey)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding = fragmentSettingsBinding
        binding?.apply {

            settingsDnBtn.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
            }
        }
        return fragmentSettingsBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}