import com.android.build.api.dsl.ApplicationExtension
import com.easyhz.noffice.build_logic.convention.configureAndroidCompose
import com.easyhz.noffice.build_logic.convention.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
            configureKotlinJvm()
        }
    }
}