package com.example.proyecto1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.isGone
import kotlin.random.Random

class GameActivity : AppCompatActivity(), View.OnClickListener {

    private val model: QuizViewModel by viewModels()

    private lateinit var txtNumberQuestion: TextView
    private lateinit var txtDifficulty: TextView
    private lateinit var txtTheme: TextView
    private lateinit var txtQuestionString: TextView

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btnNext: Button
    private lateinit var btnPrev: Button
    private lateinit var btnHint: Button

    private var hintEasy = 0
    private var backPressed = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        model.getModelContext = this
        model.startModelDatabase()

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (!backPressed){
                        backPressed = true
                        Toast.makeText(
                            this@GameActivity,
                            getString(R.string.txtBackAgain),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else {
                        model.updateGameSettings(GameSettings(4, "Finalizado", 0))
                        finish()
                    }
                }
            }
        )

        val difficultyTextString = arrayOf(
            getString(R.string.txtEasy),
            getString(R.string.txtNormal),
            getString(R.string.txtHard)
        )

        txtNumberQuestion = findViewById(R.id.questionNumber)
        txtDifficulty = findViewById(R.id.difficultyChoice)
        txtTheme = findViewById(R.id.theme)
        txtQuestionString = findViewById(R.id.questionString)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btnNext = findViewById(R.id.btnNext)
        btnPrev = findViewById(R.id.btnPrev)
        btnHint = findViewById(R.id.btnHint)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)

        btnHint.isGone = model.getGameSettingsStatusId(3) == 0
        model.indexQuestionsList = model.getAllStatusGameSet()
        model.getCurrentIndex(model.getActualIndexGameSet())

        var list = model.getAllAnswersId()
        list = list.filter { it != model.getCorrectAnswerId() }

        selectAnswers(
            model.getDifficultySelectedQuestions(),
            model.getAllAnswersId(),
            model.getCorrectAnswerId(),
            model.actualQuestionIndex
        )

        txtDifficulty.text = difficultyTextString[model.getGameSettingsStatusId(2)]
        txtTheme.text = model.getQuestionsTheme()
        txtQuestionString.text = model.getQuestionsText()
        txtNumberQuestion.text =
            getString(R.string.txtQuestionNumber) + model.currentQuestionCounter

        btnHint.text = getString(R.string.btnHint) + " " + model.getActualHintsGameSet().toString()

        if (model.getAnsweredSelectedQuestions()) {
            btn1.isEnabled = false
            btn2.isEnabled = false
            btn3.isEnabled = false
            btn4.isEnabled = false

            btn1.setBackgroundColor(Color.rgb(133, 133, 133))
            btn2.setBackgroundColor(Color.rgb(133, 133, 133))
            btn3.setBackgroundColor(Color.rgb(133, 133, 133))
            btn4.setBackgroundColor(Color.rgb(133, 133, 133))

            if (model.getCurrentCorrectAnsweredSelectedQuestions() == 1)
                txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
            else if (model.getCurrentCorrectAnsweredSelectedQuestions() == 0)
                txtQuestionString.setTextColor(Color.rgb(255, 0, 0))

            if (model.getBUsedHintsSelectedQuestions()) {
                txtQuestionString.setTextColor(Color.rgb(74, 0, 255))
            }
        }

        btnPrev.setOnClickListener { _ ->
            btn1.isEnabled = true
            btn2.isEnabled = true
            btn3.isEnabled = true
            btn4.isEnabled = true

            btn1.setBackgroundColor(Color.rgb(251, 170, 255))
            btn2.setBackgroundColor(Color.rgb(251, 170, 255))
            btn3.setBackgroundColor(Color.rgb(251, 170, 255))
            btn4.setBackgroundColor(Color.rgb(251, 170, 255))

            model.prevQuestion()
            txtQuestionString.setTextColor(Color.rgb(0, 0, 0))
            txtNumberQuestion.text =
                getString(R.string.txtQuestionNumber) + model.currentQuestionCounter
            txtTheme.text = model.getQuestionsTheme()
            txtQuestionString.text = model.getQuestionsText()

            selectAnswers(
                model.getDifficultySelectedQuestions(),
                model.getAllAnswersId(),
                model.getCorrectAnswerId(),
                model.actualQuestionIndex
            )

            if (model.getAnsweredSelectedQuestions()) {
                btn1.isEnabled = false
                btn2.isEnabled = false
                btn3.isEnabled = false
                btn4.isEnabled = false

                btn1.setBackgroundColor(Color.rgb(133, 133, 133))
                btn2.setBackgroundColor(Color.rgb(133, 133, 133))
                btn3.setBackgroundColor(Color.rgb(133, 133, 133))
                btn4.setBackgroundColor(Color.rgb(133, 133, 133))

                if (model.getCurrentCorrectAnsweredSelectedQuestions() == 1) {
                    txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
                } else if (model.getCurrentCorrectAnsweredSelectedQuestions() == 0) {
                    txtQuestionString.setTextColor(Color.rgb(255, 0, 0))
                }

                if (model.getBUsedHintsSelectedQuestions()) {
                    txtQuestionString.setTextColor(Color.rgb(74, 0, 255))
                }

            }

            txtQuestionString.text = model.getQuestionsText()
            txtNumberQuestion.text =
                getString(R.string.txtQuestionNumber) + model.currentQuestionCounter

            if (model.checkAllAnswered()) {
                model.updateGameSettings(GameSettings(4, "Finalizado", 1))
                val intent = Intent(this, ResultsActivity::class.java)
                finish()
                startActivity(intent)
            }
        }

        btnNext.setOnClickListener { _ ->
            btn1.isEnabled = true
            btn2.isEnabled = true
            btn3.isEnabled = true
            btn4.isEnabled = true

            btn1.setBackgroundColor(Color.rgb(251, 170, 255))
            btn2.setBackgroundColor(Color.rgb(251, 170, 255))
            btn3.setBackgroundColor(Color.rgb(251, 170, 255))
            btn4.setBackgroundColor(Color.rgb(251, 170, 255))

            model.nextQuestion()
            txtQuestionString.setTextColor(Color.rgb(0, 0, 0))
            txtNumberQuestion.text =
                getString(R.string.txtQuestionNumber) + model.currentQuestionCounter
            txtTheme.text = model.getQuestionsTheme()
            txtQuestionString.text = model.getQuestionsText()

            selectAnswers(
                model.getDifficultySelectedQuestions(),
                model.getAllAnswersId(),
                model.getCorrectAnswerId(),
                model.actualQuestionIndex
            )

            if (model.getAnsweredSelectedQuestions()) {
                btn1.isEnabled = false
                btn2.isEnabled = false
                btn3.isEnabled = false
                btn4.isEnabled = false

                btn1.setBackgroundColor(Color.rgb(133, 133, 133))
                btn2.setBackgroundColor(Color.rgb(133, 133, 133))
                btn3.setBackgroundColor(Color.rgb(133, 133, 133))
                btn4.setBackgroundColor(Color.rgb(133, 133, 133))

                if (model.getCurrentCorrectAnsweredSelectedQuestions() == 1) {
                    txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
                } else if (model.getCurrentCorrectAnsweredSelectedQuestions() == 0) {
                    txtQuestionString.setTextColor(Color.rgb(255, 0, 0))
                }

                if (model.getBUsedHintsSelectedQuestions()) {
                    txtQuestionString.setTextColor(Color.rgb(74, 0, 255))
                }

            }

            txtQuestionString.text = model.getQuestionsText()
            txtNumberQuestion.text =
                getString(R.string.txtQuestionNumber) + model.currentQuestionCounter

            if (model.checkAllAnswered()) {
                val intent = Intent(this, ResultsActivity::class.java)
                finish()
                model.updateGameSettings(GameSettings(4, "Finalizado", 1))
                startActivity(intent)
            }
        }
        btnHint.setOnClickListener { _ ->

            val gameSet = model.getHintsUsedGameSet()
            gameSet.status = gameSet.status?.plus(1)
            model.updateGameSet(gameSet)

            val selectedQuestions = model.getCurrentSelectedQuestions()
            selectedQuestions.bUsedHints = true
            model.updateAnsweredSelectedQuestions(selectedQuestions)

            val streakGameSet = model.getHintStreakGameSet()

            val hintsGameSet = model.getHintsGameSet()

            if (model.getActualHintsGameSet() > 0 && model.getDifficultySelectedQuestions() >= 0 && !model.getAnsweredSelectedQuestions()) {
                streakGameSet.status = 0
                model.updateGameSet(streakGameSet)
                hintsGameSet.status = hintsGameSet.status?.minus(1)
                model.updateGameSet(hintsGameSet)

                btnHint.text =
                    getString(R.string.btnHint) + " " + model.getActualHintsGameSet().toString()

                list = list.filter { it != model.getCorrectAnswerId() }
                if (model.getDifficultySelectedQuestions() == 2) {
                    selectedQuestions.difficulty = 1
                    model.updateAnsweredSelectedQuestions(selectedQuestions)

                    selectAnswers(
                        model.getDifficultySelectedQuestions(),
                        model.getAllAnswersId(),
                        model.getCorrectAnswerId(),
                        model.actualQuestionIndex
                    )
                } else if (model.getDifficultySelectedQuestions() == 1) {
                    selectedQuestions.difficulty = 0
                    model.updateAnsweredSelectedQuestions(selectedQuestions)

                    selectAnswers(
                        model.getDifficultySelectedQuestions(),
                        model.getAllAnswersId(),
                        model.getCorrectAnswerId(),
                        model.actualQuestionIndex
                    )
                } else if (model.getDifficultySelectedQuestions() == 0) {
                    selectedQuestions.answered = true

                    btn1.setBackgroundColor(Color.rgb(133, 133, 133))
                    btn2.setBackgroundColor(Color.rgb(133, 133, 133))
                    btn3.setBackgroundColor(Color.rgb(133, 133, 133))
                    btn4.setBackgroundColor(Color.rgb(133, 133, 133))

                    if (btn1.text == model.getCorrectAnswerId()) {
                        selectedQuestions.correctAnswered = 1
                        txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
                        Toast.makeText(this, getString(R.string.txtCorrect), Toast.LENGTH_SHORT).show()
                    } else if (btn2.text == model.getCorrectAnswerId()) {
                        selectedQuestions.correctAnswered = 1
                        txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
                        Toast.makeText(this, getString(R.string.txtCorrect), Toast.LENGTH_SHORT).show()
                    }
                    model.updateAnsweredSelectedQuestions(selectedQuestions)
                    hintEasy = 1
                    correctAnswer()
                }
            }
        }
    }

    private fun selectAnswers(
        difficultyQuestions: Int,
        currentAllAnswers: List<String>,
        currentQuestionAnswer: String,
        currentQuestionIndex: Int
    ) {
        var list = currentAllAnswers
        list = list.filter { it != currentQuestionAnswer }
        var newList = listOf(currentQuestionAnswer)

        if (difficultyQuestions == 0) {
            newList += list[0]
            newList = newList.shuffled(Random(currentQuestionIndex))
            btn1.text = newList[0]
            btn2.text = newList[1]
            btn3.visibility = View.INVISIBLE
            btn4.visibility = View.INVISIBLE
        } else if (difficultyQuestions == 1) {
            newList += list[0]
            newList += list[1]
            newList = newList.shuffled(Random(currentQuestionIndex))
            btn1.text = newList[0]
            btn2.text = newList[1]
            btn3.text = newList[2]
            btn3.visibility = View.VISIBLE
            btn4.visibility = View.INVISIBLE

        } else if (difficultyQuestions == 2) {
            newList += list[0]
            newList += list[1]
            newList += list[2]
            newList = newList.shuffled(Random(currentQuestionIndex))
            btn1.text = newList[0]
            btn2.text = newList[1]
            btn3.text = newList[2]
            btn4.text = newList[3]
            btn3.visibility = View.VISIBLE
            btn4.visibility = View.VISIBLE
        }
    }

    override fun onClick(p0: View?) {
        btn1.isEnabled = false
        btn2.isEnabled = false
        btn3.isEnabled = false
        btn4.isEnabled = false

        btn1.setBackgroundColor(Color.rgb(133, 133, 133))
        btn2.setBackgroundColor(Color.rgb(133, 133, 133))
        btn3.setBackgroundColor(Color.rgb(133, 133, 133))
        btn4.setBackgroundColor(Color.rgb(133, 133, 133))

        val selectedQuestions = model.getCurrentSelectedQuestions()
        selectedQuestions.answered = true
        model.updateAnsweredSelectedQuestions(selectedQuestions)

        when (p0?.id) {
            R.id.btn1 -> {
                if (btn1.text == model.getCorrectAnswerId()) {
                    correctAnswer()
                } else {
                    incorrectAnswer()
                }
            }
            R.id.btn2 -> {
                if (btn2.text == model.getCorrectAnswerId()) {
                    correctAnswer()
                } else {
                    incorrectAnswer()
                }
            }
            R.id.btn3 -> {
                if (btn3.text == model.getCorrectAnswerId()) {
                    correctAnswer()
                } else {
                    incorrectAnswer()
                }
            }
            R.id.btn4 -> {
                if (btn4.text == model.getCorrectAnswerId()) {
                    correctAnswer()
                } else {
                    incorrectAnswer()
                }
            }
        }

        if (model.getActualHintStreakGameSet() == 2) {
            val hintsGameSet = model.getHintsGameSet()
            hintsGameSet.status = hintsGameSet.status?.plus(1)
            model.updateGameSet(hintsGameSet)

            val streakGameSet = model.getHintStreakGameSet()
            streakGameSet.status = 0
            model.updateGameSet(streakGameSet)

            btnHint.text =
                getString(R.string.btnHint) + " " + model.getActualHintsGameSet().toString()
        }

    }

    private fun correctAnswer() {
        val scoreGameSet = model.getScoreGameSet()

        if (model.getDifficultySelectedQuestions() == 0) {
            if (hintEasy == 1)
                scoreGameSet.status = scoreGameSet.status?.plus(40)
            else scoreGameSet.status = scoreGameSet.status?.plus(60)

        } else if (model.getDifficultySelectedQuestions() == 1) {
            scoreGameSet.status = scoreGameSet.status?.plus(80)
        } else if (model.getDifficultySelectedQuestions() == 2) {
            scoreGameSet.status = scoreGameSet.status?.plus(100)
        }
        model.updateGameSet(scoreGameSet)

        val streakGameSet = model.getHintStreakGameSet()
        streakGameSet.status = streakGameSet.status?.plus(1)
        model.updateGameSet(streakGameSet)

        val selectedQuestions = model.getCurrentSelectedQuestions()
        selectedQuestions.correctAnswered = 1
        model.updateAnsweredSelectedQuestions(selectedQuestions)

        txtQuestionString.setTextColor(Color.rgb(0, 255, 0))
        Toast.makeText(this, getString(R.string.txtCorrect), Toast.LENGTH_SHORT).show()
        hintEasy = 0
    }

    private fun incorrectAnswer() {
        val streakGameSet = model.getHintStreakGameSet()
        streakGameSet.status = 0
        model.updateGameSet(streakGameSet)

        txtQuestionString.setTextColor(Color.rgb(255, 0, 0))

        val selectedQuestions = model.getCurrentSelectedQuestions()
        selectedQuestions.correctAnswered = 0
        model.updateAnsweredSelectedQuestions(selectedQuestions)

        Toast.makeText(this, getString(R.string.txtIncorrect), Toast.LENGTH_SHORT).show()
    }

}