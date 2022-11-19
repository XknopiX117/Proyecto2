package com.example.proyecto1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels

class TopScoreActivity : AppCompatActivity() {

    private lateinit var lstTop: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_score)

        val model: QuizViewModel by viewModels()

        model.getModelContext = this
        model.startModelDatabase()

        val top = model.getTopFiveScore()
        val array: MutableList<String> = mutableListOf()

        top.forEach {
            array.add(
                getString(R.string.txtname) + " " + it.name + " " + getString(R.string.txthints) + " " + it.hints + " " + getString(
                    R.string.txtPointsTop
                ) + " " + it.points + "\n"
            )
        }

        lstTop = findViewById(R.id.lstTop)

        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.list_view_items, array)

        lstTop.adapter = arrayAdapter

    }
}