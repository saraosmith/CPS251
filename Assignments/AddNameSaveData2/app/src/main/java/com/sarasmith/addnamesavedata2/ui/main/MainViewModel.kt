package com.sarasmith.addnamesavedata2.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var names: MutableLiveData<String> = MutableLiveData()
    var editTextName: MutableLiveData<String> = MutableLiveData()

    fun addName() {
        names.let {
            if (!it.value.isNullOrEmpty()) {
                if (editTextName.value == null) {
                    editTextName.value = it.value
                } else {
                    editTextName.value = it.value + "\n" + editTextName.value
                }
            } else {
                editTextName.value = "ENTER NAME"
            }
        }
    }
}
