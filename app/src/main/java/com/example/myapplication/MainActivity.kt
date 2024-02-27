package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraint = Constraints.Builder().setRequiresCharging(false).build()
        val data = Data.Builder().putString("key1","adfadfgadfg").build()

        val worker = OneTimeWorkRequestBuilder<MyWorker>()
            .setConstraints(constraint)
            .addTag("alias1")
            .setInputData(data)
            .build()
        val worker2 = OneTimeWorkRequestBuilder<MyWorker2>().build()
        // 15 min
        val worker3 = PeriodicWorkRequestBuilder<MyWorker>(1, TimeUnit.MINUTES).build()
        // запускать каждые двадцать минут в течение часа
        val worker4 = PeriodicWorkRequestBuilder<MyWorker>(1, TimeUnit.HOURS, 20, TimeUnit.MINUTES)

        val list: ArrayList<WorkRequest> = ArrayList()
        list.add(worker)
        list.add(worker2)

        // параллельная работа
        WorkManager.getInstance(this).enqueue(list)

        // последовательный запуск воркеров
        WorkManager.getInstance(this)
            .beginWith(worker)
            .then(worker2)
            .enqueue()

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(worker.id).observe(
            this, Observer { data: WorkInfo? ->
                if(data != null) {
                    data.outputData.getString("key2")?.let { Log.d("RRR", it) }
                    Log.d("RRR",data.state.toString())
                }
            }
        )

    }
}