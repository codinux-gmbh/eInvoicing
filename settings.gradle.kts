pluginManagement {
    val kotlinVersion: String by settings
    val quarkusVersion: String by settings


    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
    }

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion

        kotlin("plugin.allopen") version kotlinVersion
        kotlin("plugin.noarg") version kotlinVersion

        id("io.quarkus") version quarkusVersion
    }
}


plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}


rootProject.name = "eInvoicing"

include("e-invoice-domain")

include("invoice-creator")

include("e-invoice-spec-parser")

include("e-invoice-api")
