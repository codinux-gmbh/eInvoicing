@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}


kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        // suppresses compiler warning: [EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING] 'expect'/'actual' classes (including interfaces, objects, annotations, enums, and 'actual' typealiases) are in Beta.
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }


    jvmToolchain(11)

    jvm()

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }


    js {
        moduleName = "e-invoice"
        binaries.executable()

        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    useFirefoxHeadless()
                }
            }
        }

        nodejs {
            testTask {
                useMocha {
                    timeout = "20s" // Mocha times out after 2 s, which is too short for some tests
                }
            }
        }
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


    val kotlinCoroutinesVersion: String by project

    val mustangVersion: String by project

    val textInfoExtractorVersion: String by project
    val textExtractorVersion: String by project
    val pdfBoxAndroidVersion: String by project

    val angusMailVersion: String by project

    val klfVersion: String by project

    val assertKVersion: String by project
    val xunitVersion: String by project
    val jacksonVersion: String by project
    val logbackVersion: String by project

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")

            implementation("net.codinux.log:klf:$klfVersion")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))

            implementation("com.willowtreeapps.assertk:assertk:$assertKVersion")
        }

        val javaCommonMain by creating {
            dependsOn(commonMain.get())

            dependencies {
                implementation("org.mustangproject:library:$mustangVersion")

                // pdf invoice data extraction
                api("net.dankito.text.extraction:text-info-extractor:$textInfoExtractorVersion")
                api("net.dankito.text.extraction:pdfbox-text-extractor:$textExtractorVersion")

                implementation("org.eclipse.angus:angus-mail:$angusMailVersion")
            }
        }

        val javaCommonTest by creating {
            dependsOn(commonTest.get())

            dependencies {
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
                implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

                implementation("org.xmlunit:xmlunit-core:$xunitVersion")
            }
        }

        val jvmMain by getting {
            dependsOn(javaCommonMain)

            dependencies {
                implementation("org.mustangproject:validator:$mustangVersion")
            }
        }
        val jvmTest by getting {
            dependsOn(javaCommonTest)

            dependencies {
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
            }
        }

        val androidMain by getting {
            dependsOn(javaCommonMain)

            dependencies {
                implementation("com.tom-roush:pdfbox-android:$pdfBoxAndroidVersion")
                implementation("net.dankito.text.extraction:pdfbox-android-text-extractor:$textExtractorVersion")
            }
        }
        val androidUnitTest by getting {
            dependsOn(javaCommonTest)

            dependencies {

            }
        }


        val nonJvmMain by creating {
            dependsOn(commonMain.get())
        }
        nativeMain {
            dependsOn(nonJvmMain)
        }
        jsMain {
            dependsOn(nonJvmMain)
        }
        wasmJsMain {
            dependsOn(nonJvmMain)
        }
    }
}


android {
    namespace = "net.codinux.invoicing.einvoice.domain"

    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    lint {
        this.abortOnError = false
        this.checkReleaseBuilds = false
    }
}




ext["customArtifactId"] = "e-invoice"

apply(from = "../gradle/scripts/publish-codinux.gradle.kts")