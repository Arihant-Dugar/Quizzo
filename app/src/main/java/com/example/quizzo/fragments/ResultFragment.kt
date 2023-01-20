package com.example.quizzo.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding : FragmentResultBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_result, container, false)

        val username = arguments?.getString("username")
        val score = arguments?.getInt("score")

        Log.d("score","current score is $score")

        binding.displayResult.text = "${username}! you have scored $score points out of 5"

        binding.playAgain.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_resultFragment_to_homeScreenFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner)
        {
            // handle back event
            view?.findNavController()?.navigate(R.id.action_resultFragment_to_homeScreenFragment)
        }
        return binding.root
    }
}