package app.fire.manager.updatechecker.worker

import android.content.Context
import android.content.Intent
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import app.fire.manager.domain.manager.InstallManager
import app.fire.manager.domain.manager.PreferenceManager
import app.fire.manager.domain.repository.RestRepository
import app.fire.manager.network.utils.ApiResponse
import app.fire.manager.updatechecker.reciever.UpdateBroadcastReceiver
import app.fire.manager.utils.DiscordVersion
import app.fire.manager.utils.Intents
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    val api: RestRepository by inject()
    val prefs: PreferenceManager by inject()
    val installManager: InstallManager by inject()

    override suspend fun doWork(): Result {
        return Result.success()
    }

}