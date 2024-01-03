package com.example.coroutinesdemo
import RetrofitClient
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainActivityViewModel : ViewModel() {

    val dataObserver: MutableLiveData<List<DataItems>?> = MutableLiveData()

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("TAG", "Handling networkCall on 1 ${Thread.currentThread().name}")
            try {
                val newData = RetrofitClient.apiInterface.getData()
                dataObserver.postValue(newData)
            } catch (e: HttpException) {
                dataObserver.postValue(null)
            }
        }
    }
}
