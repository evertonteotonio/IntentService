package com.example.logonrm.intentservice

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import android.text.TextUtils

/**
 * Created by logonrm on 17/02/2018.
 */
class DownloadService : IntentService(DownloadService::class.java.name) {

    companion object {
        val STATUS_RUNNING = 0
        val STATUS_FINISHED = 1
        val STATUS_ERROR = 2
    }

    override fun onHandleIntent(intent: Intent?) {

        val receiver = intent!!.getParcelableExtra<ResultReceiver>("receiver")
        val url = intent.getStringExtra("url")
        val bundle = Bundle()

        if (TextUtils.isEmpty(url)){
            receiver.send(STATUS_RUNNING, Bundle.EMPTY)

            try {
                val results = dowloadData(url)
                if (null != results && results.isNotEmpty()){
                    bundle.putStringArray("result", results.toTypedArray())
                    receiver.send(STATUS_ERROR, bundle)
                }


            }catch (e: Exception){
                bundle.putString(Intent.EXTRA_TEXT, e.toString())
                receiver.send(STATUS_ERROR, bundle)
            }


        }
        this.stopSelf()

    }

    fun dowloadData(requestUrl: String){

    }
}