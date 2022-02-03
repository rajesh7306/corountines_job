package com.myandroid.jobdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var job1 : Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job1 = CoroutineScope(Dispatchers.Main).launch {
            downloadData()
        }

        cancelButton.setOnClickListener {
            job1.cancel()
        }

        statusButton.setOnClickListener {
            if(job1.isActive){
                textView.text = "Active"
            }else if(job1.isCancelled){
                textView.text ="Cancelled"
            }else if(job1.isCompleted){
                textView.text = "Completed"
            }
        }


    }
    private suspend fun downloadData(){
        withContext(Dispatchers.IO){
            repeat(30){
                delay(1000)
                Log.i("MyTag","repeating $it")
            }
        }
    }
}
