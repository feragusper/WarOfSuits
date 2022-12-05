dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // Versions
            // Main
            version("androidxCoreKtx", "1.9.0")
            version("androidxAppcompat", "1.5.1")
            version("androidxActivityCompose", "1.6.1")

            // Unit Test
            version("junit", "4.13.2")

            // Android Test
            version("androidxTextExtJunit", "1.1.4")
            version("espresso", "3.5.0")

            // Libraries
            // Main
            alias("androidxCoreKtx").to("androidx.core", "core-ktx").versionRef("androidxCoreKtx")
            alias("androidxAppcompat").to("androidx.appcompat", "appcompat").versionRef("androidxAppcompat")
            alias("androidxActivityCompose").to("androidx.activity", "activity-compose").versionRef("androidxActivityCompose")

            // Unit Test
            alias("junit").to("junit", "junit").versionRef("junit")

            // Android Test
            alias("androidxTextExtJunit").to("androidx.test.ext", "junit").versionRef("androidxTextExtJunit")
            alias("espressoCore").to("androidx.test.espresso", "espresso-core").versionRef("espresso")
        }
    }
}
