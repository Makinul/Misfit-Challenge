package tech.misfit.challenge.base

import android.app.AlertDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import tech.misfit.challenge.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showSimpleDialog(resId: Int) {
        showSimpleDialog(getString(resId))
    }

    fun showSimpleDialog(message: CharSequence?) {
        val alertDialog: Dialog = AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog: DialogInterface?, whichButton: Int -> }
            .create()
        alertDialog.show()
    }

    override fun onResume() {
        super.onResume()
//        changeLanguageProfile()
    }

    var progressDialog: ProgressDialog? = null
        private set

    @JvmOverloads
    fun showProgressDialog(message: String = getString(R.string.loading)) {
        showProgressDialog("", message)
    }

    @JvmOverloads
    fun showProgressDialog(title: String?, message: String?, cancelable: Boolean = false) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, title, message)
            progressDialog!!.setCancelable(cancelable)
        } else {
            progressDialog!!.setCancelable(cancelable)
            progressDialog!!.setTitle(title)
            progressDialog!!.setMessage(message)
            if (!progressDialog!!.isShowing) {
                progressDialog!!.show()
            }
        }
    }

    fun closeProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) progressDialog!!.dismiss()
        progressDialog = null
    }

    @JvmOverloads
    fun showLog(message: String? = "Test Log") {
        if (message == null || message.isEmpty()) return
        Log.v(TAG, message)
    }

    @JvmOverloads
    fun showToast(@StringRes resourceId: Int = R.string.work_in_progress) {
        showToast(getString(resourceId))
    }

    fun showToast(message: String?) {
        if (message == null || message.isEmpty()) return
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "BaseActivity"
    }
}