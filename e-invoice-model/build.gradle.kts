@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")

    kotlin("plugin.serialization")
}


kotlin {
    jvmToolchain(11)

    jvm()

    js {
        moduleName = "e-invoice-model"
        //binaries.executable()

        browser()

        nodejs()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }


    linuxX64()
    mingwX64()

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    watchosArm64()
    watchosSimulatorArm64()
    tvosArm64()
    tvosSimulatorArm64()

    applyDefaultHierarchyTemplate()


    val kotlinxSerializationVersion: String by project

    val xmlUtilVersion: String by project

    val klfVersion: String by project

    val invoiceTestFilesVersion: String by project
    val assertKVersion: String by project
    val logbackVersion: String by project

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")

            implementation("io.github.pdvrieze.xmlutil:serialization:$xmlUtilVersion")

            implementation("net.codinux.log:klf:$klfVersion")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))

            implementation("com.willowtreeapps.assertk:assertk:$assertKVersion")
        }

        jvmTest.dependencies {
            implementation("net.codinux.invoicing:e-invoice-test-files:$invoiceTestFilesVersion")
        }
    }
}


ext["customArtifactId"] = "e-invoice-model"

apply(from = "../gradle/scripts/publish-codinux.gradle.kts")