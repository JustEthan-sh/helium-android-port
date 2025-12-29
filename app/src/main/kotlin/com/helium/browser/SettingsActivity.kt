package com.helium.browser

import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebStorage
import android.webkit.WebViewDatabase
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {

    private lateinit var blockAdsSwitch: SwitchMaterial
    private lateinit var blockTrackersSwitch: SwitchMaterial
    private lateinit var doNotTrackSwitch: SwitchMaterial
    private lateinit var javascriptSwitch: SwitchMaterial
    private lateinit var cookiesSwitch: SwitchMaterial
    private lateinit var desktopModeSwitch: SwitchMaterial
    
    private lateinit var clearCacheButton: MaterialButton
    private lateinit var clearCookiesButton: MaterialButton
    private lateinit var clearHistoryButton: MaterialButton
    
    private lateinit var browserSettings: BrowserSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        
        browserSettings = BrowserSettings(this)
        
        setupToolbar()
        initializeViews()
        loadSettings()
        setupListeners()
    }
    
    private fun setupToolbar() {
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
    
    private fun initializeViews() {
        blockAdsSwitch = findViewById(R.id.blockAdsSwitch)
        blockTrackersSwitch = findViewById(R.id.blockTrackersSwitch)
        doNotTrackSwitch = findViewById(R.id.doNotTrackSwitch)
        javascriptSwitch = findViewById(R.id.javascriptSwitch)
        cookiesSwitch = findViewById(R.id.cookiesSwitch)
        desktopModeSwitch = findViewById(R.id.desktopModeSwitch)
        
        clearCacheButton = findViewById(R.id.clearCacheButton)
        clearCookiesButton = findViewById(R.id.clearCookiesButton)
        clearHistoryButton = findViewById(R.id.clearHistoryButton)
        
        // Set version text
        val versionText = findViewById<android.widget.TextView>(R.id.versionText)
        versionText.text = "${getString(R.string.version)}: ${BuildConfig.VERSION_NAME}"
    }
    
    private fun loadSettings() {
        blockAdsSwitch.isChecked = browserSettings.isAdBlockEnabled()
        blockTrackersSwitch.isChecked = browserSettings.isTrackerBlockEnabled()
        doNotTrackSwitch.isChecked = browserSettings.isDoNotTrackEnabled()
        javascriptSwitch.isChecked = browserSettings.isJavaScriptEnabled()
        cookiesSwitch.isChecked = browserSettings.areCookiesEnabled()
        desktopModeSwitch.isChecked = browserSettings.isDesktopModeEnabled()
    }
    
    private fun setupListeners() {
        blockAdsSwitch.setOnCheckedChangeListener { _, isChecked ->
            browserSettings.setAdBlock(isChecked)
            showToast(if (isChecked) "Ad blocking enabled" else "Ad blocking disabled")
        }
        
        blockTrackersSwitch.setOnCheckedChangeListener { _, isChecked ->
            browserSettings.setTrackerBlock(isChecked)
            showToast(if (isChecked) "Tracker blocking enabled" else "Tracker blocking disabled")
        }
        
        doNotTrackSwitch.setOnCheckedChangeListener { _, isChecked ->
            browserSettings.setDoNotTrack(isChecked)
            showToast(if (isChecked) "Do Not Track enabled" else "Do Not Track disabled")
        }
        
        javascriptSwitch.setOnCheckedChangeListener { _, isChecked ->
            browserSettings.setJavaScript(isChecked)
            showToast(if (isChecked) "JavaScript enabled" else "JavaScript disabled")
        }
        
        cookiesSwitch.setOnCheckedChangeListener { _, isChecked ->
            browserSettings.setCookies(isChecked)
            CookieManager.getInstance().setAcceptCookie(isChecked)
            showToast(if (isChecked) "Cookies enabled" else "Cookies disabled")
        }
        
        desktopModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            browserSettings.setDesktopMode(isChecked)
            showToast(if (isChecked) "Desktop mode enabled" else "Desktop mode disabled")
        }
        
        clearCacheButton.setOnClickListener {
            clearCache()
        }
        
        clearCookiesButton.setOnClickListener {
            clearCookies()
        }
        
        clearHistoryButton.setOnClickListener {
            clearHistory()
        }
    }
    
    private fun clearCache() {
        android.app.AlertDialog.Builder(this)
            .setTitle("Clear Cache")
            .setMessage("Are you sure you want to clear the cache?")
            .setPositiveButton("Clear") { _, _ ->
                WebStorage.getInstance().deleteAllData()
                showToast("Cache cleared")
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun clearCookies() {
        android.app.AlertDialog.Builder(this)
            .setTitle("Clear Cookies")
            .setMessage("Are you sure you want to clear all cookies?")
            .setPositiveButton("Clear") { _, _ ->
                CookieManager.getInstance().removeAllCookies { success ->
                    if (success) {
                        showToast("Cookies cleared")
                    } else {
                        showToast("Failed to clear cookies")
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun clearHistory() {
        android.app.AlertDialog.Builder(this)
            .setTitle("Clear History")
            .setMessage("Are you sure you want to clear browsing history?")
            .setPositiveButton("Clear") { _, _ ->
                WebViewDatabase.getInstance(this).clearHttpAuthUsernamePassword()
                showToast("History cleared")
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
