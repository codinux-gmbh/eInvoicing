plugins {
    kotlin("jvm")
}


kotlin {
    jvmToolchain(11)
}


val mustangVersion: String by project

val klfVersion: String by project

val assertKVersion: String by project

dependencies {
    implementation("org.mustangproject:library:$mustangVersion")

    implementation("net.codinux.log:klf:$klfVersion")


    testImplementation(kotlin("test"))

    implementation("com.willowtreeapps.assertk:assertk:$assertKVersion")
}


tasks.test {
    useJUnitPlatform()
}