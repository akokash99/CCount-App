package com.akokash.ccount.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.akokash.ccount.R
import com.akokash.ccount.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private var binding: FragmentMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
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
}