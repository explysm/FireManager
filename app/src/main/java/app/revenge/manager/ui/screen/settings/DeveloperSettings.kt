package app.revenge.manager.ui.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import app.revenge.manager.R
import app.revenge.manager.domain.manager.InstallManager
import app.revenge.manager.domain.manager.PreferenceManager
import app.revenge.manager.ui.components.settings.SettingsButton
import app.revenge.manager.ui.components.settings.SettingsScaffold
import app.revenge.manager.ui.components.settings.SettingsSwitch
import app.revenge.manager.ui.components.settings.SettingsTextField
import app.revenge.manager.utils.DimenUtils
import org.koin.androidx.compose.get
import java.io.File

class DeveloperSettings: Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val prefs: PreferenceManager = get()
        val installManager: InstallManager = get()

        var moduleLocation by remember {
            mutableStateOf(prefs.moduleLocation.absolutePath)
        }

        SettingsScaffold(title = stringResource(R.string.settings_developer)) { pv ->
            Column(
                modifier = Modifier
                    .padding(pv)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .padding(bottom = DimenUtils.navBarPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                SettingsTextField(
                    label = stringResource(R.string.settings_package_name),
                    pref = prefs.packageName,
                    onPrefChange = {
                        prefs.packageName = it
                        installManager.getInstalled()
                    }
                )

                SettingsSwitch(
                    label = "Bypass ISP Blocks",
                    secondaryLabel = "Redirects GitHub requests through a proxy server.",
                    pref = prefs.bypassIspBlocks,
                    onPrefChange = { prefs.bypassIspBlocks = it }
                )

                if (prefs.bypassIspBlocks) {
                    SettingsTextField(
                        label = "Proxy URL",
                        pref = prefs.proxyUrl,
                        onPrefChange = { prefs.proxyUrl = it }
                    )
                }

                SettingsSwitch(
                    label = stringResource(R.string.settings_debuggable),
                    secondaryLabel = stringResource(R.string.settings_debuggable_description),
                    pref = prefs.debuggable,
                    onPrefChange = { prefs.debuggable = it }
                )

                SettingsSwitch(
                    label = stringResource(R.string.settings_advanced_install_options),
                    secondaryLabel = stringResource(R.string.settings_advanced_install_options_description),
                    pref = prefs.advancedInstallOptions,
                    onPrefChange = { prefs.advancedInstallOptions = it }
                )

                if (prefs.advancedInstallOptions) {
                    SettingsTextField(
                        label = stringResource(R.string.settings_custom_xposed_url),
                        pref = prefs.customXposedBundleUrl,
                        onPrefChange = { prefs.customXposedBundleUrl = it }
                    )
                }

                SettingsTextField(
                    label = stringResource(R.string.settings_module_location),
                    supportingText = stringResource(R.string.settings_module_location_description),
                    pref = moduleLocation,
                    onPrefChange = {
                        moduleLocation = it
                        prefs.moduleLocation = File(it)
                    }
                )

                SettingsButton(
                    label = stringResource(R.string.settings_module_location_reset),
                    onClick = {
                        prefs.moduleLocation = prefs.DEFAULT_MODULE_LOCATION
                    }
                )

                SettingsSwitch(
                    label = stringResource(R.string.settings_save_patched),
                    secondaryLabel = stringResource(R.string.settings_save_patched_description),
                    pref = prefs.savePatchedApk,
                    onPrefChange = { prefs.savePatchedApk = it }
                )

                SettingsSwitch(
                    label = stringResource(R.string.settings_allow_downgrade),
                    secondaryLabel = stringResource(R.string.settings_allow_downgrade_description),
                    pref = prefs.allowDowngrade,
                    onPrefChange = { prefs.allowDowngrade = it }
                )
            }
        }
    }
}