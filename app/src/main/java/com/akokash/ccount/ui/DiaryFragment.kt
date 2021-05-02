package com.akokash.ccount.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akokash.ccount.MainActivity.Companion.BUDGET_SELECTION
import com.akokash.ccount.MainActivity.Companion.SCALE_IMAGE

import com.akokash.ccount.R
import com.akokash.ccount.database.Food
import com.akokash.ccount.databinding.FragmentDiaryBinding
import com.akokash.ccount.databinding.FragmentMainBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiaryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiaryFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {
    private val vm: AppViewModel by activityViewModels()
    private var binding: FragmentDiaryBinding? = null
    private val foodAdapter = FoodAdapter()

    private lateinit var calorie_budget: TextView
    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        prefs.registerOnSharedPreferenceChangeListener(this)
        val bindingDiary = FragmentDiaryBinding.inflate(inflater, container, false)
        binding = bindingDiary
        binding?.apply {
            foodRecyclerview.run {
                layoutManager = LinearLayoutManager(context)
                adapter = foodAdapter
            }
            diaryDnBtn.setOnClickListener {
                findNavController().navigate(R.id.action_diaryFragment_to_mainFragment)
            }

            googleBtn.setOnClickListener{
                findNavController().navigate(R.id.action_diaryFragment_to_webviewFragment)
            }
            addBtn.setOnClickListener {
                findNavController().navigate(R.id.action_diaryFragment_to_dataEntryFragment)
            }
            rmvBtn.setOnClickListener{
                if(vm.toDelete!=-1){
                    val thisFood = foodAdapter.getFoodAtPosition(vm.toDelete)
                    vm.deleteFood(food = thisFood)
                    vm.toDelete=-1

                }

            }
            budgetTxtView.setText("Your Budget is: " + vm.calories_budget.toString())


        }
        return bindingDiary.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.food.observe(viewLifecycleOwner, {
            foodAdapter.updateFood(it)

        })

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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }


    private inner class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var food: Food
        private val foodName: TextView = itemView.findViewById(R.id.fname_view)
        private val foodCalories: TextView = itemView.findViewById(R.id.cal_view)
        private val foodProtein: TextView = itemView.findViewById(R.id.protein_txt)
        private val foodCarbs: TextView = itemView.findViewById(R.id.carbs_txt)
        private val foodFat: TextView = itemView.findViewById(R.id.fat_txt)





        init {
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {

            Toast.makeText(activity, foodName.text.toString()+ " will be deleted", Toast.LENGTH_SHORT).show()
            vm.toDelete=adapterPosition
        }

        fun bind(food: Food) {
            this.food = food
            foodName.text =  food.fname
            foodCalories.text =  food.fcalories.toString()+ " Calories"
            foodProtein.text=  "protein: "+food.food_protein.toString()+"g"
            foodCarbs.text=  "carbs: "+food.food_carbs.toString()+"g"
            foodFat.text=  "fat: "+food.food_fat.toString()+"g"



        }
    }
    private inner class FoodAdapter : RecyclerView.Adapter<FoodViewHolder>() {
        var foods: List<Food> = emptyList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
            val view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
            return FoodViewHolder(view)
        }

        override fun onBindViewHolder(holder: FoodViewHolder, position: Int) = holder.bind(foods[position])

        override fun getItemCount() = foods.size

        fun updateFood(newFoods: List<Food>) {
            this.foods = newFoods
            notifyDataSetChanged()
        }

        fun getFoodAtPosition(position: Int): Food {
            return foods[position]
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            BUDGET_SELECTION -> {
                setSettings()
            }
        }
    }

    private fun setSettings() {
        binding?.apply {
            val effect = when (prefs.getString(BUDGET_SELECTION, "0")?.toInt()) {
                0 -> budgetTxtView.setText("Your Budget is: 1500")

                1 -> budgetTxtView.setText("Your Budget is: 1600")

                2 -> budgetTxtView.setText("Your Budget is: 1700")

                3 -> budgetTxtView.setText("Your Budget is: 1800")

                4 -> budgetTxtView.setText("Your Budget is: 1900")

                5 -> budgetTxtView.setText("Your Budget is: 2000")

                6 -> budgetTxtView.setText("Your Budget is: 2100")

                7 -> budgetTxtView.setText("Your Budget is: 2200")

                8 -> budgetTxtView.setText("Your Budget is: 2300")

                9 -> budgetTxtView.setText("Your Budget is: 2400")

                10 -> budgetTxtView.setText("Your Budget is: 2500")

                11-> budgetTxtView.setText("Your Budget is: 2600")

                12-> budgetTxtView.setText("Your Budget is: 2700")

                13-> budgetTxtView.setText("Your Budget is: 2800")

                14-> budgetTxtView.setText("Your Budget is: 2900")

                15-> budgetTxtView.setText("Your Budget is: 3000")

                else ->budgetTxtView.setText("Your Budget is: 2000")

            }
        }


    }
}