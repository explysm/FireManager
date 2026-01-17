package app.fire.manager.installer.step.installing

import android.content.Context
import android.os.Environment
import app.fire.manager.R
import app.fire.manager.domain.manager.InstallMethod
import app.fire.manager.domain.manager.PreferenceManager
import app.fire.manager.installer.Installer
import app.fire.manager.installer.session.SessionInstaller
import app.fire.manager.installer.shizuku.ShizukuInstaller
import app.fire.manager.installer.shizuku.ShizukuPermissions
import app.fire.manager.installer.step.Step
import app.fire.manager.installer.step.StepGroup
import app.fire.manager.installer.step.StepRunner
import app.fire.manager.utils.isMiui
import app.fire.manager.utils.showToast
import com.github.diamondminer88.zip.ZipWriter
import org.koin.core.component.inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Installs all the modified splits with the users desired [Installer]
 *
 * @see SessionInstaller
 * @see ShizukuInstaller
 *
 * @param lspatchedDir Where all the patched APKs are
 */
class InstallStep(
    private val lspatchedDir: File
): Step() {

    private val preferences: PreferenceManager by inject()
    private val context: Context by inject()

    override val group = StepGroup.INSTALLING
    override val nameRes = R.string.step_installing

    override suspend fun run(runner: StepRunner) {
        runner.logger.i("Installing apks")
        val files = lspatchedDir.listFiles()
            ?.takeIf { it.isNotEmpty() }
            ?: throw Error("Missing APKs from LSPatch step; failure likely")

        if (preferences.savePatchedApk) {
            val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val fireCordDir = downloadDir.resolve("FireCord/saved_apks")
            fireCordDir.mkdirs()
            val outputFile = fireCordDir.resolve("FireCord.apks")
            if (outputFile.exists()) outputFile.delete()
            
            ZipWriter(outputFile).use { zip ->
                files.forEach { file ->
                    zip.writeEntry(file.name, file.readBytes())
                }
            }
            
            withContext(Dispatchers.Main) {
                context.applicationContext.showToast(R.string.msg_saved, outputFile.name)
            }
        }

        val installMethod = if (preferences.installMethod == InstallMethod.SHIZUKU && !ShizukuPermissions.waitShizukuPermissions()) {
            // Temporarily use DEFAULT if SHIZUKU permissions are not granted
            withContext(Dispatchers.Main) {
                context.applicationContext.showToast(R.string.msg_shizuku_denied)
            }
            InstallMethod.DEFAULT
        } else {
            preferences.installMethod
        }

        val installer: Installer = when (installMethod) {
            InstallMethod.DEFAULT -> SessionInstaller(context)
            InstallMethod.SHIZUKU -> ShizukuInstaller(context)
        }

        installer.installApks(silent = !isMiui, *files)
    }

}