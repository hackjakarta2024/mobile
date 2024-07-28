# GrabBites

## Technologies Used
- Programming Language: Kotlin
- Integrated Development Environment (IDE): Android Studio
- API: RESTful API
- Network Library: Retrofit
- Kotlin Coroutinge
- Architecture: MVVM (Model-View-ViewModel)

## Features
- Login
- For you page - food recommendation
- Searching for food

## How to Run
1. Clone this repository or download it as a zip file.
2. Open Android Studio.
3. Import the downloaded or cloned files.
4. Build the application on an Android device or emulator.

## Development Details
### Programming Language: Kotlin
Kotlin is a modern programming language that runs on the Java Virtual Machine (JVM) platform. It is designed to be concise, safe, and interoperable with Java.

### Integrated Development Environment (IDE): Android Studio
Android Studio is the official IDE for Android app development. It offers powerful tools for designing, testing, and debugging Android applications.

### API: RESTful API
RESTful API is used to provide data and services to the Bytes app. It follows the principles of Representational State Transfer (REST) for web development.

### Network Library: Retrofit
Retrofit is a type-safe HTTP client for Android and Java. It simplifies the process of making network requests and parsing JSON responses.

### Kotlin Coroutines
Kotlin Coroutines simplify writing asynchronous code by eliminating the need for callbacks or Futures, making them ideal for handling long-running operations like network or database calls. In the Bytes app, coroutines are used within the MVVM architecture to manage data flow efficiently. 

## Architecture: MVVM (Model-View-ViewModel)
Using MVVM to separate concerns from business logic and ui logic. Retrieving data from backend using repository, and load the data business logic using view model and then pass it to view.
