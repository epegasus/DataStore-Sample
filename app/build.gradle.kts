import com.google.protobuf.gradle.*

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.protobuf") version "0.9.3"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }


    sourceSets {
        getByName("main") {
            proto {
                srcDir("src/main/proto")
            }
        }
    }
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

    // Proto - DataStore with javalite
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.21.12") // Ensure this is consistent

    // gRPC dependencies compatible with javalite
    implementation("io.grpc:grpc-stub:1.52.0")
    implementation("io.grpc:grpc-protobuf-lite:1.52.0") // grpc-protobuf-lite for compatibility with javalite
    implementation("io.grpc:grpc-okhttp:1.52.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.12"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("java") {
                    option("lite") // Ensures javalite-compatible code generation
                }
            }
        }
    }
}
