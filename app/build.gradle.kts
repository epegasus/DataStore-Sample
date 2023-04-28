import com.google.protobuf.gradle.id

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.protobuf") version "0.9.3" apply false
}

android {
    namespace = "dev.pegasus.datastore"
    compileSdk = 33

    defaultConfig {
        applicationId = "dev.pegasus.datastore"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }


    /*// For Protobuf
    java.sourceSets["main"].java {
        srcDir("src/main/proto")
    }*/
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // Preference - DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Proto - DataStore
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.18.0")
    implementation("com.google.protobuf:protobuf-kotlin:3.21.2")


    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    implementation("io.grpc:grpc-stub:1.52.0")
    implementation("io.grpc:grpc-protobuf:1.52.0")
    implementation("io.grpc:grpc-okhttp:1.52.0")

    implementation("com.google.protobuf:protobuf-java-util:3.21.7")
    implementation("com.google.protobuf:protobuf-kotlin:3.21.2")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")


    //implementation("com.google.protobuf:protobuf-javalite:3.18.0")
    //implementation("com.google.protobuf:protobuf-java:3.22.3")
    //implementation("com.google.protobuf:protobuf-kotlin-lite:3.18.0")
}
/*

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:21.7"
    }

    // Generates the java Protobuf-lite code for the Protobufs in this project. See
    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
    // for more information.
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("kotlin")
            }
        }
    }
}*/
