plugins {
    kotlin("jvm")
}


kotlin {
    jvmToolchain(11)
}

java {
    withSourcesJar()
}


val kotlinCoroutinesVersion: String by project

val mustangVersion: String by project

val textInfoExtractor: String by project
val pdfboxTextExtractor: String by project

val angusMailVersion: String by project

val klfVersion: String by project

val assertKVersion: String by project
val xunitVersion: String by project
val logbackVersion: String by project

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")

    implementation("org.mustangproject:library:$mustangVersion")
    implementation("org.mustangproject:validator:$mustangVersion")

    // pdf invoice data extraction
    api("net.dankito.text.extraction:text-info-extractor:$textInfoExtractor")
    api("net.dankito.text.extraction:pdfbox-text-extractor:$pdfboxTextExtractor")

    implementation("org.eclipse.angus:angus-mail:$angusMailVersion")

    implementation("net.codinux.log:klf:$klfVersion")


    testImplementation(kotlin("test"))

    testImplementation("com.willowtreeapps.assertk:assertk:$assertKVersion")
    testImplementation("org.xmlunit:xmlunit-core:$xunitVersion")

    testImplementation("ch.qos.logback:logback-classic:$logbackVersion")
}


tasks.test {
    useJUnitPlatform()
}



ext["customArtifactId"] = "e-invoice"

apply(from = "../gradle/scripts/publish-codinux.gradle.kts")