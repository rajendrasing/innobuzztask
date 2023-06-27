package com.rr.innobuzztask.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.LinearLayout
import android.widget.TextView
import com.rr.innobuzztask.R


class Loading(context: Context) : Dialog(context) {

    lateinit var txtMsg: TextView

    fun build(): Loading {
        this.window?.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        this.setContentView(R.layout.dialog_loading)
        this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.setCancelable(false)
        this.txtMsg = this.findViewById(R.id.msg)
        return this
    }

    fun showProgress() {
        this.show()
    }

    fun showProgress(msg: String) {
        if (this::txtMsg.isInitialized)
            this.txtMsg.text = msg
        this.show()
    }

    fun hideProgress() {
        this.dismiss()
    }

}