package com.helium.browser

import android.content.Context
import android.content.SharedPreferences

class BrowserSettings(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    companion object {
        private const val PREFS_NAME = "helium_browser_prefs"
        private const val KEY_JAVASCRIPT = "javascript_enabled"
        private const val KEY_COOKIES = "cookies_enabled"
        private const val KEY_AD_BLOCK = "ad_block_enabled"
        private const val KEY_TRACKER_BLOCK = "tracker_block_enabled"
        private const val KEY_DO_NOT_TRACK = "do_not_track"
        private const val KEY_DESKTOP_MODE = "desktop_mode"
        
        // Default values
        private const val DEFAULT_JAVASCRIPT = true
        private const val DEFAULT_COOKIES = true
        private const val DEFAULT_AD_BLOCK = true
        private const val DEFAULT_TRACKER_BLOCK = true
        private const val DEFAULT_DO_NOT_TRACK = true
        private const val DEFAULT_DESKTOP_MODE = false
    }
    
    fun isJavaScriptEnabled(): Boolean {
        return prefs.getBoolean(KEY_JAVASCRIPT, DEFAULT_JAVASCRIPT)
    }
    
    fun setJavaScript(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_JAVASCRIPT, enabled).apply()
    }
    
    fun areCookiesEnabled(): Boolean {
        return prefs.getBoolean(KEY_COOKIES, DEFAULT_COOKIES)
    }
    
    fun setCookies(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_COOKIES, enabled).apply()
    }
    
    fun isAdBlockEnabled(): Boolean {
        return prefs.getBoolean(KEY_AD_BLOCK, DEFAULT_AD_BLOCK)
    }
    
    fun setAdBlock(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_AD_BLOCK, enabled).apply()
    }
    
    fun isTrackerBlockEnabled(): Boolean {
        return prefs.getBoolean(KEY_TRACKER_BLOCK, DEFAULT_TRACKER_BLOCK)
    }
    
    fun setTrackerBlock(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_TRACKER_BLOCK, enabled).apply()
    }
    
    fun isDoNotTrackEnabled(): Boolean {
        return prefs.getBoolean(KEY_DO_NOT_TRACK, DEFAULT_DO_NOT_TRACK)
    }
    
    fun setDoNotTrack(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_DO_NOT_TRACK, enabled).apply()
    }
    
    fun isDesktopModeEnabled(): Boolean {
        return prefs.getBoolean(KEY_DESKTOP_MODE, DEFAULT_DESKTOP_MODE)
    }
    
    fun setDesktopMode(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_DESKTOP_MODE, enabled).apply()
    }
}
