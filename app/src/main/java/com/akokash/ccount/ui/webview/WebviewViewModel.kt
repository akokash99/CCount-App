package com.akokash.ccount.ui.webview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebviewViewModel : ViewModel() {

    private val _url = MutableLiveData<String>().apply {
        value = "https://google.com"
    }
    val url: LiveData<String> = _url
    fun setUrl(url: String) {
        _url.value = url
    }
}