buildscript {
    val kotlinVersion: String by extra

    repositories {
        mavenCentral()
    }
    dependencies {
        // don't know why but otherwise Quarkus is not able to index the classes of e-invoice-domain
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}


allprojects {
    group = "net.codinux.invoicing"
    version = "0.5.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}