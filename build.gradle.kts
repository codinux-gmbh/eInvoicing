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

    ext["sourceCodeRepositoryBaseUrl"] = "git.dankito.net/codinux/eInvoicing"

    ext["projectDescription"] = "Tools to work with eInvoices according to EU standard EN 16931"


    repositories {
        mavenCentral()
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