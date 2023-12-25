import org.gradle.api.JavaVersion

object ProjectVersion {
    const val COMPILE_SDK_VERSION = 34
    const val TARGET_SDK_VERSION = 31
    const val MIN_SDK_VERSION = 26
    const val VERSION_NAME = "1.0.0"
    const val VERSION_CODE = 1

    val JAVA_VERSION = JavaVersion.VERSION_11
    const val JVM_TARGET_VERSION = "11"
}