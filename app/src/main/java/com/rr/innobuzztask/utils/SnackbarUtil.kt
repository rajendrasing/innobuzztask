package com.rr.innobuzztask.utils

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackbarUtil {

    fun show(activity: Activity?, msg: String, duration: Int = Snackbar.LENGTH_LONG) {
        if (activity != null && msg.isEmpty().not()) {
            val snackbar = Snackbar.make(activity.window.decorView.findViewById(android.R.id.content), msg, duration)
            // styling for text color
            //snackbar.setTextColor(activity.getColor(R.color.dark_black_grey))
            snackbar.show()
        }
    }

    // for activity and action
    fun show(
        activity: Activity?, msg: String, actionText: String, clickListener: View.OnClickListener,
        duration: Int = Snackbar.LENGTH_LONG
    ) {
        if (activity != null && msg.isEmpty().not()) {
            Snackbar.make(
                activity.window.decorView.findViewById(android.R.id.content), msg, duration
            ).setAction(actionText, clickListener).show()
        }
    }


    // for view and action
    fun show(view: View?, msg: String, actionText: String, clickListener: View.OnClickListener) {
        if (view != null && msg.isEmpty().not()) {
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction(actionText, clickListener).show()
        }
    }
}