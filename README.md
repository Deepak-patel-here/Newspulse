# NewsPulse - Your Personalized News App

## Overview
NewsPulse is a modern Android news application that provides users with the latest news articles across various categories. The app is built using **Jetpack Compose** and **Ktor Client**, with **Firebase Authentication** for login. It also supports **dark mode** and **database caching** for offline reading.

## Features
### 1. **User Authentication**
- Supports **Google Authentication** via Firebase.
- Secure user login and logout functionality.

### 2. **News Categories**
- Fetches news articles from **Currents API**.
- Users can browse articles from different categories:
  - **General** (default view)
  - **Sports**
  - **Science**
  - **Technology**
- News articles are displayed in a **scrollable list** with images and headlines.

### 3. **Database Caching for Offline Access**
- Uses **Room Database** for storing news articles locally.
- Automatically saves fetched news articles for offline reading.
- Ensures a smooth user experience even with **no internet connection**.

### 4. **Dark Mode Support** 🌙
- Users can **toggle dark mode** from the settings.
- Saves user preference using **Jetpack DataStore**.
- UI elements dynamically adjust based on the selected theme.

### 5. **Live News Fetching**
- Utilizes **Ktor Client** for making network requests.
- Displays **loading indicators** while fetching news.
- Allows users to **refresh news** manually.

### 6. **Detailed News View**
- Clicking on an article opens a **detailed news page**.
- Shows the **full description**, **image**, and **published date**.
- Provides a link to **read the full article** in a web browser.

## Tech Stack
- **Jetpack Compose** (Modern UI)
- **Ktor Client** (Fetching news from API)
- **Room Database** (Local caching)
- **Firebase Authentication** (User login)
- **DataStore Preferences** (Dark mode settings)
- **LiveData & ViewModel** (State management)
- **Navigation Component** (Seamless navigation)

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/Deepak-patel-here/NewsPulse.git
   ```
2. Open the project in **Android Studio**.
3. Run the app on an **emulator** or **physical device**.

## API Setup
- Sign up for the **Currents API** at [Currents API](https://currentsapi.services/).
- Add your **API key** in `ApiClient.kt`:
  ```kotlin
  private const val API_KEY = "your_api_key_here"
  ```

## Contribution
- Feel free to fork the project and submit **pull requests**!
- Report any issues in the **GitHub Issues** section.

## License
This project is licensed under the **MIT License**.

---

Enjoy using **NewsPulse** and stay informed! 📢📲

