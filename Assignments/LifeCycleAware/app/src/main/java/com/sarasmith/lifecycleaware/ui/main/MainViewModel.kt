package com.sarasmith.lifecycleaware.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object{
        private var result: MutableLiveData<String> = MutableLiveData("")

        fun setResult(value: String) {
            result.value += value
        }
    }

    fun getStatus(): MutableLiveData<String>{
        return result
    }

}