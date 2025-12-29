<div align="center">
    <br/>
    <p>
        <img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher.png"
            title="Helium" alt="Helium logo" width="120" />
        <h1>Helium for Android</h1>
    </p>
    <p width="120">
        The privacy-focused web browser for Android, made with love.
        <br>
        Best privacy by default, unbiased ad-blocking, no bloat and no noise.
    </p>
</div>

## About

Helium for Android is a privacy-focused mobile web browser built with Kotlin and modern Android development practices. It provides a clean, fast browsing experience with built-in ad and tracker blocking, all without any Google dependencies.

## Features

- 🔒 **Privacy First**: Built-in ad and tracker blocking by default
- 🚫 **No Google Services**: Zero dependencies on Google Play Services
- 🎨 **Material Design**: Clean, modern interface following Android design guidelines
- ⚡ **Fast & Lightweight**: Optimized for performance and battery life
- 🛡️ **Security**: Modern WebView with enhanced security features
- 🌐 **Desktop Mode**: Browse websites in desktop mode when needed
- 📱 **Pure Kotlin**: 100% Kotlin implementation for Android

## Privacy Features

- **Ad Blocking**: Blocks ads and trackers by default
- **Do Not Track**: Sends DNT header with all requests
- **No Telemetry**: Zero tracking, analytics, or data collection
- **Cookie Control**: Fine-grained control over cookie acceptance
- **JavaScript Control**: Toggle JavaScript on/off as needed
- **Clear Data**: Easy-to-access tools to clear cache, cookies, and history

## Building

### Prerequisites

- Android SDK (API 24+)
- JDK 8 or higher
- Gradle 8.2+

### Build APK

```bash
# Debug build
./gradlew assembleDebug

# Release build (requires signing configuration)
./gradlew assembleRelease

# APK will be in: app/build/outputs/apk/
```

### Install on Device

```bash
# Install debug build directly
./gradlew installDebug

# Or use adb
adb install app/build/outputs/apk/debug/app-debug.apk
```

## Requirements

- **Minimum SDK**: Android 7.0 (API 24)
- **Target SDK**: Android 14 (API 34)
- **Permissions**: 
  - `INTERNET` - For browsing the web
  - `ACCESS_NETWORK_STATE` - For connectivity checks
  - `WRITE_EXTERNAL_STORAGE` / `READ_EXTERNAL_STORAGE` - For downloads (Android 9 and below)

## Project Structure

```
helium-android-port/
├── app/
│   ├── src/main/
│   │   ├── kotlin/com/helium/browser/
│   │   │   ├── MainActivity.kt          # Main browser activity
│   │   │   ├── SettingsActivity.kt      # Settings screen
│   │   │   ├── BrowserSettings.kt       # Settings manager
│   │   │   └── AdBlocker.kt             # Ad/tracker blocking engine
│   │   ├── res/                         # Resources (layouts, strings, icons)
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── build.gradle.kts                      # Root build file
├── settings.gradle.kts                   # Project settings
└── gradle.properties                     # Gradle configuration
```

## Settings

Access settings via the menu button (three dots) in the browser toolbar.

### Privacy Settings
- **Block Ads**: Enable/disable ad blocking
- **Block Trackers**: Enable/disable tracker blocking
- **Send Do Not Track**: Toggle DNT header

### Browser Settings
- **Enable JavaScript**: Control JavaScript execution
- **Enable Cookies**: Control cookie acceptance
- **Desktop Mode**: Switch between mobile and desktop user agent

### Data Management
- **Clear Cache**: Remove cached web data
- **Clear Cookies**: Delete all cookies
- **Clear History**: Remove browsing history

## Credits

### Inspired By
This Android port is inspired by the desktop [Helium browser](https://helium.computer/) project, which is based on ungoogled-chromium.

### The Chromium Project
Built on Android's WebView, which is based on [The Chromium Project](https://www.chromium.org/).

## License

All code, patches, modified portions of imported code or patches, and
any other content that is unique to Helium and not imported from other
repositories is licensed under GPL-3.0. See [LICENSE](LICENSE).

Any content imported from other projects retains its original license.

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for bugs and feature requests.

## Support

For issues, questions, or feature requests, please open an issue on GitHub.
