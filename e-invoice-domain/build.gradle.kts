import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")
}


kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        // suppresses compiler warning: [EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING] 'expect'/'actual' classes (including interfaces, objects, annotations, enums, and 'actual' typealiases) are in Beta.
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }


    jvmToolchain(11)

    jvm()

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

    val textInfoExtractor: String by project
    val pdfboxTextExtractor: String by project

    val angusMailVersion: String by project

    val klfVersion: String by project

    val assertKVersion: String by project
    val xunitVersion: String by project
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

        jvmMain.dependencies {
            implementation("org.mustangproject:library:$mustangVersion")
            implementation("org.mustangproject:validator:$mustangVersion")

            // pdf invoice data extraction
            api("net.dankito.text.extraction:text-info-extractor:$textInfoExtractor")
            api("net.dankito.text.extraction:pdfbox-text-extractor:$pdfboxTextExtractor")

            implementation("org.eclipse.angus:angus-mail:$angusMailVersion")
        }
        jvmTest.dependencies {
            implementation("org.xmlunit:xmlunit-core:$xunitVersion")

            implementation("ch.qos.logback:logback-classic:$logbackVersion")

            implementation("ch.qos.logback:logback-classic:$logbackVersion")
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



ext["customArtifactId"] = "e-invoice"

apply(from = "../gradle/scripts/publish-codinux.gradle.kts")