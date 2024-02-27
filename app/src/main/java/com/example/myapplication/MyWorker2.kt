package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker2(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {
    override fun doWork(): Result {
        Log.d("RRR", "MyWorker2 start")
        Thread.sleep(6000)
        Log.d("RRR","MyWorker2 finished")
        return Result.success()
    }
}