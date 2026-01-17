package app.fire.manager.ui.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import app.fire.manager.R
import app.fire.manager.domain.manager.PreferenceManager
import app.fire.manager.ui.components.settings.SettingsButton
import app.fire.manager.ui.components.settings.SettingsItemChoice
import app.fire.manager.ui.components.settings.SettingsScaffold
import app.fire.manager.ui.components.settings.SettingsSwitch
import app.fire.manager.ui.viewmodel.settings.AdvancedSettingsViewModel
import app.fire.manager.utils.DimenUtils
import org.koin.androidx.compose.get

class AdvancedSettings: Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val ctx = LocalContext.current
        val prefs: PreferenceManager = get()
        val viewModel: AdvancedSettingsViewModel = getScreenModel()

        SettingsScaffold(title = stringResource(R.string.settings_advanced)) { pv ->
            Column(
                modifier = Modifier
                    .padding(pv)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .padding(bottom = DimenUtils.navBarPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                SettingsItemChoice(
                    label = stringResource(R.string.settings_mirror),
                    pref = prefs.mirror,
                    labelFactory = {
                        it.baseUrl.toUri().authority ?: it.baseUrl
                    },
                    onPrefChange = {
                        prefs.mirror = it
                    }
                )

                SettingsItemChoice(
                    label = stringResource(R.string.install_method),
                    pref = prefs.installMethod,
                    labelFactory = {
                        ctx.getString(it.labelRes)
                    },
                    onPrefChange = viewModel::setInstallMethod,
                )

                SettingsSwitch(
                    label = stringResource(R.string.settings_auto_clear_cache),
                    secondaryLabel = stringResource(R.string.settings_auto_clear_cache_description),
                    pref = prefs.autoClearCache,
                    onPrefChange = {
                        prefs.autoClearCache = it
                    }
                )

                SettingsButton(
                    label = stringResource(R.string.action_clear_cache),
                    onClick = {
                        viewModel.clearCache()
                    }
                )
            }
        }
    }
}