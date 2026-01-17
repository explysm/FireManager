package app.revenge.manager.ui.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@Composable
fun SettingsSwitch(
    label: String,
    secondaryLabel: String? = null,
    disabled: Boolean = false,
    pref: Boolean,
    onPrefChange: (Boolean) -> Unit,
) {
    SettingsItem(
        modifier = Modifier.clickable(enabled = !disabled) { onPrefChange(!pref) },
        text = { Text(text = label) },
        secondaryText = {
            secondaryLabel?.let {
                Text(text = it)
            }
        }
    ) {
        Switch(
            checked = pref,
            enabled = !disabled,
            onCheckedChange = { onPrefChange(!pref) },
            modifier = Modifier.scale(0.9f)
        )
    }
}