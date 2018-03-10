package br.com.wale.getwater.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.wale.getwater.R
import br.com.wale.getwater.SharedPref

/**
 * Created by Admin on 28/07/17.
 */

class SplashActivity : AppCompatActivity(){

    lateinit var prefs : SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        prefs = SharedPref(this)

        if(!prefs.getAccessToken().equals("") && !prefs.getClient().equals("") && !prefs.getUid().equals("")){

            val intent  = Intent(this@SplashActivity, StoresActivity()::class.java)
            startActivity(intent)

        }else{

            val intent  = Intent(this@SplashActivity, LoginActivity()::class.java)
            startActivity(intent)

        }
    }

}