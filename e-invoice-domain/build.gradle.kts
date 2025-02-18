@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")

    kotlin("plugin.serialization")
}


kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        // suppresses compiler warning: [EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING] 'expect'/'actual' classes (including interfaces, objects, annotations, enums, and 'actual' typealiases) are in Beta.
        freeCompilerArgs.add("-Xexpect-actual-classes")

        // avoid "variable has been optimised out" in debugging mode
        if (System.getProperty("idea.debugger.dispatch.addr") != null) {
            freeCompilerArgs.add("-Xdebug")
        }
    }


    jvmToolchain(11)

    jvm()

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }

        publishLibraryVariants("release")
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
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    useFirefoxHeadless()
                }
            }
        }
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

    val kotlinxSerializationVersion: String by project

    val mustangVersion: String by project

    val saxonVersion: String by project
    val handlebarsVersion: String by project
    val openHtmlToPdfVersion: String by project
    val jsoupVersion: String by project

    val textInfoExtractorVersion: String by project
    val textExtractorVersion: String by project
    val pdfBoxAndroidVersion: String by project

    val veraPdfVersion: String by project

    val angusMailVersion: String by project

    val ktorVersion: String by project

    val xmlUtilVersion: String by project

    val kmpBaseVersion: String by project
    val klfVersion: String by project

    val invoiceTestFilesVersion: String by project
    val mockkVersion: String by project
    val assertKVersion: String by project
    val xunitVersion: String by project
    val jacksonVersion: String by project
    val logbackVersion: String by project

    sourceSets {
        commonMain.dependencies {
//            implementation(project(":e-invoice-model"))

            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")

            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")

            implementation("io.github.pdvrieze.xmlutil:serialization:$xmlUtilVersion")

            implementation("net.codinux.kotlin:kmp-base:$kmpBaseVersion")
            implementation("net.codinux.log:klf:$klfVersion")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutinesVersion")

            implementation("com.willowtreeapps.assertk:assertk:$assertKVersion")
        }

        val javaCommonMain by creating {
            dependsOn(commonMain.get())

            dependencies {
//                implementation(project(":invoice-creator"))

                implementation("org.mustangproject:library:$mustangVersion")

                implementation("net.sf.saxon:Saxon-HE:$saxonVersion")

                implementation("com.github.jknack:handlebars:$handlebarsVersion")

                // pdf invoice data extraction
                api("net.dankito.text.extraction:text-info-extractor:$textInfoExtractorVersion")
                api("net.dankito.text.extraction:pdfbox-text-extractor:$textExtractorVersion")

                implementation("org.verapdf:validation-model-jakarta:$veraPdfVersion")

                implementation("org.eclipse.angus:angus-mail:$angusMailVersion")

                implementation("io.ktor:ktor-client-cio:$ktorVersion")
            }
        }

        val javaCommonTest by creating {
            dependsOn(commonTest.get())

            dependencies {
                implementation("net.codinux.invoicing:e-invoice-test-files:$invoiceTestFilesVersion")

                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
                implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

                implementation("org.xmlunit:xmlunit-core:$xunitVersion")

                implementation("io.mockk:mockk:$mockkVersion")

                implementation("org.junit.jupiter:junit-jupiter-params:5.11.4")

                implementation("ch.qos.logback:logback-classic:$logbackVersion")
            }
        }

        val jvmMain by getting {
            dependsOn(javaCommonMain)

            dependencies {
                implementation("io.github.openhtmltopdf:openhtmltopdf-pdfbox:$openHtmlToPdfVersion")
//                implementation("io.github.openhtmltopdf:openhtmltopdf-svg-support:$openHtmlToPdfVersion")
                implementation("io.github.openhtmltopdf:openhtmltopdf-slf4j:$openHtmlToPdfVersion")

                implementation("org.jsoup:jsoup:$jsoupVersion")
            }
        }
        val jvmTest by getting {
            dependsOn(javaCommonTest)

            dependencies {
                implementation("org.mustangproject:validator:$mustangVersion")
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
        val nonJvmTest by creating {
            dependsOn(commonTest.get())
        }

        val jsCommonMain by creating {
            dependsOn(nonJvmMain)

            dependencies {
                implementation(npm("big.js", "6.2.2"))
            }
        }
        val jsCommonTest by creating {
            dependsOn(nonJvmTest)
        }
        jsMain {
            dependsOn(jsCommonMain)
        }
        jsTest {
            dependsOn(jsCommonTest)
        }
        val wasmJsMain by getting {
            dependsOn(jsCommonMain)
        }
        val wasmJsTest by getting {
            dependsOn(jsCommonTest)
        }

        nativeMain {
            dependsOn(nonJvmMain)
        }
        val linuxAndMingwMain by creating {
            dependsOn(nativeMain.get())
        }
        val linuxAndMingwTest by creating {
            dependsOn(nativeTest.get())
        }
        linuxMain {
            dependsOn(linuxAndMingwMain)
        }
        linuxTest {
            dependsOn(linuxAndMingwTest)
        }
        mingwMain {
            dependsOn(linuxAndMingwMain)
        }
        mingwTest {
            dependsOn(linuxAndMingwTest)
        }


        appleMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")
        }
        linuxMain.dependencies {
            implementation("io.ktor:ktor-client-curl:$ktorVersion")
        }
        mingwMain.dependencies {
            implementation("io.ktor:ktor-client-winhttp:$ktorVersion")
        }

        jsMain {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktorVersion")
            }
        }
        wasmJsMain {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktorVersion")
            }
        }
    }
}


tasks.named<Test>("jvmTest") {
    useJUnitPlatform() // use JUnit 5, required for @ParameterizedTests
}

afterEvaluate { // below tasks are only available after evaluation
    tasks.named<Test>("testDebugUnitTest") {
        useJUnitPlatform() // use JUnit 5 in Android DebugUnit tests, required for @ParameterizedTests
    }

    tasks.named<Test>("testReleaseUnitTest") {
        useJUnitPlatform() // use JUnit 5 in Android ReleaseUnit tests, required for @ParameterizedTests
    }
}


tasks.register("generateVersionFile") {
    val outputDir = project.projectDir.resolve("src/commonMain/kotlin/net/codinux/invoicing/config")

    doLast {
        val versionFile = outputDir.resolve("Version.kt")
        versionFile.parentFile.mkdirs()
        versionFile.writeText(
            """
            // Generated file. Do not modify!
            package net.codinux.invoicing.config

            object Version {
            
                const val ProjectVersion = "${project.version}"
                
            }
            """.trimIndent()
        )
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    dependsOn("generateVersionFile")
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