plugins {
    kotlin("kapt")
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = "io.dvlt.themoviedbtest.framework"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isJniDebuggable = true
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/movie/top_rated/\"")
            buildConfigField("String", "tmdbApiKeyV3", "\"76909ebd17e275d556f8e0ed68bbc2b5\"" )
            buildConfigField(
                "String",
                "tmdbApiKeyV4",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NjkwOWViZDE3ZTI3NWQ1NTZmOGUwZWQ2OGJiYzJiNSIsInN1YiI6IjYzOTljOWExNzdjMDFmMDBhMjE0NGRkNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.P4cLbLgDuQ93kq5fPWj84ioH3pbpaMFx8P1bU9Sc7VU\""
            )
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/movie/top_rated/\"")
            buildConfigField("String", "tmdbApiKeyV3", "\"76909ebd17e275d556f8e0ed68bbc2b5\"" )
            buildConfigField(
                "String",
                "tmdbApiKeyV4",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NjkwOWViZDE3ZTI3NWQ1NTZmOGUwZWQ2OGJiYzJiNSIsInN1YiI6IjYzOTljOWExNzdjMDFmMDBhMjE0NGRkNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.P4cLbLgDuQ93kq5fPWj84ioH3pbpaMFx8P1bU9Sc7VU\""
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")

    //Room
    val roomVersion = "2.4.3"

    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")

    //Paging 3
    val pagingVersion = "3.1.1"
    implementation("androidx.paging:paging-runtime:$pagingVersion")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha17")

    //Gson
    implementation("com.google.code.gson:gson:2.9.0")

    //Retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp3 logging-interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    implementation(project(mapOf("path" to ":core")))

}