package com.ancient.game.ui.starting

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ancient.game.R
import com.ancient.game.core.library.GameFragment
import com.ancient.game.databinding.FragmentStartingBinding

class FragmentStarting : GameFragment<FragmentStartingBinding>(FragmentStartingBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exit.setOnClickListener {
            requireActivity().finish()
        }

        binding.play.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMain_to_fragmentAncientGame)
        }

        binding.privacyText.setOnClickListener {
            requireActivity().startActivity(
                Intent(
                    ACTION_VIEW,
                    Uri.parse("https://www.google.com")
                )
            )
        }
    }
}