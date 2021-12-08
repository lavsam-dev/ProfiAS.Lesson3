package lavsam.gb.profias.application

import android.app.Application
import lavsam.gb.profias.di.application
import lavsam.gb.profias.di.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}