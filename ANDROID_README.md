# Helium Browser for Android

<div align="center">
    <p>
        <img src="resources/branding/app_icon/raw.png"
            title="Helium" alt="Helium logo" width="120" />
        <h1>Helium</h1>
    </p>
    <p>
        The privacy-focused web browser for Android, made with love.
        <br>
        Best privacy by default, unbiased ad-blocking, no bloat and no noise.
    </p>
</div>

## Features

- 🔒 **Privacy First**: Built-in ad and tracker blocking
- 🚫 **No Google Services**: No dependencies on Google Play Services
- 🎨 **Clean Interface**: Simple, intuitive material design
- ⚡ **Fast & Lightweight**: Optimized for performance
- 🛡️ **Security**: Modern WebView with security features enabled
- 🌐 **Desktop Mode**: Browse as desktop when needed
- 📱 **Android Native**: Written in Kotlin for Android

## Privacy Features

- **Ad Blocking**: Block ads and trackers by default
- **Do Not Track**: Send DNT header with all requests
- **No Telemetry**: Zero tracking or analytics
- **Cookie Control**: Manage cookies with fine-grained control
- **JavaScript Control**: Enable/disable JavaScript per your preference

## Building

### Prerequisites

- Android SDK (API 24+)
- JDK 8 or higher
- Gradle 8.2+

### Build APK

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# APK will be in: app/build/outputs/apk/
```

### Install on Device

```bash
# Install debug build
./gradlew installDebug

# Or use adb directly
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Project Structure

```
helium-android-port/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── kotlin/com/helium/browser/
│   │       │   ├── MainActivity.kt          # Main browser activity
│   │       │   ├── SettingsActivity.kt      # Settings screen
│   │       │   ├── BrowserSettings.kt       # Settings manager
│   │       │   └── AdBlocker.kt             # Ad/tracker blocking
│   │       ├── res/                         # Resources (layouts, strings, icons)
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

## Settings

Access settings via the menu button (three dots) in the browser toolbar.

### Privacy Settings
- Block Ads
- Block Trackers
- Send Do Not Track header

### Browser Settings
- Enable/Disable JavaScript
- Enable/Disable Cookies
- Desktop Mode

### Data Management
- Clear Cache
- Clear Cookies
- Clear History

## Requirements

- **Minimum SDK**: Android 7.0 (API 24)
- **Target SDK**: Android 14 (API 34)
- **Permissions**: 
  - INTERNET (for browsing)
  - ACCESS_NETWORK_STATE (for connectivity checks)

## License

All code unique to Helium is licensed under GPL-3.0. See [LICENSE](LICENSE).

Any content imported from other projects retains its original license.

## Credits

Based on Android WebView with custom privacy and security enhancements.

Inspired by the desktop [Helium browser](https://helium.computer/) project.
