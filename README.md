# War of suits

Android application of the card game "War".

Architecture
------------
It's based on Clean Architecture: MVI, Use Cases, Repositories and Data Sources. 
Although it may feel like a little overdesigned (since, after all the layers it relies on an object that process everything in memory), it scales to connect it to an API by changing the data sources. 
And, in the end, the main goal of this is exposure and try new tech. 

Tech stack
----------

- Kotlin
- Architecture Components
- Kotlin Coroutines/Flow
- Hilt
- Compose
- Compose Navigation
- JUnit
- Kluent
- Mockk
- Espresso

CI
--
There's already a workflow available for downloading the last stable build at https://github.com/feragusper/Warofsuits/actions

Demo
----
![WOS_DEMO](misc/war_of_suits_demo.gif)

Getting Started
---------------

Since this uses gradle 7, it requires java 11 to run.

- Use `./gradlew assemble` to build it, or run it in Android Studio.
- Use `./gradlew connectedAndroidTest` to run the tests on a connected emulator or device.
- Use `./gradlew test` to run the unit test on your local host.

Support
-------
If you've found an error in this project, please file an issue: https://github.com/feragusper/Warofsuits/issues

Patches are encouraged, and may be submitted by forking this project and submitting a pull request through GitHub.

Contribute
----------
Pull requests are welcome.

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D
