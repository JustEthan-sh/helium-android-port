#!/bin/bash

# Helium Browser for Android - Build Script
# This script helps build the Helium Android APK

set -e

echo "======================================"
echo "Helium Browser for Android - Builder"
echo "======================================"
echo ""

# Check for required tools
check_requirements() {
    echo "Checking requirements..."
    
    if ! command -v java &> /dev/null; then
        echo "❌ Java is not installed. Please install JDK 8 or higher."
        exit 1
    fi
    
    JAVA_VERSION=$(java -version 2>&1 | head -1 | cut -d'"' -f2 | cut -d'.' -f1)
    echo "✓ Java version: $(java -version 2>&1 | head -1)"
    
    if [ -z "$ANDROID_HOME" ] && [ -z "$ANDROID_SDK_ROOT" ]; then
        echo "⚠️  Warning: ANDROID_HOME or ANDROID_SDK_ROOT not set."
        echo "   You may need to set this to your Android SDK location."
    else
        echo "✓ Android SDK: ${ANDROID_HOME:-$ANDROID_SDK_ROOT}"
    fi
    
    echo ""
}

# Build debug APK
build_debug() {
    echo "Building debug APK..."
    ./gradlew assembleDebug
    echo ""
    echo "✅ Debug APK built successfully!"
    echo "   Location: app/build/outputs/apk/debug/app-debug.apk"
}

# Build release APK
build_release() {
    echo "Building release APK..."
    echo "⚠️  Note: Release builds require signing configuration"
    ./gradlew assembleRelease
    echo ""
    echo "✅ Release APK built successfully!"
    echo "   Location: app/build/outputs/apk/release/app-release.apk"
}

# Clean build
clean_build() {
    echo "Cleaning build artifacts..."
    ./gradlew clean
    echo "✅ Clean completed!"
}

# Install debug APK to connected device
install_debug() {
    echo "Installing debug APK to device..."
    ./gradlew installDebug
    echo "✅ App installed successfully!"
}

# Show menu
show_menu() {
    echo "Please select an option:"
    echo "1. Build Debug APK"
    echo "2. Build Release APK"
    echo "3. Build and Install Debug"
    echo "4. Clean Build"
    echo "5. Check Requirements"
    echo "6. Exit"
    echo ""
    read -p "Enter your choice [1-6]: " choice
    
    case $choice in
        1)
            check_requirements
            build_debug
            ;;
        2)
            check_requirements
            build_release
            ;;
        3)
            check_requirements
            build_debug
            install_debug
            ;;
        4)
            clean_build
            ;;
        5)
            check_requirements
            ;;
        6)
            echo "Goodbye!"
            exit 0
            ;;
        *)
            echo "Invalid choice. Please try again."
            show_menu
            ;;
    esac
}

# Main
if [ "$1" == "--debug" ]; then
    check_requirements
    build_debug
elif [ "$1" == "--release" ]; then
    check_requirements
    build_release
elif [ "$1" == "--install" ]; then
    check_requirements
    build_debug
    install_debug
elif [ "$1" == "--clean" ]; then
    clean_build
else
    show_menu
fi
