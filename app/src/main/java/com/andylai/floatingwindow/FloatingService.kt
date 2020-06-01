package com.andylai.floatingwindow

import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView

class FloatingService : Service() {

    override fun onBind(intent: Intent): IBinder {
       return Binder()
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Andy", "service onCreate")

        val windowManager = applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val contentView  =  FrameLayout(this)
        val image = ImageView(this).apply {
            this.setImageResource(R.mipmap.ic_launcher_round)
        }

        val params = getWindowManagerLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        params.windowAnimations = R.style.AnimationFadeRight
        windowManager.addView(image, params)

        image.setOnClickListener { image.visibility = View.GONE }
    }

    private fun getWindowManagerLayoutParams(width: Int, height: Int): WindowManager.LayoutParams {
        return WindowManager.LayoutParams(width, height,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            else
                WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT)
    }

}
