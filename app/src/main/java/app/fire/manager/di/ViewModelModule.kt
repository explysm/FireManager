package app.fire.manager.di

import app.fire.manager.ui.viewmodel.home.HomeViewModel
import app.fire.manager.ui.viewmodel.installer.InstallerViewModel
import app.fire.manager.ui.viewmodel.installer.LogViewerViewModel
import app.fire.manager.ui.viewmodel.libraries.LibrariesViewModel
import app.fire.manager.ui.viewmodel.settings.AdvancedSettingsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {
    factoryOf(::InstallerViewModel)
    factoryOf(::AdvancedSettingsViewModel)
    factoryOf(::HomeViewModel)
    factoryOf(::LogViewerViewModel)
    factoryOf(::LibrariesViewModel)
}