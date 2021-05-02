package com.akokash.ccount.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akokash.ccount.R
import com.akokash.ccount.databinding.FragmentFactsBinding
import com.akokash.ccount.databinding.FragmentInfoBinding
import com.akokash.ccount.model.FactsGenerator


class FactsFragment : Fragment() {
    private var binding: FragmentFactsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var thiscontext = container?.getContext()
        val model = FactsGenerator(thiscontext)
        setHasOptionsMenu(true)

        val fragmentFactsBinding = FragmentFactsBinding.inflate(inflater, container, false)
        binding = fragmentFactsBinding
        binding?.apply {

            factsDoneBtn.setOnClickListener {
                 findNavController().navigate(R.id.action_factsFragment_to_mainFragment)
             }
            textView3.setText(model.fact_generate())

            factsGenBtn2.setOnClickListener{
                textView3.setText(model.fact_generate())
            }
        }
        return fragmentFactsBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}