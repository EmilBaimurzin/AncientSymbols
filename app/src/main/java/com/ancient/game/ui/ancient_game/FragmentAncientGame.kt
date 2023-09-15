package com.ancient.game.ui.ancient_game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ancient.game.R
import com.ancient.game.core.library.GameFragment
import com.ancient.game.databinding.FragmentAncientGameBinding
import com.ancient.game.domain.ancient_game.adapter.AncientGameAdapter

class FragmentAncientGame: GameFragment<FragmentAncientGameBinding>(FragmentAncientGameBinding::inflate) {
    private val viewModel: AncientGameViewModel by viewModels()
    private lateinit var gameAdapter: AncientGameAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        binding.shuffle.setOnClickListener {
            viewModel.shuffle()
        }

        binding.home.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.list.observe(viewLifecycleOwner) {
            gameAdapter.items = it.toMutableList()
            gameAdapter.notifyDataSetChanged()
        }
        viewModel.task1.observe(viewLifecycleOwner) {
            binding.firstTaskText.text = "$it/"
            checkTasks()
        }
        viewModel.task2.observe(viewLifecycleOwner) {
            binding.secondTaskText.text = "$it/"
            checkTasks()
        }
        viewModel.task3.observe(viewLifecycleOwner) {
            binding.thirdTaskText.text = "$it/"
            checkTasks()
        }
        viewModel.task4.observe(viewLifecycleOwner) {
            binding.fourthTaskText.text = "$it/"
            checkTasks()
        }
        viewModel.moves.observe(viewLifecycleOwner) {
            binding.moves.text = it.toString()

            if (it == 0 && viewModel.gameState) {
                viewModel.gameState = false
                findNavController().navigate(FragmentAncientGameDirections.actionFragmentAncientGameToDialogLoss(
                    viewModel.task1.value!!,
                    viewModel.task2.value!!,
                    viewModel.task3.value!!,
                    viewModel.task4.value!!,
                ))
            }
        }
    }

    private fun checkTasks() {
        if (viewModel.task1.value!! == 15 && viewModel.task2.value!! == 15 && viewModel.task3.value!! == 15 && viewModel.task4.value == 15 && viewModel.gameState) {
            viewModel.gameState = false
            findNavController().navigate(R.id.action_fragmentAncientGame_to_dialogWin)
        }
    }

    private fun initAdapter() {
        gameAdapter = AncientGameAdapter {
            if (viewModel.moves.value!! > 0) {
                viewModel.itemClick(it)
            }
        }
        with(binding.gameRV) {
            adapter = gameAdapter
            layoutManager = GridLayoutManager(requireContext(), 6).apply {
                orientation = GridLayoutManager.VERTICAL
            }
        }
    }
}