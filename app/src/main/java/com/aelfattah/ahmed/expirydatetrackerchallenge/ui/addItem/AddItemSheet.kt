package com.aelfattah.ahmed.expirydatetrackerchallenge.ui.addItem

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.aelfattah.ahmed.expirydatetrackerchallenge.databinding.SheetAddItemBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.DateFormat
import java.util.*


class AddItemSheet : BottomSheetDialogFragment() {

    private lateinit var binding: SheetAddItemBinding

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        binding = SheetAddItemBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        (binding.root.parent as View).setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.transparent
            )
        )
        WindowCompat.setDecorFitsSystemWindows(dialog.window!!, false)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            binding.root.setPadding(0, 0, 0, imeHeight)
            insets
        }
        val layoutParams =
            (binding.root.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior as BottomSheetBehavior<*>?
        if (behavior != null) {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        val newCalendar: Calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year, monthOfYear, dayOfMonth ->
                val newDate: Calendar = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                val dateFormatted = DateFormat.getTimeInstance().format(newDate.time)
                binding.tietExpirydate.setText(dateFormatted)
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )

        binding.tilExpirydate.setOnClickListener { datePickerDialog.show() }
        binding.ivClose.setOnClickListener { dismiss() }
    }
}