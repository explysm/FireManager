package app.fire.manager.ui.screen.libraries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import app.fire.manager.R
import app.fire.manager.ui.components.ThinDivider
import app.fire.manager.ui.components.settings.SettingsScaffold
import app.fire.manager.ui.viewmodel.libraries.LibrariesViewModel
import app.fire.manager.ui.widgets.libraries.LibraryItem

class LibrariesScreen: Screen {

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    override fun Content() {
        val viewModel: LibrariesViewModel = getScreenModel()

        SettingsScaffold(title = stringResource(R.string.title_libraries)) { pv ->
            LazyColumn(
                modifier = Modifier.padding(pv)
            ) {
                itemsIndexed(viewModel.libraries.libraries) { i, library ->
                    Column {
                        LibraryItem(
                            library = library
                        )
                        if(i != viewModel.libraries.libraries.lastIndex) {
                            ThinDivider()
                        }
                    }
                }
            }
        }
    }
}