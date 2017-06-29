# Stage1

————————————————————————————————————————————————————————
App Description
————————————————————————————————————————————————————————
The purpose of this application is to apply the lessons learned during the first module of the Udacity course, developing an application that shows popular movies on a phone using the Movie Database APIs (https://www.themoviedb.org/documentation/api)

Main activity displays a grid of popular movies to the user, who can then click the action bar menu to toggle between poplar movies or top rated movies as received from themoviedb.org.

Clicking a movie's poster will take the user to a details screen which will display the movie's title, poster, release date, duration and voting average. There is also the plot synopsis.

User's state is saved on transition between activities, rotation, and when clicking the Android home button. There is also a check on the network connection.

————————————————————————————————————————————————————————
Installing the app:
————————————————————————————————————————————————————————

- Clone the repository on the command line with:
git clone https://github.com/caranExperiments/AndroidPart1.git
- Then Import the project in Android Studio
- An API Key from TheMovieDB service is needed to use this application, please refer to the official api faq here: https://www.themoviedb.org/faq/api
- Once the API key is obtained it needs to be placed in the file: package com.caranha.android.udacity.popmoviesstage1.network : Client.java specifically in String API_KEY = "";

————————————————————————————————————————————————————————
References:
————————————————————————————————————————————————————————

1. Library references:
The Movie Database API (https://www.themoviedb.org/documentation/api)
Picasso - A powerful image downloading and caching library for Android (http://square.github.io/picasso/)
Retrofit: Library for making network calls:http://square.github.io/retrofit/

2. Code references:
The code is primarily referenced from the Udacity Sunshine app ( few steps taken from the exercises as well).

Links that helped:
https://stackoverflow.com/questions/36100187/how-to-start-fragment-from-an-activity
https://stackoverflow.com/questions/2789612/how-can-i-check-whether-an-android-device-is-connected-to-the-web
http://www.vogella.com/tutorials/Retrofit/article.html