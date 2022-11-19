package com.example.proyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels

class ResultsActivity : AppCompatActivity() {

    private lateinit var txtPoints: TextView
    private lateinit var imageResult: ImageView
    private lateinit var user: EditText
    private lateinit var btnSave: Button

    private lateinit var stringPoints: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val model: QuizViewModel by viewModels()

        model.getModelContext = this
        model.startModelDatabase()

        txtPoints = findViewById(R.id.txtPoints)
        imageResult = findViewById(R.id.imageResults)
        user = findViewById(R.id.user)
        btnSave = findViewById(R.id.btnSave)

        stringPoints = getString(R.string.txtPoints) + model.getActualScoreGameSet()

        txtPoints.text = stringPoints

        if (model.getGameSettingsStatusId(2) == 0) {
            if (model.getActualScoreGameSet() in 401..600) {
                imageResult.setImageResource(R.drawable.skiper)
            } else if (model.getActualScoreGameSet() in 201..400) {
                imageResult.setImageResource(R.drawable.tenor)
            } else if (model.getActualScoreGameSet() in 0..200) {
                imageResult.setImageResource(R.drawable.bob1)
            }
        }
        else if (model.getGameSettingsStatusId(2) == 1) {
            if (model.getActualScoreGameSet() in 601..800) {
                imageResult.setImageResource(R.drawable.skiper)
            } else if (model.getActualScoreGameSet() in 401..600) {
                imageResult.setImageResource(R.drawable.tenor)
            } else if (model.getActualScoreGameSet() in 201..400) {
                imageResult.setImageResource(R.drawable.patricio)
            } else if (model.getActualScoreGameSet() in 0..200) {
                imageResult.setImageResource(R.drawable.bob1)
            }
        }
        else if (model.getGameSettingsStatusId(2) == 3) {
            if (model.getActualScoreGameSet() in 751..1000) {
                imageResult.setImageResource(R.drawable.skiper)
            } else if (model.getActualScoreGameSet() in 501..750) {
                imageResult.setImageResource(R.drawable.tenor)
            } else if (model.getActualScoreGameSet() in 251..500) {
                imageResult.setImageResource(R.drawable.patricio)
            } else if (model.getActualScoreGameSet() in 0..250) {
                imageResult.setImageResource(R.drawable.bob1)
            }
        }

        btnSave.setOnClickListener { _ ->

            val user = user.text.toString()

            if(user == "") {
                Toast.makeText(this, getString(R.string.txtWarning), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            model.insertScore(
                Score(model.getNScore(), user ,model.getActualHintsUsedGameSet(), model.getActualScoreGameSet())
            )

            val intent = Intent(this, TopScoreActivity::class.java)
            finish()
            startActivity(intent)
        }

    }
}