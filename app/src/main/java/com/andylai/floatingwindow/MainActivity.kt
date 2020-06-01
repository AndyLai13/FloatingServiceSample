package com.andylai.floatingwindow

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
	private val REQUEST_CODE_OVERLAY = 10

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if (Settings.canDrawOverlays(this)) {
			startService(Intent(this, FloatingService::class.java))
			finish()
		} else {
			val intent = Intent()
			intent.action = Settings.ACTION_MANAGE_OVERLAY_PERMISSION
			intent.data = Uri.parse("package:$packageName")
			startActivityForResult(intent, REQUEST_CODE_OVERLAY)
		}
	}

	fun hasPermission() {

	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (requestCode == REQUEST_CODE_OVERLAY) {
			Log.d("Andy", "startService")
			startService(Intent(this, FloatingService::class.java))
		}
	}
}
