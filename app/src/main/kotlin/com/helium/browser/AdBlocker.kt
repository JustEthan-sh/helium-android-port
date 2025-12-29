package com.helium.browser

class AdBlocker {
    
    // Simple ad/tracker blocking patterns
    // In a production app, this would be loaded from a comprehensive filter list
    private val adBlockPatterns = listOf(
        "doubleclick.net",
        "googleadservices.com",
        "googlesyndication.com",
        "google-analytics.com",
        "googletagmanager.com",
        "facebook.com/tr",
        "facebook.net",
        "connect.facebook.net",
        "adservice.google",
        "pagead2.googlesyndication.com",
        "adbrite.com",
        "adbureau.net",
        "admob.com",
        "advertising.com",
        "adnxs.com",
        "adsystem.com",
        "outbrain.com",
        "taboola.com",
        "scorecardresearch.com",
        "2mdn.net",
        "ads.",
        "adserver",
        "tracker",
        "/ads/",
        "/ad/",
        "/banner",
        "/tracking",
        "/analytics"
    )
    
    fun isAd(url: String): Boolean {
        val lowerUrl = url.lowercase()
        return adBlockPatterns.any { pattern ->
            lowerUrl.contains(pattern.lowercase())
        }
    }
    
    fun isTracker(url: String): Boolean {
        val lowerUrl = url.lowercase()
        val trackerPatterns = listOf(
            "analytics",
            "tracking",
            "tracker",
            "telemetry",
            "metric",
            "pixel",
            "beacon"
        )
        return trackerPatterns.any { pattern ->
            lowerUrl.contains(pattern)
        }
    }
}
