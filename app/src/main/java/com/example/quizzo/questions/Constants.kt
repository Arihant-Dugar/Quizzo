package com.example.quizzo.questions

import com.example.quizzo.model.QuestionsData
import com.example.quizzo.R

object Constants {
    fun getQuestions() : ArrayList<QuestionsData>{

        val questionslist = ArrayList<QuestionsData>()

        val ques = listOf(QuestionsData(
            1,
            "What is the name of this famous landmark?",
            R.drawable.eiffel_tower,
            "Eiffel Tower",
            "Statue of Liberty",
            "Red Fort",
            "Big Ben",
            1
        ),
        QuestionsData(
            2,
            "Which planet is known as the Red Planet?",
            R.drawable.mars,
            "Earth",
            "Venus",
            "Mars",
            "Neptune",
            3
        ),
        QuestionsData(
            3,
            "Which animal is known as the king of the jungle?",
            R.drawable.lion,
            "Tiger",
            "Lion",
            "Elephant",
            "Fox",
            2
        ),
        QuestionsData(
            4,
            "Name of this Place?",
            R.drawable.desert,
            "Ocean",
            "Glacier",
            "Mountain",
            "Desert",
            4
        ),
        QuestionsData(
            5,
            "Name of this Character in Marvel Movies?",
            R.drawable.iron_man,
            "Iron Man",
            "Black Panther",
            "Captain America",
            "Hulk",
            1
        ))

        questionslist.addAll(ques)

        return questionslist

    }
}