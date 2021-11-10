package ru.a1exs.srez.api.app

import android.app.Application
import ru.a1exs.srez.api.data.DataManager

class App : Application(){
    companion object{
        lateinit var dm : DataManager
    }

    override fun onCreate() {
        super.onCreate()
        dm = DataManager(baseContext)
    }

}