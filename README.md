# Report Search Application

A mobile app for Android that helps users search for and view different types of gemstone reports. The app provides an easy way to look up reports by their unique report numbers and displays detailed information about various gemstones.

## What This App Does

This application allows users to:
- Search for gemstone reports using report numbers
- View detailed information, photos, and specifications for various gemstones

## How to Use the App

1. **Search**: Enter a report number in the search field
2. **View Results**: The app will display the report information including photos and details specific to that gemstone type. You can choose to display results on the same screen as the search or navigate to a separate results screen.

### Development Environment Requirements

| Component | Version/Requirement |
|-----------|-------------------|
| Android Studio | Narwhal (2024.2) or newer |
| JDK | 11 or newer (required by Android Gradle Plugin) |
| Gradle | 8.9.2 |
| Kotlin | 1.9.25 |

### Technical Specifications

| Specification | Value |
|---------------|-------|
| Compile SDK | 35 (Android 14) |
| Target SDK | 35 (Android 14) |
| Minimum SDK | 24 (Android 7.0 - API level 24) |
| Architecture Pattern | Clean Architecture with MVVM |
| UI Framework | Jetpack Compose |

### Key Dependencies

| Library | Version |
|---------|---------|
| Jetpack Compose BOM | 2025.02.00 |
| Hilt | 2.48 |
| Retrofit | 2.9.0 |
| OkHttp Logging | 4.10.0 |
| Kotlin Coroutines | 1.5.1 |
| Navigation Compose | 2.8.9 |
| Lifecycle ViewModel | 2.5.1 |
| Coil | 2.5.0 |
| Gson | 2.10.1 |
| Activity Compose | 1.10.0 |
| ConstraintLayout Compose | 1.0.1 |

### Quick Setup
1. Open the project in Android Studio
2. Wait for Gradle sync to complete
3. Run the application on your device/emulator
4. Start with any of the test report numbers below

## Test Data for Development

For this interview project, use these report numbers for testing your implementation. The API calls are intercepted to return hardcoded JSON responses from the mock services:

| Report Type | Description | Available Reports |
|-------------|-------------|-------------------|
| DG | Diamond Grading Reports | DG1, DG2, DG3, DG4, DG5, DG6 |
| CDOR | Colored Diamond Origin Reports | CDOR1, CDOR2, CDOR3, CDOR4, CDOR5, CDOR6 |
| ER | Emerald Reports | ER1, ER2, ER3, ER4, ER5, ER6 |
| Invalid Input Testing | Error Handling | Any other report numbers will trigger error handling |