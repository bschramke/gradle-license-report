import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    `maven-publish`
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "0.10.1"
}

group = "com.github.bschramke.gradle"
version = "0.1.0"

repositories {
    google()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly(gradleApi())

    testCompileOnly(gradleTestKit())
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

pluginBundle {
    website = "https://github.com/bschramke/gradle-license-report"
    vcsUrl = "https://github.com/bschramke/gradle-license-report"
    description = "A plugin for generating reports about the licenses of the dependencies for your Gradle project."
    tags = listOf("dependency-management", "license", "reporting")
}

// local maven publishing to ease development
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

// release publishing to gradle plugin repo
gradlePlugin {
    plugins {
        create("dependency-license-plugin") {
            id = "com.github.bschramke.dependency-license-plugin"
            displayName = "Dependency License Plugin"
            description = "A plugin for generating reports about the licenses of the dependencies for your Gradle project."
            implementationClass = "com.github.bschramke.license.LicenseReportPlugin"
        }
    }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}