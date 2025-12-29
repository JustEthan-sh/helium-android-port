package com.helium.browser

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.*
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var urlEditText: EditText
    private lateinit var backButton: ImageButton
    private lateinit var forwardButton: ImageButton
    private lateinit var refreshButton: ImageButton
    private lateinit var menuButton: ImageButton
    private lateinit var progressBar: ProgressBar
    private lateinit var tabCounterFab: FloatingActionButton
    
    private lateinit var browserSettings: BrowserSettings
    private val adBlocker = AdBlocker()
    
    companion object {
        private const val DEFAULT_URL = "https://www.google.com"
        private const val SETTINGS_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        browserSettings = BrowserSettings(this)
        
        initializeViews()
        setupWebView()
        setupListeners()
        
        // Load initial page
        val url = intent?.data?.toString() ?: DEFAULT_URL
        loadUrl(url)
    }
    
    private fun initializeViews() {
        webView = findViewById(R.id.webView)
        urlEditText = findViewById(R.id.urlEditText)
        backButton = findViewById(R.id.backButton)
        forwardButton = findViewById(R.id.forwardButton)
        refreshButton = findViewById(R.id.refreshButton)
        menuButton = findViewById(R.id.menuButton)
        progressBar = findViewById(R.id.progressBar)
        tabCounterFab = findViewById(R.id.tabCounterFab)
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        webView.apply {
            settings.apply {
                javaScriptEnabled = browserSettings.isJavaScriptEnabled()
                domStorageEnabled = true
                databaseEnabled = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                loadWithOverviewMode = true
                useWideViewPort = true
                mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
                
                // Privacy settings
                saveFormData = false
                savePassword = false
                setGeolocationEnabled(false)
                
                // User agent
                if (browserSettings.isDesktopModeEnabled()) {
                    userAgentString = getDesktopUserAgent()
                }
                
                // Cache settings
                cacheMode = WebSettings.LOAD_DEFAULT
                setAppCacheEnabled(true)
            }
            
            webViewClient = HeliumWebViewClient()
            webChromeClient = HeliumWebChromeClient()
            
            // Enable downloads
            setDownloadListener { url, userAgent, contentDisposition, mimeType, _ ->
                // Handle downloads
                Toast.makeText(this@MainActivity, "Download: $url", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun setupListeners() {
        backButton.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            }
        }
        
        forwardButton.setOnClickListener {
            if (webView.canGoForward()) {
                webView.goForward()
            }
        }
        
        refreshButton.setOnClickListener {
            webView.reload()
        }
        
        menuButton.setOnClickListener {
            showMenu()
        }
        
        urlEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO || 
                (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                val url = urlEditText.text.toString()
                loadUrl(url)
                urlEditText.clearFocus()
                true
            } else {
                false
            }
        }
        
        tabCounterFab.setOnClickListener {
            Toast.makeText(this, "Tab management coming soon", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun loadUrl(input: String) {
        val url = when {
            input.isEmpty() -> DEFAULT_URL
            input.startsWith("http://") || input.startsWith("https://") -> input
            input.contains(".") -> "https://$input"
            else -> "https://www.google.com/search?q=$input"
        }
        webView.loadUrl(url)
    }
    
    private fun showMenu() {
        val items = arrayOf(
            getString(R.string.new_tab),
            getString(R.string.settings),
            getString(R.string.share),
            getString(R.string.find_in_page),
            getString(R.string.desktop_mode)
        )
        
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.menu))
            .setItems(items) { _, which ->
                when (which) {
                    0 -> loadUrl(DEFAULT_URL)
                    1 -> {
                        startActivityForResult(
                            Intent(this, SettingsActivity::class.java),
                            SETTINGS_REQUEST_CODE
                        )
                    }
                    2 -> shareCurrentPage()
                    3 -> showFindInPage()
                    4 -> toggleDesktopMode()
                }
            }
            .show()
    }
    
    private fun shareCurrentPage() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, webView.url)
            putExtra(Intent.EXTRA_SUBJECT, webView.title)
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
    }
    
    private fun showFindInPage() {
        Toast.makeText(this, "Find in page coming soon", Toast.LENGTH_SHORT).show()
    }
    
    private fun toggleDesktopMode() {
        val desktopMode = !browserSettings.isDesktopModeEnabled()
        browserSettings.setDesktopMode(desktopMode)
        
        webView.settings.userAgentString = if (desktopMode) {
            getDesktopUserAgent()
        } else {
            null // Reset to default mobile user agent
        }
        
        webView.reload()
        Toast.makeText(
            this, 
            "Desktop mode ${if (desktopMode) "enabled" else "disabled"}", 
            Toast.LENGTH_SHORT
        ).show()
    }
    
    private fun getDesktopUserAgent(): String {
        return "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/143.0.0.0 Safari/537.36"
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SETTINGS_REQUEST_CODE) {
            // Reload settings
            webView.settings.javaScriptEnabled = browserSettings.isJavaScriptEnabled()
        }
    }
    
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
    
    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }
    
    // Custom WebViewClient
    inner class HeliumWebViewClient : WebViewClient() {
        
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return false
        }
        
        override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
            request?.url?.toString()?.let { url ->
                if (browserSettings.isAdBlockEnabled() && adBlocker.isAd(url)) {
                    // Block the request
                    return WebResourceResponse("text/plain", "utf-8", null)
                }
            }
            return super.shouldInterceptRequest(view, request)
        }
        
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
            progressBar.progress = 0
            urlEditText.setText(url)
        }
        
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
            urlEditText.setText(url)
            backButton.isEnabled = webView.canGoBack()
            forwardButton.isEnabled = webView.canGoForward()
        }
        
        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            if (request?.isForMainFrame == true) {
                val errorHtml = """
                    <html>
                    <head>
                        <style>
                            body { font-family: sans-serif; padding: 20px; text-align: center; }
                            h1 { color: #6366F1; }
                            p { color: #6B7280; }
                        </style>
                    </head>
                    <body>
                        <h1>${getString(R.string.error_page_title)}</h1>
                        <p>${getString(R.string.error_page_message)}</p>
                        <p>Error: ${error?.description}</p>
                    </body>
                    </html>
                """.trimIndent()
                view?.loadData(errorHtml, "text/html", "utf-8")
            }
        }
    }
    
    // Custom WebChromeClient
    inner class HeliumWebChromeClient : WebChromeClient() {
        
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            progressBar.progress = newProgress
            if (newProgress == 100) {
                progressBar.visibility = View.GONE
            } else {
                progressBar.visibility = View.VISIBLE
            }
        }
        
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            // Could update tab title here
        }
        
        override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
            super.onReceivedIcon(view, icon)
            // Could update favicon here
        }
    }
}
