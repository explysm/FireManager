package app.fire.manager.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import app.fire.manager.BuildConfig
import app.fire.manager.R
import app.fire.manager.domain.manager.PreferenceManager
import app.fire.manager.ui.components.settings.SettingsScaffold
import app.fire.manager.ui.screen.libraries.LibrariesScreen
import app.fire.manager.ui.theme.FireOrange
import app.fire.manager.ui.widgets.about.LinkItem
import app.fire.manager.ui.widgets.about.ListItem
import app.fire.manager.utils.*
import org.koin.androidx.compose.get

class AboutScreen : Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val uriHandler = LocalUriHandler.current
        val prefs: PreferenceManager = get()
        val ctx = LocalContext.current
        val bitmap = remember {
            ctx.getBitmap(R.mipmap.ic_launcher, 60).asImageBitmap()
        }
        var tapCount by remember {
            mutableIntStateOf(0)
        }

        SettingsScaffold(title = stringResource(R.string.title_about)) { pv ->
            Column(
                modifier = Modifier
                    .padding(pv)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .padding(bottom = DimenUtils.navBarPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Image(
                        bitmap = bitmap,
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .glow(FireOrange, radius = 20.dp, alpha = 0.4f)
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            stringResource(R.string.app_name),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Black
                        )

                        Text(
                            text = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                            style = MaterialTheme.typography.bodyMedium,
                            color = FireOrange,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable(
                                enabled = !prefs.isDeveloper
                            ) {
                                tapCount++
                                when (tapCount) {
                                    3 -> ctx.showToast(R.string.msg_seven_left)
                                    5 -> ctx.showToast(R.string.msg_five_left)
                                    8 -> ctx.showToast(R.string.msg_two_left)
                                    10 -> {
                                        ctx.showToast(R.string.msg_unlocked)
                                        prefs.isDeveloper = true
                                    }
                                }
                            }
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        LinkItem(
                            icon = R.drawable.ic_github,
                            label = R.string.label_github,
                            link = BuildConfig.ORG_LINK
                        )

                        LinkItem(
                            icon = R.drawable.ic_discord,
                            label = R.string.label_discord,
                            link = BuildConfig.INVITE_LINK
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.label_team),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                
                ElevatedCard(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                    )
                ) {
                    Constants.TEAM_MEMBERS.forEachIndexed { i, member ->
                        ListItem(
                            text = member.name,
                            subtext = member.role,
                            imageUrl = "https://github.com/${member.username}.png",
                            onClick = {
                                uriHandler.openUri("https://github.com/${member.username}")
                            }
                        )
                        if (i != Constants.TEAM_MEMBERS.lastIndex) {
                            Divider(
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }

                Text(
                    text = stringResource(R.string.label_special_thanks),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                
                ElevatedCard(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                    )
                ) {
                    listOf(
                        Triple("Palm", "Lead developer of Revenge", "palmdevs"),
                        Triple("Pylix", "Past developer of Bunny", "pylixonly"),
                        Triple("Fiery", "Past developer of the iOS tweak", "FieryFlames"),
                        Triple("Maisy", "Past developer of Vendetta", "maisymoe"),
                        Triple("Wing", "Past developer of Manager", "wingio"),
                        Triple("Kasi", "Past developer of the Xposed Module", "redstonekasi"),
                        Triple("rushii", "Developer of the installer", "rushiiMachine"),
                        Triple("Xinto", "Developer of the preference manager", "X1nto")
                    ).forEachIndexed { i, member ->
                        ListItem(
                            text = member.first,
                            subtext = member.second,
                            imageUrl = "https://github.com/${member.third}.png",
                            onClick = {
                                uriHandler.openUri("https://github.com/${member.third}")
                            }
                        )
                        if (i != 7) {
                            Divider(
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }

                val navigator = LocalNavigator.currentOrThrow
                ElevatedCard(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                    ),
                    modifier = Modifier.clickable { navigator.push(LibrariesScreen()) }
                ) {
                    ListItem(
                        text = stringResource(R.string.title_os_libraries),
                        subtext = "View open source libraries used in this project",
                        onClick = { navigator.push(LibrariesScreen()) }
                    )
                }
            }
        }
    }
}