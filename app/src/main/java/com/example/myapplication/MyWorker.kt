package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {
    override fun doWork(): Result {
        Log.d("RRR", "MyWorker1 start")
        Thread.sleep(3000)
        Log.d("RRR","MyWorker1 finished")

        //val getvalue = inputData.getString("key1")
        val data = Data.Builder().putString("key2","data from worker1").build()

        return Result.success(data)
    }
}