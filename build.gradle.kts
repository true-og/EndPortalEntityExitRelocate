import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.jvm.toolchain.JvmVendorSpec

plugins {
    id("java")
    id("java-library")
    id("com.diffplug.spotless") version "7.0.4"
    eclipse
    id("com.gradleup.shadow") version "8.3.8"
}

group = "com.gradyn"
version = "1.1-SNAPSHOT"
description = "EndPortalEntityExitRelocate"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
        vendor.set(JvmVendorSpec.GRAAL_VM)
    }
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://repo.purpurmc.org/snapshots") }
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
    maven { url = uri("https://oss.sonatype.org/content/groups/public/") }
}

dependencies {
    compileOnly("org.purpurmc.purpur:purpur-api:1.19.4-R0.1-SNAPSHOT")
}

tasks.processResources {
    filteringCharset = "UTF-8"
    val v = version.toString()
    inputs.property("pluginVersion", v)
    filesMatching("plugin.yml") {
        expand(mapOf("version" to v))
    }
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("EndPortalEntityExitRelocate")
    archiveVersion.set(project.version.toString())
    archiveClassifier.set("")
}

tasks.jar {
    enabled = false
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

