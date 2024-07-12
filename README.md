# Job Search Aid
This Android application aids jobs seekers by providing resources such as FAQs, resume templates, and interview tips. Coupled with that, users can dynamically fetch jobs within a maximum radius of 100 miles based on the location entered and career field desired. As long as a user has created an account and is signed in, they can save jobs and view their saved jobs under their profile information where they have the ability to delete saved jobs.

## Technologies Used
- Kotlin
- Google Firebase
- Cloud Firestore
- XML
- Adzuna API

## Prerequisites
- Download & Install Android Studio <https://developer.android.com/studio>
- Clone this repository or download it as a zip file and open it in Android Studio as a project.
- Ensure the build.gradle files (project level and app level) are synced and do not have errors.
- Download a device from the Device Manager tab in Android Studio with an API version of 34, connecting an Android device via USB may also work.
- Add an app configuration in Android Studio, it should look something like this:
  - <img width="872" alt="Screenshot 2024-07-11 at 12 26 28 PM" src="https://github.com/mahathiryali/jobSearchApp/assets/80718213/f5c1d59e-c9f6-44a3-add5-ecf3f6c6b0b1" style="margin-top: 200px;">
- Create an Adzuna API account (this is free) <https://developer.adzuna.com/>
- Integrate the Adzuna API with valid credentials to fetch jobs.
  - In JobSearch.kt, there are 3 variables that need to be set:
    - apiUrl
    - apiId
    - apiKey
- Modify your local.properties file to point to the location Android SDK.
- You should now be able to run the app.
