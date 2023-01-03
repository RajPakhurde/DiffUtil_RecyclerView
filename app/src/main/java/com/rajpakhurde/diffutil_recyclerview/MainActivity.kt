package com.rajpakhurde.diffutil_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rajpakhurde.diffutil_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adabter : SampleAdapter
    val nameList = mutableListOf<SampleModel>()
    var i = 1

    companion object{
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adabter = SampleAdapter(object : SampleAdapter.ItemClickedListener {
            override fun onClicked(position: Int) {
                Toast.makeText(this@MainActivity,"You clicked on $position item and name is ${nameList[position].name}",Toast.LENGTH_SHORT).show()
            }

        })

        binding.apply {
            rvMain.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = adabter
            }
        }


        binding.fabButton.setOnClickListener {
            openDialog()
        }
    }
    private fun openDialog(){
        val editText = EditText(this)
        editText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle("Enter your Name!!")
            .setView(editText)
            .setPositiveButton("Add"){_,_->
                var text = editText.text.toString()
                nameList.add(SampleModel(i++,text))
                    adabter.setData(nameList)
            }.create().show()
    }


}