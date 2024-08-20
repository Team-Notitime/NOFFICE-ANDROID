import com.easyhz.noffice.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

// TODO: crashlytics 설정
class AndroidApplicationFirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.gms.google-services")
            }

            dependencies {
                val bom = libs.findLibrary("firebase-bom").get()
                add("implementation", platform(bom))

                "implementation"(libs.findLibrary("firebase.cloud.messaging").get())
//                "implementation"(libs.findLibrary("firebase.crashlytics").get())
            }
        }
    }
}