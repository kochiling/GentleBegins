buildscript {
    repositories{
        google()
        mavenCentral()
    }
    dependencies {

        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.android.tools.build:gradle:4.1.3")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false

}
