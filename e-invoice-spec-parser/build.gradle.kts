plugins {
    kotlin("jvm")
}


kotlin {
    jvmToolchain(11)
}


val phGenericodeVersion: String by project

val klfVersion: String by project

val assertKVersion: String by project
val logbackVersion: String by project

dependencies {
    implementation(project(":e-invoice-domain"))

    implementation("com.helger:ph-genericode:$phGenericodeVersion")

    implementation("net.codinux.log:klf:$klfVersion")


    testImplementation(kotlin("test"))

    testImplementation("com.willowtreeapps.assertk:assertk:$assertKVersion")

    testImplementation("ch.qos.logback:logback-classic:$logbackVersion")
}


tasks.test {
    useJUnitPlatform()
}