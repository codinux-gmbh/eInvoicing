buildscript {
    val kotlinVersion: String by extra

    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        // don't know why but otherwise Quarkus is not able to index the classes of e-invoice-domain
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        classpath("com.android.tools.build:gradle:8.5.2")
    }
}


allprojects {
    group = "net.codinux.invoicing"
    version = "0.8.1-SNAPSHOT"

    ext["sourceCodeRepositoryBaseUrl"] = "git.dankito.net/codinux/eInvoicing"

    ext["projectDescription"] = "Tools to work with eInvoices according to EU standard EN 16931"

    // disable Apache 2 license that is added by Gradle Scripts. I never put the library under Apache 2, but Gradle
    // Scripts set it in release pom
    ext["licenseName"] = ""
    ext["licenseUrl"] = ""


    repositories {
        maven {
            setUrl("https://maven.dankito.net/api/packages/codinux/maven")
        }
        mavenCentral()
        google()
        mavenLocal()
    }
}


tasks.register("publishAllToMavenLocal") {
    dependsOn(
        ":e-invoice-domain:publishToMavenLocal"
    )
}

tasks.register("publishAll") {
    dependsOn(
        ":e-invoice-domain:publish"
    )
}