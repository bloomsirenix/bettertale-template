plugins {
    id("java-library")
    id("com.gradleup.shadow") version "9.3.1"
    id("run-hytale")
}

group = findProperty("pluginGroup") as String? ?: "com.curseforge.bloomsirenix.btessentials"
version = findProperty("pluginVersion") as String? ?: "1.0.0"
description = findProperty("pluginDescription") as String? ?: "BTEssentials - BetterTale Essentials for Hytale"

repositories {
    mavenCentral()
    maven {
        name = "hytale-release"
        url = uri("https://maven.hytale.com/release")
    }
    maven {
        name = "hytale-pre-release"
        url = uri("https://maven.hytale.com/pre-release")
    }
}

dependencies {
    // Hytale Server API (provided by server at runtime)
    compileOnly("com.hypixel.hytale:Server:2026.01.22-6f8bdbdc4")
    
    // Common dependencies (will be bundled in JAR)
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.yaml:snakeyaml:2.2")
    implementation("org.jetbrains:annotations:24.1.0")
    
    // Test dependencies
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Configure server testing
runHytale {
    // TODO: Update this URL when Hytale server is available
    // Using Paper server as placeholder for testing the runServer functionality
    jarUrl = "https://fill-data.papermc.io/v1/objects/d5f47f6393aa647759f101f02231fa8200e5bccd36081a3ee8b6a5fd96739057/paper-1.21.10-115.jar"
}

tasks {
    // Configure Java compilation
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release = 25
    }
    
    // Configure resource processing
    processResources {
        filteringCharset = Charsets.UTF_8.name()
        
        // Replace placeholders in manifest.json
        val props = mapOf(
            "group" to project.group,
            "version" to project.version,
            "description" to project.description
        )
        inputs.properties(props)
        
        filesMatching("manifest.json") {
            expand(props)
        }
    }
    
    // Configure ShadowJar (bundle dependencies)
    shadowJar {
        archiveBaseName.set("btessentials")
        archiveClassifier.set("")
        
        // Relocate dependencies to avoid conflicts
        relocate("com.google.gson", "com.curseforge.bloomsirenix.btessentials.libs.gson")
        
        
        // Include manifest.json in root
        from("src/main/resources/manifest.json") {
            into("manifest.json")
        }
        
        // Ensure manifest.json is not minimized
        exclude("META-INF/*.SF")
        exclude("META-INF/*.DSA")
        exclude("META-INF/*.RSA")
    }
    
    // Configure tests
    test {
        useJUnitPlatform()
    }
    
    // Make build depend on shadowJar
    build {
        dependsOn(shadowJar)
    }
}

// Configure Java toolchain
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}
