# Quick Start Guide - Helium Browser for Android

## 🎉 Congratulations!

You now have a complete Android browser application built with Kotlin! Here's everything you need to know to use it.

## 📦 What You Have

A fully functional Android browser with:
- ✅ Privacy-focused browsing (ad/tracker blocking built-in)
- ✅ Clean Material Design interface
- ✅ Settings for customization
- ✅ All modern browser features
- ✅ Builds to a standalone APK

## 🚀 Building the APK

### Option 1: Using the Build Script (Easiest)
```bash
./build.sh
# Then select option 1 for Debug build
```

### Option 2: Using Gradle Directly
```bash
# Debug version (for testing)
./gradlew assembleDebug

# Release version (for distribution)
./gradlew assembleRelease
```

### Option 3: Using Android Studio
1. Open Android Studio
2. Select "Open an existing project"
3. Navigate to this directory
4. Wait for Gradle sync
5. Click "Build" → "Build Bundle(s) / APK(s)" → "Build APK(s)"

## 📱 Installing on Your Device

### Method 1: Direct Install (if device connected)
```bash
./gradlew installDebug
```

### Method 2: Manual Install with ADB
```bash
# After building
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Method 3: Transfer APK to Phone
1. Build the APK (see above)
2. Find it at: `app/build/outputs/apk/debug/app-debug.apk`
3. Copy to your phone via USB, email, or cloud storage
4. On your phone, tap the APK file
5. Allow installation from unknown sources if prompted
6. Tap "Install"

## 🎨 App Features

### Main Browser Screen
- **URL Bar**: Type a website address or search term
- **Back/Forward**: Navigate through your browsing history
- **Refresh**: Reload the current page
- **Menu**: Access settings and options

### Settings (via Menu Button)
- **Privacy**:
  - Block Ads ✓ (enabled by default)
  - Block Trackers ✓ (enabled by default)
  - Send Do Not Track ✓ (enabled by default)
  
- **Browser**:
  - JavaScript: Enable/disable
  - Cookies: Enable/disable
  - Desktop Mode: Switch between mobile and desktop view

- **Data**:
  - Clear Cache
  - Clear Cookies
  - Clear History

## 📋 System Requirements

- **Android Version**: 7.0 (API 24) or higher
- **Disk Space**: ~5-10 MB
- **Permissions**: 
  - Internet access
  - Network state (to check connectivity)

## 🔧 Development

### Project Structure
```
helium-android-port/
├── app/
│   ├── src/main/
│   │   ├── kotlin/com/helium/browser/    # Kotlin source code
│   │   ├── res/                          # Resources (layouts, images, strings)
│   │   └── AndroidManifest.xml           # App configuration
│   └── build.gradle.kts                  # App build configuration
├── build.gradle.kts                       # Project build configuration
└── settings.gradle.kts                    # Project settings
```

### Key Files
- `MainActivity.kt`: Main browser interface and WebView handling
- `SettingsActivity.kt`: Settings screen
- `BrowserSettings.kt`: Settings storage and retrieval
- `AdBlocker.kt`: Ad and tracker blocking logic

### Customization Ideas
1. **Add more ad filters**: Edit `AdBlocker.kt` to add patterns
2. **Change colors**: Edit `app/src/main/res/values/colors.xml`
3. **Add features**: Extend MainActivity with new functionality
4. **Improve UI**: Modify layout files in `app/src/main/res/layout/`

## 🐛 Troubleshooting

### Build fails with "SDK not found"
- Set ANDROID_HOME environment variable: `export ANDROID_HOME=/path/to/android/sdk`

### APK won't install
- Enable "Install from Unknown Sources" in Android settings
- Check that Android version is 7.0 or higher

### App crashes on startup
- Check Android version compatibility
- Verify all permissions are granted
- Check device logs: `adb logcat | grep Helium`

## 📚 Learn More

- **README.md**: Overview and features
- **ANDROID_README.md**: Detailed Android documentation
- **IMPLEMENTATION_SUMMARY.md**: Technical implementation details

## 🎯 Next Steps

1. **Build the APK**: Use one of the methods above
2. **Install on your device**: Transfer and install the APK
3. **Try it out**: Open the app and browse the web
4. **Customize**: Modify settings to your preference
5. **Extend**: Add your own features!

## 💡 Tips

- The app uses Android's WebView, so it's secure and up-to-date
- Ad blocking happens at the URL level, so it's lightweight
- All settings are saved automatically
- Desktop mode is great for websites that don't work well on mobile

## 🤝 Contributing

This is an open-source project! Feel free to:
- Report bugs
- Suggest features
- Submit pull requests
- Share with friends

---

**Enjoy your privacy-focused mobile browsing experience!** 🎉

Built with ❤️ using Kotlin and Android
