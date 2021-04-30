package com.akokash.ccount.ui

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.akokash.ccount.R
import com.akokash.ccount.database.Food
import com.akokash.ccount.databinding.FragmentDataEntryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File
class DataEntryFragment : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private val sharedViewModel: AppViewModel by activityViewModels()
    private var binding: FragmentDataEntryBinding? = null





    override fun onStart() {
        super.onStart()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val addFragmentBinding = FragmentDataEntryBinding.inflate(inflater, container, false)
        binding = addFragmentBinding
        binding?.apply {



            addButton.setOnClickListener {

                if(fatInput.text.toString().equals("")){
                    Toast.makeText(activity, "Missing the fats", Toast.LENGTH_SHORT).show()
                }
                else if (proteinInput.text.toString().equals("")){
                    Toast.makeText(activity, "Missing the protein", Toast.LENGTH_SHORT).show()
                }
                else if(carbsInput.text.toString().equals("")){
                    Toast.makeText(activity, "Missing the carbs", Toast.LENGTH_SHORT).show()
                }
                else if(foodInput.text.toString().equals("")){
                    Toast.makeText(activity, "Missing the food", Toast.LENGTH_SHORT).show()
                }
                else if(caloriesInput.text.toString().equals("")){
                    Toast.makeText(activity, "Missing the calories", Toast.LENGTH_SHORT).show()
                }
                else {

                    val newFood = Food()
                    newFood.food_fat = fatInput.text.toString().toInt()
                    newFood.food_protein = proteinInput.text.toString().toInt()
                    newFood.food_carbs = carbsInput.text.toString().toInt()

                    newFood.fname = foodInput.text.toString()
                    newFood.fcalories = caloriesInput.text.toString().toInt()
                    newFood.comment = commentInput.text.toString()




                    itemAddedAlert(newFood)
                }
                /*val msg_alert = resources.getString(R.string.food_added_notif, newFood.fname)
                val builder = AlertDialog.Builder(context)
                with(builder) {
                    setTitle(R.string.alert_title)
                    setMessage(msg_alert)
                    setPositiveButton(R.string.confirm){_,_ ->
                        sharedViewModel.insert(newFood)

                    }
                    setNegativeButton(R.string.no){_,_->

                    }
                    show()
                }*/


            }
            cancelButton.setOnClickListener {
                dismiss()
            }
        }
        return addFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    fun itemAddedAlert(food: Food) {
        val msg_alert = resources.getString(R.string.food_added_notif, food.fname)
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle(R.string.alert_title)
            setMessage(msg_alert)
            setPositiveButton(R.string.confirm){_,_ ->
                sharedViewModel.insert(food)
                findNavController().navigate(R.id.action_dataEntryFragment_to_diaryFragment)

            }
            setNegativeButton(R.string.no){_,_->

            }
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        binding?.apply {

            /*when (parent) {
                typeSpinner -> {
                    newItem.type = position
                    typeImageView.setImageResource(when(newItem.type) {
                        0 -> R.drawable.type_book
                        1 -> R.drawable.type_movie
                        else -> R.drawable.type_song
                    })
                }
                publishedSpinner -> newItem.published = position
                ratingSpinner -> newItem.rating = position
            }*/
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) { }






    companion object {
        private const val REQUEST_PHOTO = 0
        private const val CAMERA_REQUEST_CODE = 1000
        const val YEAR_ZERO = 1984
        val YEARS = (YEAR_ZERO..2021).toList().toTypedArray()
    }
}

fun Context.hideKeyboard(view: View) {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}