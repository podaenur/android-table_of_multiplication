package com.zhurov.android_table_of_multiplication.components.widget.dialog

import android.app.Dialog
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import kotlinx.parcelize.Parcelize

class ConfirmActionDialogFragment : DialogFragment() {
    private val args: Arguments by lazy {
        requireNotNull(
            requireArguments().getParcelable(ARGUMENTS_KEY)
        )
    }

    //TODO Implemented custom style
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext()).apply {
            setTitle(args.title)
            setMessage(args.message)
            setPositiveButton(args.positiveButtonText) { dialog, _ ->
                args.positiveAction()
                dialog.dismiss()
            }

            setNegativeButton(args.negativeButtonText) { dialog, _ ->
                dialog.cancel()
            }
        }.create()

    @Parcelize
    data class Arguments(
        @StringRes
        val title: Int,
        @StringRes
        val message: Int,
        @StringRes
        val positiveButtonText: Int,
        @StringRes
        val negativeButtonText: Int,
        val positiveAction: () -> Unit
    ) : Parcelable

    companion object {
        private const val ARGUMENTS_KEY = "ConfirmActionDialogFragment arguments key"

        fun instance(
            @StringRes
            title: Int,
            @StringRes
            message: Int,
            @StringRes
            positiveButtonText: Int,
            @StringRes
            negativeButtonText: Int,
            positiveAction: () -> Unit
        ) = ConfirmActionDialogFragment().apply {
            arguments = bundleOf(
                ARGUMENTS_KEY to Arguments(
                    title = title,
                    message = message,
                    positiveButtonText = positiveButtonText,
                    negativeButtonText = negativeButtonText,
                    positiveAction = positiveAction
                )
            )
        }
    }
}