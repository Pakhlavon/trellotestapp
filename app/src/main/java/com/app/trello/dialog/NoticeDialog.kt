package com.app.trello.dialog

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.Display
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.app.trello.R
import com.app.trello.view.fragments.BaseDialog

class NoticeDialog(
    context: Context,
    private val message: String,
    private var title: String = "",
    private var firstButtonText: String = "",
    private val firstButtonOnClickListener: View.OnClickListener? = null,
    private val widthPercent: Int = 0, private val heightPercent: Int = 0,
    private val isShowCloseImageButton: Boolean = false,
    private val icon: Int? = null,
) : BaseDialog(context) {
    override val layoutId: Int = R.layout.dialog_message

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (widthPercent > 0 || heightPercent > 0) {
            val window: Window? = this.window
            val size = Point()
            val display: Display = window?.windowManager!!.defaultDisplay
            display.getSize(size)
            val width: Int = size.x
            val height: Int = size.y

            if ((widthPercent > 0 && heightPercent == 0)) {
                window.setLayout(
                    (width * widthPercent / 100),
                    WindowManager.LayoutParams.WRAP_CONTENT
                )
            } else {
                window.setLayout((width * widthPercent / 100), height * heightPercent / 100)
            }
            window.setGravity(Gravity.CENTER)
        }

        if (title.isEmpty())
            title = "Successful"

        if (firstButtonText.isEmpty())
            firstButtonText = "OK"


//        btnFirst.setOnClickListener {
//            dismiss()
//            firstButtonOnClickListener?.onClick(it)
//        }
//        imageButtonClose.setOnClickListener{
//            dismiss()
//        }
    }
}