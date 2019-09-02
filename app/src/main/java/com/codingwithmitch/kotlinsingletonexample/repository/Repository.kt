package com.codingwithmitch.kotlinsingletonexample.repository

import androidx.lifecycle.LiveData
import com.codingwithmitch.kotlinsingletonexample.api.ApiService
import com.codingwithmitch.kotlinsingletonexample.api.MyRetrofitBuilder
import com.codingwithmitch.kotlinsingletonexample.model.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User>{
        job = Job()
        return object: LiveData<User>(){
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getUser(userId)
                        withContext(Main){
                            value = user
                            theJob.complete()
                        }
                    }

                }

            }
        }
    }

    fun cancelJobs(){
        job?.cancel()
    }

}
















