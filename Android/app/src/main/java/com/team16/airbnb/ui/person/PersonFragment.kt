package com.team16.airbnb.ui.person

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.team16.airbnb.databinding.FragmentPersonBinding
import com.team16.airbnb.ui.search.detail.DetailSearchViewModel


class PersonFragment : Fragment() {

    private lateinit var binding : FragmentPersonBinding

    private val viewModel: DetailSearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdultPlus()
        setAdultMinus()
        setChildPlus()
        setChildMinus()
        setInfantPlus()
        setInfantMinus()
    }

    private fun setAdultPlus() {
        binding.btnPlusAdult.setOnClickListener {
            viewModel.adult++
            binding.item = viewModel
        }
    }

    private fun setAdultMinus() {
        binding.btnMinusAdult.setOnClickListener {
            viewModel.adult--
            binding.item = viewModel
        }
    }

    private fun setChildPlus() {
        binding.btnPlusKid.setOnClickListener {
            viewModel.child++
            binding.item = viewModel
        }
    }

    private fun setChildMinus() {
        binding.btnMinusKid.setOnClickListener {
            viewModel.child--
            binding.item = viewModel
        }
    }

    private fun setInfantPlus() {
        binding.btnPlusBaby.setOnClickListener {
            viewModel.infant++
            binding.item = viewModel
        }
    }

    private fun setInfantMinus() {
        binding.btnMinusBaby.setOnClickListener {
            viewModel.infant--
            binding.item = viewModel
        }
    }
}