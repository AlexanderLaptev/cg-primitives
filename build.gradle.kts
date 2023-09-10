plugins {
    kotlin("jvm") version "1.9.10"
    id("application")
}

group = "ru.trforcex.uni.cg"
version = "1.0-SNAPSHOT"

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
    mainClass.set("ru.trforcex.uni.cg.primitives.launch.LaunchKt")
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
    jvmToolchain(20)
}
