package uni.iit.assignment

import android.app.Application
import android.content.Context
import appModule
import uni.iit.assignment.di.daoModule
import uni.iit.assignment.di.dataSourceModule
import uni.iit.assignment.di.interactorModule
import uni.iit.assignment.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AssignmentApplication : Application() {
    companion object {

        lateinit var instance: AssignmentApplication
            private set

        fun context() : Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@AssignmentApplication)
            modules(listOf(appModule, daoModule, dataSourceModule, repositoryModule, interactorModule))
        }
    }
}

