plugins {
    kotlin("jvm")
}


kotlin {
    jvmToolchain(11)
}


val openHtmlToPdfVersion: String by project
val playwrightVersion: String by project

val jsoupVersion: String by project

val klfVersion: String by project

val assertKVersion: String by project
val logbackVersion: String by project

dependencies {
    api(project(":e-invoice-model"))

    implementation("io.github.openhtmltopdf:openhtmltopdf-pdfbox:$openHtmlToPdfVersion")
    implementation("io.github.openhtmltopdf:openhtmltopdf-slf4j:$openHtmlToPdfVersion")

    implementation("com.microsoft.playwright:playwright:$playwrightVersion")

    implementation("org.jsoup:jsoup:$jsoupVersion")

    implementation("net.codinux.log:klf:$klfVersion")


    testImplementation(kotlin("test"))

    testImplementation("com.willowtreeapps.assertk:assertk:$assertKVersion")

    testImplementation("ch.qos.logback:logback-classic:$logbackVersion")
}


tasks.test {
    useJUnitPlatform()
}



ext["customArtifactId"] = "invoice-creator"

apply(from = "../gradle/scripts/publish-codinux-repo.gradle.kts")