plugins {
    application
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("fm.search.index.Index")
}

tasks.withType<JavaExec>().configureEach {
    javaLauncher.set(
            javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(17))
            }
    )
}