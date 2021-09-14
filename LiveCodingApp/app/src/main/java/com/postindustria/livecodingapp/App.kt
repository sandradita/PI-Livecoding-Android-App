package com.postindustria.livecodingapp

import android.app.Application
import com.postindustria.livecodingapp.repo.MovieRepository


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MovieRepository.init(applicationContext)
    }

}