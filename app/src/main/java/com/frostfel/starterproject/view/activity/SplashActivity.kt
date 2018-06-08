package com.frostfel.starterproject.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.frostfel.starterproject.R
import org.jetbrains.anko.intentFor

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(intentFor<MainActivity>()
            )},2000L)
    }
}
