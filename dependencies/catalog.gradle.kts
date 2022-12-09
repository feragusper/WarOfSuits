dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // Versions
            // Main
            version("androidxCoreKtx", "1.9.0")
            version("androidxAppcompat", "1.5.1")
            version("androidxActivityCompose", "1.6.1")
            version("androidxCompose", "1.3.1")
            version("androidxNavigationCompose", "2.5.3")
            version("hilt", "2.42")
            version("hiltNavigationCompose", "1.0.0")

            // Unit Test
            version("junit", "4.13.2")
            version("mockk", "1.10.6")
            version("coroutinesTest", "1.4.3")
            version("kluent", "1.65")

            // Android Test
            version("androidxTextExtJunit", "1.1.4")
            version("espresso", "3.5.0")
            version("dexmakerMockito", "2.28.1")

            // Libraries
            // Main
            alias("androidxCoreKtx").to("androidx.core", "core-ktx").versionRef("androidxCoreKtx")
            alias("androidxAppcompat").to("androidx.appcompat", "appcompat").versionRef("androidxAppcompat")
            alias("androidxActivityCompose").to("androidx.activity", "activity-compose").versionRef("androidxActivityCompose")
            alias("androidxComposeUI").to("androidx.compose.ui", "ui").versionRef("androidxCompose")
            alias("androidxNavigationCompose").to("androidx.navigation", "navigation-compose").versionRef("androidxNavigationCompose")
            alias("androidxComposeMaterial").to("androidx.compose.material", "material").versionRef("androidxCompose")
            alias("androidxComposeUITooling").to("androidx.compose.ui", "ui-tooling").versionRef("androidxCompose")
            alias("hilt").to("com.google.dagger", "hilt-android").versionRef("hilt")
            alias("hiltCompiler").to("com.google.dagger", "hilt-compiler").versionRef("hilt")
            alias("hiltNavigationCompose").to("androidx.hilt", "hilt-navigation-compose").versionRef("hiltNavigationCompose")

            // Unit Test
            alias("junit").to("junit", "junit").versionRef("junit")
            alias("mockk").to("io.mockk", "mockk").versionRef("mockk")
            alias("coroutinesTest").to("org.jetbrains.kotlinx", "kotlinx-coroutines-test").versionRef("coroutinesTest")
            alias("kluent").to("org.amshove.kluent", "kluent").versionRef("kluent")

            // Android Test
            alias("androidxTextExtJunit").to("androidx.test.ext", "junit").versionRef("androidxTextExtJunit")
            alias("espressoCore").to("androidx.test.espresso", "espresso-core").versionRef("espresso")
            alias("androidxComposeTest").to("androidx.compose.ui", "ui-test-junit4").versionRef("androidxCompose")
        }
    }
}
