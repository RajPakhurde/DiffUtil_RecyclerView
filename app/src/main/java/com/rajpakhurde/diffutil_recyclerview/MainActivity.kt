package com.rajpakhurde.diffutil_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rajpakhurde.diffutil_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sampleAdapter by lazy { SampleAdapter() }
    val nameList = mutableListOf<SampleModel>()
    var i = 1

    companion object{
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rvMain.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = sampleAdapter
            }
        }
        var person1 = SampleModel(1,"raj")
        var person2 = SampleModel(2,"riu")
        var person3 = SampleModel(3,"hello")
        nameList.add(person1)
        nameList.add(person2)
        nameList.add(person3)
        sampleAdapter.setData(nameList)
        binding.fabButton.setOnClickListener {
            openDialog()
            sampleAdapter.setData(nameList)
        }
    }
    private fun openDialog(){
        val editText = EditText(this)
        editText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle("Enter your Name!!")
            .setView(editText)
            .setPositiveButton("Add"){_,_->
                 var dummyData = SampleModel(i++,editText.text.toString())
                 nameList.add(dummyData)
            }.create().show()
    }
}