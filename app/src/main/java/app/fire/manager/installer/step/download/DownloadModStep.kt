package app.fire.manager.installer.step.download

import androidx.compose.runtime.Stable
import app.fire.manager.R
import app.fire.manager.installer.step.download.base.DownloadStep
import java.io.File

/**
 * Downloads the Revenge XPosed module
 *
 * https://github.com/explysm/firexposed
 */
@Stable
class DownloadModStep(
    workingDir: File
): DownloadStep() {

    override val nameRes = R.string.step_dl_mod

    override val downloadFullUrl: String
        get() {
            val customUrl = preferenceManager.getCustomXposedUrl(preferenceManager.packageName)
            return if (preferenceManager.advancedInstallOptions && customUrl.isNotBlank()) {
                customUrl
            } else {
                "https://github.com/explysm/FireXposed/releases/latest/download/app-release.apk"
            }
        }

    override val destination = preferenceManager.moduleLocation
    override val workingCopy = workingDir.resolve("xposed.apk")

}
