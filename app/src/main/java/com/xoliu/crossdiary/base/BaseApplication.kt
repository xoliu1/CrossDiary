package com.xoliu.crossdiary.base

import android.app.Application
import com.tencent.mmkv.MMKV
import com.xoliu.crossdiary.model.local.database.AppDatabase
import com.xoliu.crossdiary.repository.NoteRepository
import leakcanary.LeakCanary
import me.jessyan.autosize.AutoSizeConfig

class BaseApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        AutoSizeConfig.getInstance().setCustomFragment(true);
        MMKV.initialize(this)




    }

}