package br.com.wale.getwater.util

import android.content.Context
import android.widget.Toast

/**
 * Created by Admin on 15/08/17.
 */

fun Context.showToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()