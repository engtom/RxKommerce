package br.com.wale.getwater

import android.content.Context
import android.content.SharedPreferences
import br.com.wale.getwater.util.Constants

/**
 * Created by Admin on 01/08/17.
 */
class SharedPref(context: Context){

    private val editor: SharedPreferences.Editor
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)

    init {

        editor = sharedPreferences.edit()

    }

    fun setAccessToken(accessToken: String?){

        editor.putString(Constants.PREF_ACCESS_TOKEN, accessToken)
        editor.commit()

    }

    fun getAccessToken():String{

        return this.sharedPreferences.getString(Constants.PREF_ACCESS_TOKEN, "")

    }

    fun setClient(client: String?){

        editor.putString(Constants.PREF_CLIENT, client)
        editor.commit()

    }

    fun getClient():String{

        return this.sharedPreferences.getString(Constants.PREF_CLIENT, "")

    }

    fun setUid(uid: String?){

        editor.putString(Constants.PREF_UID, uid)
        editor.commit()

    }

    fun getUid():String{

        return this.sharedPreferences.getString(Constants.PREF_UID, "")

    }

}