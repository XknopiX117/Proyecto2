package com.example.proyecto1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.slider.Slider

class OptionActivity : AppCompatActivity(), View.OnClickListener {

    private val model: QuizViewModel by viewModels()

    private lateinit var spinnerDifficulty: Spinner
    private lateinit var chkHistory: CheckBox
    private lateinit var chkArtLit: CheckBox
    private lateinit var chkGeography: CheckBox
    private lateinit var chkEntertainment: CheckBox
    private lateinit var chkScience: CheckBox
    private lateinit var chkAll: CheckBox
    private lateinit var sliderNumber: Slider
    private lateinit var switchHint: SwitchCompat

    private var chkList = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)

        model.getModelContext = this
        model.startModelDatabase()

        spinnerDifficulty = findViewById(R.id.spinnerDifficulty)
        chkHistory = findViewById(R.id.chkHistory)
        chkArtLit = findViewById(R.id.chkArtLit)
        chkGeography = findViewById(R.id.chkGeography)
        chkEntertainment = findViewById(R.id.chkEntertainment)
        chkScience = findViewById(R.id.chkScience)
        chkAll = findViewById(R.id.chkAll)
        sliderNumber = findViewById(R.id.sliderNumber)
        switchHint = findViewById(R.id.switchHint)

        chkHistory.setOnClickListener(this)
        chkArtLit.setOnClickListener(this)
        chkGeography.setOnClickListener(this)
        chkEntertainment.setOnClickListener(this)
        chkScience.setOnClickListener(this)
        chkAll.setOnClickListener(this)

        ArrayAdapter.createFromResource(
            this,
            R.array.difficulty,
            R.layout.spinner_selected_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout)
            spinnerDifficulty.adapter = adapter
        }

        loadChkBtnSettings()
        loadNumberQuestionsSettings()
        loadDifficultySettings()
        loadHintSettings()

        chkList = model.getGameSettingsStatusId(0)

        spinnerDifficulty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (adapterView!!.selectedItemPosition) {
                    0 -> model.updateGameSettings(GameSettings(2, "Dificultad", 0))
                    1 -> model.updateGameSettings(GameSettings(2, "Dificultad", 1))
                    2 -> model.updateGameSettings(GameSettings(2, "Dificultad", 2))
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        sliderNumber.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            var maxQ = 5
            override fun onStartTrackingTouch(slider: Slider) {
                maxQ = slider.value.toInt()
            }

            override fun onStopTrackingTouch(slider: Slider) {
                maxQ = slider.value.toInt()
                when (maxQ) {
                    5 -> model.updateGameSettings(GameSettings(1, "No preguntas", 5))
                    6 -> model.updateGameSettings(GameSettings(1, "No preguntas", 6))
                    7 -> model.updateGameSettings(GameSettings(1, "No preguntas", 7))
                    8 -> model.updateGameSettings(GameSettings(1, "No preguntas", 8))
                    9 -> model.updateGameSettings(GameSettings(1, "No preguntas", 9))
                    10 -> model.updateGameSettings(GameSettings(1, "No preguntas", 10))
                }
            }
        })

        switchHint.setOnCheckedChangeListener { _, isChecked ->
            model.updateGameSettings(GameSettings(3, "Pistas", 0))
            if (isChecked) {
                model.updateGameSettings(GameSettings(3, "Pistas", 1))
            }
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.chkHistory -> {
                if(chkAll.isChecked)
                    chkList -= 5

                chkAll.isChecked = false

                if(!chkHistory.isChecked && !chkScience.isChecked && !chkAll.isChecked
                    && !chkEntertainment.isChecked && !chkGeography.isChecked && !chkArtLit.isChecked) {
                    chkHistory.isChecked = true
                    chkList = 1
                }

                if (chkHistory.isChecked)
                    chkList += 1
                else
                    chkList -= 1
                model.updateGameSettings(GameSettings(0, "Temas", chkList))
            }
            R.id.chkArtLit -> {
                if(chkAll.isChecked)
                    chkList -= 5

                chkAll.isChecked = false

                if(!chkHistory.isChecked && !chkScience.isChecked && !chkAll.isChecked
                    && !chkEntertainment.isChecked && !chkGeography.isChecked && !chkArtLit.isChecked) {
                    chkHistory.isChecked = true
                    chkList = 1
                }


                if (chkArtLit.isChecked)
                    chkList += 10
                else
                    chkList -= 10
                model.updateGameSettings(GameSettings(0, "Temas", chkList))
            }
            R.id.chkGeography -> {
                if(chkAll.isChecked)
                    chkList -= 5

                chkAll.isChecked = false

                if(!chkHistory.isChecked && !chkScience.isChecked && !chkAll.isChecked
                    && !chkEntertainment.isChecked && !chkGeography.isChecked && !chkArtLit.isChecked) {
                    chkHistory.isChecked = true
                    chkList = 1
                }


                if (chkGeography.isChecked)
                    chkList += 100
                else
                    chkList -= 100
                model.updateGameSettings(GameSettings(0, "Temas", chkList))
            }
            R.id.chkEntertainment -> {
                if(chkAll.isChecked)
                    chkList -= 5

                chkAll.isChecked = false

                if(!chkHistory.isChecked && !chkScience.isChecked && !chkAll.isChecked
                    && !chkEntertainment.isChecked && !chkGeography.isChecked && !chkArtLit.isChecked) {
                    chkHistory.isChecked = true
                    chkList = 1
                }


                if (chkEntertainment.isChecked)
                    chkList += 1000
                else
                    chkList -= 1000
                model.updateGameSettings(GameSettings(0, "Temas", chkList))
            }
            R.id.chkScience -> {
                if(chkAll.isChecked)
                    chkList -= 5

                chkAll.isChecked = false

                if(!chkHistory.isChecked && !chkScience.isChecked && !chkAll.isChecked
                    && !chkEntertainment.isChecked && !chkGeography.isChecked && !chkArtLit.isChecked) {
                    chkHistory.isChecked = true
                    chkList = 1
                }


                if (chkScience.isChecked)
                    chkList += 10000
                else
                    chkList -= 10000
                model.updateGameSettings(GameSettings(0, "Temas", chkList))
            }
            R.id.chkAll -> {
                chkHistory.isChecked = false
                chkArtLit.isChecked = false
                chkEntertainment.isChecked = false
                chkGeography.isChecked = false
                chkScience.isChecked = false
                chkList = 0

                if (chkAll.isChecked)
                    chkList = 5
                else {
                    chkHistory.isChecked = true
                    chkList = 1
                }
                model.updateGameSettings(GameSettings(0, "Temas", chkList))
            }
        }
    }

    private fun loadChkBtnSettings() {
        when (model.getGameSettingsStatusId(0)) {
            1 -> chkHistory.isChecked = true
            5 -> chkAll.isChecked = true
            10 -> chkArtLit.isChecked = true
            11 -> {
                chkHistory.isChecked = true
                chkArtLit.isChecked = true
            }
            100 -> chkGeography.isChecked = true
            101 -> {
                chkHistory.isChecked = true
                chkGeography.isChecked = true
            }
            110-> {
                chkArtLit.isChecked = true
                chkGeography.isChecked = true
            }
            111 -> {
                chkHistory.isChecked = true
                chkArtLit.isChecked = true
                chkGeography.isChecked = true
            }
            1000 -> chkEntertainment.isChecked = true
            1001 -> {
                chkHistory.isChecked = true
                chkEntertainment.isChecked = true
            }
            1010 -> {
                chkArtLit.isChecked = true
                chkEntertainment.isChecked = true
            }
            1011 -> {
                chkHistory.isChecked = true
                chkArtLit.isChecked = true
                chkEntertainment.isChecked = true
            }
            1100 -> {
                chkGeography.isChecked = true
                chkEntertainment.isChecked = true
            }
            1101 -> {
                chkHistory.isChecked = true
                chkGeography.isChecked = true
                chkEntertainment.isChecked = true
            }
            1110 -> {
                chkArtLit.isChecked = true
                chkGeography.isChecked = true
                chkEntertainment.isChecked = true
            }
            1111 -> {
                chkHistory.isChecked = true
                chkArtLit.isChecked = true
                chkGeography.isChecked = true
                chkEntertainment.isChecked = true
            }
            10000 -> chkScience.isChecked = true
            10001 -> {
                chkHistory.isChecked = true
                chkScience.isChecked = true
            }
            10010 -> {
                chkArtLit.isChecked = true
                chkScience.isChecked = true
            }
            10011 -> {
                chkHistory.isChecked = true
                chkArtLit.isChecked = true
                chkScience.isChecked = true
            }
            10100 -> {
                chkGeography.isChecked = true
                chkScience.isChecked = true
            }
            10101 -> {
                chkHistory.isChecked = true
                chkGeography.isChecked = true
                chkScience.isChecked = true
            }
            10110 -> {
                chkArtLit.isChecked = true
                chkGeography.isChecked = true
                chkScience.isChecked = true
            }
            10111 -> {
                chkHistory.isChecked = true
                chkArtLit.isChecked = true
                chkGeography.isChecked = true
                chkScience.isChecked = true
            }
            11000 -> {
                chkEntertainment.isChecked = true
                chkScience.isChecked = true
            }
            11001 -> {
                chkHistory.isChecked = true
                chkEntertainment.isChecked = true
                chkScience.isChecked = true
            }
            11010 -> {
                chkArtLit.isChecked = true
                chkEntertainment.isChecked = true
                chkScience.isChecked = true
            }
            11011 -> {
                chkHistory.isChecked = true
                chkArtLit.isChecked = true
                chkEntertainment.isChecked = true
                chkScience.isChecked = true
            }
            11100 -> {
                chkGeography.isChecked = true
                chkEntertainment.isChecked = true
                chkScience.isChecked = true
            }
            11101 -> {
                chkHistory.isChecked = true
                chkGeography.isChecked = true
                chkEntertainment.isChecked = true
                chkScience.isChecked = true
            }
            11110 -> {
                chkArtLit.isChecked = true
                chkGeography.isChecked = true
                chkEntertainment.isChecked = true
                chkScience.isChecked = true
            }
            11111 -> {
                chkHistory.isChecked = true
                chkArtLit.isChecked = true
                chkGeography.isChecked = true
                chkEntertainment.isChecked = true
                chkScience.isChecked = true
            }
        }
    }

    private fun loadNumberQuestionsSettings() {
        if (model.getGameSettingsStatusId(1) == 5)
            sliderNumber.value = 5F
        else if (model.getGameSettingsStatusId(1) == 6)
            sliderNumber.value = 6F
        else if (model.getGameSettingsStatusId(1) == 7)
            sliderNumber.value = 7F
        else if (model.getGameSettingsStatusId(1) == 8)
            sliderNumber.value = 8F
        else if (model.getGameSettingsStatusId(1) == 9)
            sliderNumber.value = 9F
        else if (model.getGameSettingsStatusId(1) == 10)
            sliderNumber.value = 10F
    }

    private fun loadDifficultySettings() {
        if (model.getGameSettingsStatusId(2) == 0)
            spinnerDifficulty.setSelection(0)
        else if (model.getGameSettingsStatusId(2) == 1)
            spinnerDifficulty.setSelection(1)
        else if (model.getGameSettingsStatusId(2) == 2)
            spinnerDifficulty.setSelection(2)
    }

    private fun loadHintSettings() {
        switchHint.isChecked = model.getGameSettingsStatusId(3) != 0
    }
}