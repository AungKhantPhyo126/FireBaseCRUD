package co.xware_tech.firebasecrud

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.google.android.gms.tasks.Task
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun hideKeyboard(activity: FragmentActivity?, view: View) {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
fun showDialog(context: Context, message: String, view: View) {
    MaterialAlertDialogBuilder(context)
        .setMessage(message)
        .setNeutralButton(context.resources.getString(R.string.cancel)) { dialog, _ ->
            // Respond to neutral button press
            dialog.dismiss()
            view.findNavController().popBackStack()
        }
        .show()
}