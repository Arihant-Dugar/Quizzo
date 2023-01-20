package com.example.quizzo.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentQuizBinding
import com.example.quizzo.model.QuestionsData
import com.example.quizzo.questions.Constants

class QuizFragment : Fragment(), View.OnClickListener {

    private lateinit var binding : FragmentQuizBinding

    private lateinit var myqueslist : ArrayList<QuestionsData>

    private  var selectedoptionposition: Int = 0
    private var correctanswer : Int = 0
    private var currentquesposition: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_quiz, container, false)

        // retrieving all hardcoded questions array from Constants object
        myqueslist = Constants.getQuestions()

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

        setQuestion()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(p0: View?) {

        when(p0?.id) {

            R.id.tv_optionOne->{

                selectedOptionView(binding.tvOptionOne, 1)

            }

            R.id.tv_optionTwo->{

                selectedOptionView(binding.tvOptionTwo, 2)



            }

            R.id.tv_optionThree->{

                selectedOptionView(binding.tvOptionThree, 3)



            }

            R.id.tv_optionFour->{

                selectedOptionView(binding.tvOptionFour, 4)




            }

            R.id.btnSubmit->{
                // if user has not selected any option
                if (selectedoptionposition == 0){

                    currentquesposition++ // when current position is increasing means index is increasing
                    //if index is increasing that means we will get a new question from the question list


                    when{
                        currentquesposition <= myqueslist.size->
                        {

                            setQuestion()

                        }
                        else ->
                        {

                        // go to result

                        val username = arguments?.getString("username")
                        val score = correctanswer
                        val bundle = Bundle()
                        bundle.putString("username",username)
                        bundle.putInt("score",score)


                        val navController = findNavController()
                        navController.navigate(R.id.action_quizFragment_to_resultFragment,bundle)

                        }
                    }
                }
                else
                {
                    // if user selected an option
                    // we will check if its correct or incorrect

                    val question = myqueslist[currentquesposition-1]

                    // if selected position 1..4 matches value at the correct answer
                    // correct answer values are 1..4 if the value matches or not matches

                    if (question.correctAnswer!=selectedoptionposition)
                    {
                        answerView(selectedoptionposition, R.drawable.wrong_bg_color)
                    } else
                    {
                        correctanswer++
                    }

                    answerView(question.correctAnswer, R.drawable.correct_bg_color)

                    if (currentquesposition == myqueslist.size){

                        binding.btnSubmit.text = "Finished"
                    } else {

                        binding.btnSubmit.text = "Go to Next Question"
                    }

                    selectedoptionposition = 0 // next question options positions should be 0
                }
            }
        }
}

    private fun answerView(selectedoptionposition: Int, drawableView: Int) {

        when (selectedoptionposition) {
            1->{
                binding.tvOptionOne.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }

            2->{
                binding.tvOptionTwo.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }

            3->{
                binding.tvOptionThree.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }

            4->{
                binding.tvOptionFour.background =
                    context?.let { ContextCompat.getDrawable(it, drawableView) }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {

        val question: QuestionsData = myqueslist[currentquesposition -1]

        binding.tvQuestion.text = question.question
        binding.imageView.setImageResource(question.image)
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        binding.pb.progress = currentquesposition
        binding.tvProgress.text = "$currentquesposition" + "/" + binding.pb.max // displays question number

        // this resets the appearance for everytime a new question comes up
        defaultAppearance()

        // if all questions in the lists are used
        if (currentquesposition == myqueslist.size){
            binding.btnSubmit.text = "Quiz Finished"
        } else {
            binding.btnSubmit.text = "Submit"
        }
    }

    private fun defaultAppearance() {

        //controlling textview that share same behavior
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options){

            option.setTextColor(Color.parseColor("#7A8089"))
            //default appearance
            option.typeface = Typeface.DEFAULT
            option.background = context?.let { ContextCompat.getDrawable(it, R.drawable.default_bg_color) }
        }
    }

    private fun selectedOptionView(tv: TextView, i: Int) {

        //reset text views
        defaultAppearance()

        selectedoptionposition = i

        tv.setTextColor(Color.parseColor("#363A43"))
        //default appearance
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = context?.let { ContextCompat.getDrawable(it, R.drawable.selected_bg_color) }


    }
}