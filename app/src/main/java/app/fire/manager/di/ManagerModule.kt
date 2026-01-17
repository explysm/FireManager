package app.fire.manager.di

import app.fire.manager.domain.manager.DownloadManager
import app.fire.manager.domain.manager.InstallManager
import app.fire.manager.domain.manager.PreferenceManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val managerModule = module {
    singleOf(::DownloadManager)
    singleOf(::PreferenceManager)
    singleOf(::InstallManager)
}