package dev.pegasus.datastore.utils

import android.content.Context
import android.widget.Toast

/**
 * @Author: SOHAIB AHMED
 * @Date: 27,April,2023
 * @Accounts
 *      -> https://github.com/epegasus
 *      -> https://stackoverflow.com/users/20440272/sohaib-ahmed
 */

object GeneralUtils {

    const val TAG = "MyTag"

    fun showToast(context: Context, message: Any) {
        Toast.makeText(context, message.toString(), Toast.LENGTH_SHORT).show()
    }

}