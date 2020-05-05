package com.github.bschramke.license

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.util.GradleVersion

class LicenseReportPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        assertCompatibleGradleVersion()
    }

    private fun assertCompatibleGradleVersion() {
        if (GradleVersion.current() < GradleVersion.version(MINIMUM_REQUIRED_GRADLE_VERSION)) {
            throw GradleException("License Report Plugin requires Gradle $MINIMUM_REQUIRED_GRADLE_VERSION. ${GradleVersion.current()} detected.")
        }
    }

    companion object {
        const val MINIMUM_REQUIRED_GRADLE_VERSION = "4.10"
    }
}