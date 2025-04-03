package com.deepakjetpackcompose.newspulse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThemeViewModel(): ViewModel() {
   private val _isDarkMode= MutableLiveData(false)
    val darkMode: LiveData<Boolean> get() =_isDarkMode

    fun toggleTheme(){
        _isDarkMode.value=_isDarkMode.value?.not()
    }
}