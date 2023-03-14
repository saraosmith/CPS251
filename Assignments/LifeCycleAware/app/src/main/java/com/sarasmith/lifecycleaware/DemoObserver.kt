package com.sarasmith.lifecycleaware

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.sarasmith.lifecycleaware.ui.main.MainViewModel
import java.time.LocalTime

class DemoObserver: DefaultLifecycleObserver {

    private val LOG_TAG = "DemoObserver"
    private var mvm = MainViewModel

    private fun getDateTime():String{
        return LocalTime.now().toString()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.i(LOG_TAG, "onResume "+
        mvm.setResult("onResume was fired on " + getDateTime() + "\n**********\n"))

    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.i(LOG_TAG, "onPause"+
        mvm.setResult("onPause was fired on " + getDateTime() + "\n**********\n"))

    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.i(LOG_TAG, "onCreate"+
        mvm.setResult("onCreate was fired on " + getDateTime() + "\n"))


    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.i(LOG_TAG, "onStart"+
        mvm.setResult("onStart was fired on " + getDateTime() + "\n"))

    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.i(LOG_TAG, "onStop"+
        mvm.setResult("onStop was fired on " + getDateTime() + "\n"))

    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.i(LOG_TAG, "onDestroy"+
        mvm.setResult("onDestroy was fired on " + getDateTime() + "\n**********\n"))

    }
}
