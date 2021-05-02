package com.akokash.ccount.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akokash.ccount.MainActivity
import com.akokash.ccount.MainActivity.Companion.SCALE_IMAGE

import com.akokash.ccount.R
import com.akokash.ccount.databinding.FragmentMainBinding


class MainFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val viewModel: AppViewModel by activityViewModels()
    private var binding: FragmentMainBinding? = null


    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        prefs.registerOnSharedPreferenceChangeListener(this)
        val bindingMain = FragmentMainBinding.inflate(inflater, container, false)
        binding = bindingMain
        binding?.apply {

            diaryBtn.setOnClickListener{
                findNavController().navigate(R.id.action_mainFragment_to_diaryFragment)
            }
           /* addBtn.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_dataEntryFragment)
            }
            rmvBtn.setOnClickListener{
                if(vm.toDelete!=-1){
                    val thisFriend = friendAdapter.getFriendAtPosition(vm.toDelete)
                    vm.deleteFriend(friend = thisFriend)
                    vm.toDelete=-1

                }

            }

            friendsRecyclerview.run {
                layoutManager = LinearLayoutManager(context)
                adapter = friendAdapter
            }*/

        }
        return bindingMain.root
    }
    companion object {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSettings()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            MainActivity.SCALE_IMAGE, MainActivity.BUDGET_SELECTION -> {
                setSettings()
            }
        }
    }

    private fun setSettings() {
        binding?.apply {
            val resID = if (prefs.getBoolean(
                    SCALE_IMAGE,
                    false
                )
            ) imageView.setImageResource(R.drawable.dscale)else imageView.setImageResource(R.drawable.scale2)
        }


    }
}