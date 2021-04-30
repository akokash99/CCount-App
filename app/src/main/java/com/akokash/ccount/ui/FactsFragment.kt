package com.akokash.ccount.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.akokash.ccount.R
import com.akokash.ccount.databinding.FragmentFactsBinding
import com.akokash.ccount.databinding.FragmentInfoBinding


class FactsFragment : Fragment() {
    private var binding: FragmentFactsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val fragmentFactsBinding = FragmentFactsBinding.inflate(inflater, container, false)
        binding = fragmentFactsBinding
        binding?.apply {

            /* settingsDnBtn.setOnClickListener {
                 findNavController().navigate(R.id.action_settingsFragment_to_mainFragment)
             }*/
        }
        return fragmentFactsBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}