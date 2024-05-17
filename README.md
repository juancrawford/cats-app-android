# CatsApp [Android]

CatsApp is an Android application that displays information about different cat breeds using the [TheCatAPI](https://thecatapi.com/).

## Features

- Display a list of cat breeds with images.
- View detailed information about each cat breed.
- Use shared element transition for smooth transition effects.
- Utilize the Glide library for image loading.

## Running the Application

To run CatsApp on an Android device or emulator, follow these steps:

1. Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/cat-app.git
```

2. Open the project in Android Studio.

3. Build and run the application on your preferred device or emulator.

## Running Tests

CatsApp includes instrumented tests. To run the tests, follow these steps:

1. Locate the test classes under the androidTest directory in the app module. Right-click on the desired test class or package and select "Run".

2. Alternatively, you can run all tests at once by right-clicking on the app module directory and selecting "Run tests" or right-clicking on the "ActivityTestSuite".

## Dependencies

The app uses the following libraries:

* Retrofit: For making network requests.
* Glide: For loading and caching images.
* Hilt: For dependency injection.
* Paging 3: For pagination support.
* Espresso: For UI testing.