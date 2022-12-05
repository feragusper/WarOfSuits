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
            alias("androidxComposeUI").to("androidx.compose.ui", "ui").versionRef("androidxCompose")
            alias("androidxNavigationCompose").to("androidx.navigation", "navigation-compose").versionRef("androidxNavigationCompose")
            alias("androidxComposeMaterial").to("androidx.compose.material", "material").versionRef("androidxCompose")
            alias("androidxComposeUITooling").to("androidx.compose.ui", "ui-tooling").versionRef("androidxCompose")

            // Unit Test
            alias("junit").to("junit", "junit").versionRef("junit")

            // Android Test
            alias("androidxTextExtJunit").to("androidx.test.ext", "junit").versionRef("androidxTextExtJunit")
            alias("espressoCore").to("androidx.test.espresso", "espresso-core").versionRef("espresso")
        }
    }
}
