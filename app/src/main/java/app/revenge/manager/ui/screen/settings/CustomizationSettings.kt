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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import app.revenge.manager.R
import app.revenge.manager.domain.manager.PreferenceManager
import app.revenge.manager.ui.components.settings.SettingsScaffold
import app.revenge.manager.ui.components.settings.SettingsSwitch
import app.revenge.manager.utils.DimenUtils
import org.koin.androidx.compose.get

class CustomizationSettings: Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val prefs: PreferenceManager = get()

        SettingsScaffold(title = stringResource(R.string.settings_customization)) { pv ->
            Column(
                modifier = Modifier
                    .padding(pv)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .padding(bottom = DimenUtils.navBarPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                SettingsSwitch(
                    label = stringResource(R.string.settings_app_icon),
                    secondaryLabel = stringResource(R.string.settings_app_icon_description),
                    pref = prefs.patchIcon,
                    onPrefChange = {
                        prefs.patchIcon = it
                    }
                )
            }
        }
    }
}