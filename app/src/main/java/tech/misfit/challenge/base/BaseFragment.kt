package tech.misfit.challenge.base

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import tech.misfit.challenge.R

open class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    fun showSimpleDialog(@StringRes resId: Int) {
        showSimpleDialog(getString(resId))
    }

    fun showSimpleDialog(message: CharSequence?) {
        if (activity == null) {
            Toast.makeText(
                context, message,
                Toast.LENGTH_LONG
            ).show()
            return
        }
        val alertDialog: Dialog = AlertDialog.Builder(requireActivity())
            .setMessage(message)
            .setPositiveButton(
                R.string.ok,
                { dialog: DialogInterface?, whichButton: Int -> })
            .create()
        alertDialog.show()
    }

    fun showProgressDialog(@StringRes messageResId: Int) {
        showProgressDialog("", getString(messageResId))
    }

    @JvmOverloads
    fun showProgressDialog(message: String? = getString(R.string.loading)) {
        showProgressDialog("", message)
    }

    @JvmOverloads
    fun showProgressDialog(title: String?, message: String?, cancelable: Boolean = false) {
        if (activity is BaseActivity) {
            (activity as BaseActivity?)!!.showProgressDialog(title, message, cancelable)
        } else {
            if (activity != null)
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
        }
    }

    fun closeProgressDialog() {
        if (getActivity() is BaseActivity) {
            (getActivity() as BaseActivity?)!!.closeProgressDialog()
        }
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
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "BaseFragment"
    }
}