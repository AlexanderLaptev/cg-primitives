plugins {
    kotlin("jvm") version "1.9.10"
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "ru.trforcex.uni.cg"
version = "1.2.0"

extra["mainClass"] = "ru.trforcex.uni.cg.primitives.launch.LaunchKt"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set(project.extra["mainClass"] as String)
}

sourceSets {
    main {
        java.srcDir("src/main/java")
        kotlin.srcDir("src/main/kotlin")
    }
    test {
        java.srcDir("src/test/java")
        kotlin.srcDir("src/test/kotlin")
    }
}

kotlin {
    jvmToolchain(8)
}
