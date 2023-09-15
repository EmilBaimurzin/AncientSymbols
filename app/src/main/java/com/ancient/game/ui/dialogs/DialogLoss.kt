package com.ancient.game.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ancient.game.R
import com.ancient.game.core.library.ViewBindingDialog
import com.ancient.game.databinding.DialogLossBinding

class DialogLoss: ViewBindingDialog<DialogLossBinding>(DialogLossBinding::inflate) {
    private val args: DialogLossArgs by navArgs()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), R.style.Dialog_No_Border)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog!!.setCancelable(false)
        dialog!!.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                findNavController().popBackStack(R.id.fragmentMain, false, false)
                true
            } else {
                false
            }
        }

        binding.firstTaskText.text = "${args.task1}/"
        binding.secondTaskText.text = "${args.task2}/"
        binding.thirdTaskText.text = "${args.task3}/"
        binding.fourthTaskText.text = "${args.task4}/"

        binding.home.setOnClickListener {
            findNavController().popBackStack(R.id.fragmentMain, false, false)
        }

        binding.restart.setOnClickListener {
            findNavController().popBackStack(R.id.fragmentMain, false, false)
            findNavController().navigate(R.id.action_fragmentMain_to_fragmentAncientGame)
        }
    }
}