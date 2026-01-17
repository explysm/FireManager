package app.fire.manager.ui.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import app.fire.manager.BuildConfig
import app.fire.manager.R
import app.fire.manager.domain.manager.PreferenceManager
import app.fire.manager.ui.components.settings.SettingsCategory
import app.fire.manager.ui.components.settings.SettingsScaffold
import app.fire.manager.ui.screen.about.AboutScreen
import app.fire.manager.utils.DimenUtils
import org.koin.androidx.compose.get

class SettingsScreen : Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val preferences: PreferenceManager = get()

        SettingsScaffold(title = stringResource(R.string.title_settings)) { pv ->
            Column(
                modifier = Modifier
                    .padding(pv)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .padding(bottom = DimenUtils.navBarPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                SettingsCategory(
                    icon = Icons.Outlined.Palette,
                    text = stringResource(R.string.settings_appearance),
                    subtext = stringResource(R.string.settings_appearance_description),
                    destination = ::AppearanceSettings
                )

                SettingsCategory(
                    icon = Icons.Outlined.AutoAwesome,
                    text = stringResource(R.string.settings_customization),
                    subtext = stringResource(R.string.settings_customization_description),
                    destination = ::CustomizationSettings
                )

                SettingsCategory(
                    icon = Icons.Outlined.Tune,
                    text = stringResource(R.string.settings_advanced),
                    subtext = stringResource(R.string.settings_advanced_description),
                    destination = ::AdvancedSettings
                )

                SettingsCategory(
                    icon = Icons.Outlined.Code,
                    text = stringResource(R.string.settings_developer),
                    subtext = stringResource(R.string.settings_developer_description),
                    destination = ::DeveloperSettings
                )

                SettingsCategory(
                    icon = Icons.Outlined.Info,
                    text = stringResource(R.string.title_about),
                    subtext = buildString {
                        append(stringResource(R.string.app_name))
                        append(" v${BuildConfig.VERSION_NAME}")
                        if (preferences.isDeveloper) {
                            append(" (${BuildConfig.GIT_COMMIT}")
                            if (BuildConfig.GIT_LOCAL_CHANGES || BuildConfig.GIT_LOCAL_COMMITS) {
                                append(" - Local")
                            }
                            append(")")
                        }
                    },
                    destination = ::AboutScreen
                )
            }
        }
    }

}
