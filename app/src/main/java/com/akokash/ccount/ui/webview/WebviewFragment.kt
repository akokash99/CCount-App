package com.akokash.ccount.ui.webview

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.akokash.ccount.R
import com.akokash.ccount.databinding.FragmentWebviewBinding
private const val TAG = "WebviewFragment"

class WebviewFragment : Fragment() {

    private var binding: FragmentWebviewBinding? = null
    private val viewModel: WebviewViewModel by lazy {
        ViewModelProvider(this).get(WebviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val webviewFragmentBinding = FragmentWebviewBinding.inflate(inflater, container, false)
        binding = webviewFragmentBinding
        return webviewFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            webView.webViewClient = WebViewClient()
            loadUrl(viewModel.url.value.toString())
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun loadUrl(request: String) {
        Log.d(TAG, "url in loadUrl: $request")
        binding?.webView?.loadUrl(request)
    }

}