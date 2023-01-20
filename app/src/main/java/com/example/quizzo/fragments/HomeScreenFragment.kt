package com.example.quizzo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentHomeScreenBinding

class HomeScreenFragment : Fragment() {

    // Data binding to bind fragments code to its layout and vice-versa.
    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home_screen,container,false)

        binding.startBtn.setOnClickListener {

            // checking if the name edittext is not empty
            if (binding.etName.text!!.isNotEmpty())
            {
                // takes us to the next fragment to play game
                // storing name of the user and passing it to next fragment through bundle object.
                val name = binding.etName.text.toString()
                val bundle = Bundle()
                bundle.putString("username",name)

                // NavController(reference) to navigate between fragments as drawn in nav_graph
                val navController = findNavController()
                navController.navigate(R.id.action_homeScreenFragment_to_quizFragment,bundle)
            }
            else
            {
                Toast.makeText(context, "Enter your name to start", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}