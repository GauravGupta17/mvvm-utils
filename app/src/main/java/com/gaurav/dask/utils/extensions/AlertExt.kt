package com.gaurav.dask.utils.extensions

import android.app.Activity
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import timber.log.Timber

fun Activity.showPrompt(
    msg: String, @StringRes titleResId: Int? = null,
    positiveAction: () -> Unit = {},
    negativeAction: () -> Unit = {},
    positiveButtonTitle: Int = android.R.string.yes,
    negativeButtonTitle: Int = android.R.string.cancel
) {
    Timber.i("showInfoDialog() called with: msg = [$msg], titleResId = [$titleResId]")


    if (!isDestroyed && !isFinishing) {
        AlertDialog.Builder(this).apply {
            titleResId?.let { setTitle(it) }
            setMessage(msg)
            setPositiveButton(positiveButtonTitle, { dialogInterface, i ->
                positiveAction()
            })
            setNegativeButton(negativeButtonTitle, { _, _ -> negativeAction() })
        }.show()
    } else
        Timber.i("showInfoDialog: error: activity is destroyed")
}
