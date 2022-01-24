package com.example.localbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class Fragment : Fragment() {
    private lateinit var broadcastManager: LocalBroadcastManager
    private val volumeDownReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getIntExtra("key_event_extra", KeyEvent.KEYCODE_UNKNOWN)) {
                KeyEvent.KEYCODE_VOLUME_DOWN -> {
                    Toast.makeText(context, "volumeDownKey Pressed", Toast.LENGTH_SHORT).show()
                }
                KeyEvent.KEYCODE_VOLUME_UP -> {
                    Toast.makeText(context, "volumeUpKey Pressed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        broadcastManager = LocalBroadcastManager.getInstance(view.context)
        val filter = IntentFilter().apply { addAction("key_event_action") }
        broadcastManager.registerReceiver(volumeDownReceiver, filter)
    }
    override fun onDestroy() {
        super.onDestroy()
        broadcastManager.unregisterReceiver(volumeDownReceiver)
    }
}
