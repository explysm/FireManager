package app.fire.manager.ui.screen.settings

import android.os.Build
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import app.fire.manager.R
import app.fire.manager.domain.manager.PreferenceManager
import app.fire.manager.ui.components.settings.SettingsScaffold
import app.fire.manager.ui.components.settings.SettingsSwitch
import app.fire.manager.ui.widgets.settings.ThemePicker
import app.fire.manager.utils.DimenUtils
import org.koin.androidx.compose.get

class AppearanceSettings: Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val prefs: PreferenceManager = get()

        SettingsScaffold(title = stringResource(R.string.settings_appearance)) { pv ->
            Column(
                modifier = Modifier
                    .padding(pv)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .padding(bottom = DimenUtils.navBarPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                ThemePicker(prefs = prefs)

                Spacer(modifier = Modifier.height(8.dp))

                SettingsSwitch(
                    label = stringResource(R.string.settings_dynamic_color),
                    secondaryLabel = stringResource(R.string.settings_dynamic_color_description),
                    pref = prefs.monet,
                    onPrefChange = {
                        prefs.monet = it
                    },
                    disabled = Build.VERSION.SDK_INT < Build.VERSION_CODES.S
                )

                SettingsSwitch(
                    label = stringResource(R.string.settings_experimental_ui),
                    secondaryLabel = stringResource(R.string.settings_experimental_ui_description),
                    pref = prefs.experimentalUi,
                    onPrefChange = {
                        prefs.experimentalUi = it
                    }
                )

                SettingsSwitch(
                    label = stringResource(R.string.settings_frosted_glass),
                    secondaryLabel = stringResource(R.string.settings_frosted_glass_description),
                    pref = prefs.frostedGlass,
                    onPrefChange = {
                        prefs.frostedGlass = it
                    }
                )
            }
        }
    }
}