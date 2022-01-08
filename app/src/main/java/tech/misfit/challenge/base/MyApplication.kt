package tech.misfit.challenge.base

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    companion object {
        private const val TAG = "MyApplication"

//        @JvmStatic
//        lateinit var firebaseAnalytics: FirebaseAnalytics
//            private set

        var instance: MyApplication? = null
            private set

        @JvmStatic
        val context: Context?
            get() = instance

//        fun logout() {
//            appPreferences.clearAllData()
//        }
    }
}