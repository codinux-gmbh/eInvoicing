plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")

    id("io.quarkus")
}


kotlin {
    jvmToolchain(17)
}


val quarkusVersion: String by project

val mustangVersion: String by project

val klfVersion: String by project
val lokiLogAppenderVersion: String by project

dependencies {
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:$quarkusVersion"))
    implementation("io.quarkus:quarkus-kotlin")

    implementation("io.quarkus:quarkus-rest")
    implementation("io.quarkus:quarkus-rest-jackson")

    implementation("io.quarkus:quarkus-smallrye-health")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-micrometer")
    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")

    implementation(project(":e-invoice-domain"))

    compileOnly("org.mustangproject:library:$mustangVersion") // only required for documenting REST API

    implementation("net.codinux.log:klf:$klfVersion")
    implementation("net.codinux.log:quarkus-loki-log-appender:$lokiLogAppenderVersion")
    implementation("net.codinux.log.kubernetes:codinux-kubernetes-info-retriever:$lokiLogAppenderVersion")


    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}


tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("jakarta.persistence.Entity")
    annotation("io.quarkus.test.junit.QuarkusTest")
}
