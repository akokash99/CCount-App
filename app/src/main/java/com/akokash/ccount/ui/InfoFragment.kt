package com.akokash.ccount.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akokash.ccount.R
import com.akokash.ccount.databinding.FragmentInfoBinding
import com.akokash.ccount.databinding.FragmentSettingsBinding


class InfoFragment : Fragment() {
    private var binding: FragmentInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        val fragmentInfoBinding = FragmentInfoBinding.inflate(inflater, container, false)
        binding = fragmentInfoBinding
        binding?.apply {

            infoDoneBtn.setOnClickListener {
                findNavController().navigate(R.id.action_infoFragment_to_mainFragment)
            }
        }
        return fragmentInfoBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}