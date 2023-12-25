// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    @Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
    alias(libs.plugins.androidApplication) apply false

    @Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
    alias(libs.plugins.kotlinGradlePlugin) apply false

    @Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
    alias(libs.plugins.androidLibrary) apply false
}