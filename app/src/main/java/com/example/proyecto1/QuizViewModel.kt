package com.example.proyecto1

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room

class QuizViewModel : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    private lateinit var context: Context
    private lateinit var scoreDao: ScoreDao
    private lateinit var questionsDao: QuestionsDao
    private lateinit var gameSettingsDao: GameSettingsDao
    private lateinit var gameSetDao: GameSetDao
    private lateinit var selectedQuestionsDao: SelectedQuestionsDao

    private var currentQuestionIndex = 0
    private var currentListIndex = 0
    private var indexList = listOf(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)

    fun getCurrentIndex(index: Int){
        currentListIndex = index
        currentQuestionIndex = indexList[currentListIndex]
    }

    //Queries
    //Initialize database
    var getModelContext: Context
        get() = context
        set(value) {
            context = value
        }

    fun startModelDatabase() {
        val db = Room.databaseBuilder(
            context,
            QuizDatabase::class.java, "quizapp"
        )
            .allowMainThreadQueries()
            .build()

        scoreDao = db.scoreDao()
        questionsDao = db.questionsDao()
        gameSettingsDao = db.gameSettingsDao()
        gameSetDao = db.gameSetDao()
        selectedQuestionsDao = db.selectedQuestionsDao()
    }

    //Questions
    val getAllNumberQuestions: Int
        get() = questionsDao.getAllNumberQuestions()

    fun insertQuestions(questions: Questions) {
        questionsDao.insertQuestions(questions)
    }

    fun getQuestionsData(id: Int): Questions {
        return questionsDao.getQuestionsData(id)
    }

    fun getQuestionsText(): String {
        return questionsDao.getQuestionsText(currentQuestionIndex)
    }

    fun getQuestionsTheme(): String {
        return questionsDao.getQuestionsTheme(currentQuestionIndex)
    }

    fun getQuestionsAllThemeSelected(): List<Int> {
        return questionsDao.getQuestionsAllThemeSelected()
    }

    fun getQuestionsThemeSelected(theme: String): List<Int> {
        return questionsDao.getQuestionsThemeSelected(theme)
    }

    fun getQuestionsThemeSelected2(theme1: String, theme2: String): List<Int> {
        return questionsDao.getQuestionsThemeSelected2(theme1, theme2)
    }

    fun getQuestionsThemeSelected3(theme1: String, theme2: String, theme3: String): List<Int> {
        return questionsDao.getQuestionsThemeSelected3(theme1, theme2, theme3)
    }

    fun getQuestionsThemeSelected4(theme1: String, theme2: String, theme3: String, theme4: String): List<Int> {
        return questionsDao.getQuestionsThemeSelected4(theme1, theme2, theme3, theme4)
    }

    //Settings
    fun insertGameSettings(gameSettings: GameSettings) {
        gameSettingsDao.insertGameSettings(gameSettings)
    }

    fun getGameSettingsStatusId(id: Int): Int {
        return gameSettingsDao.getGameSettingsStatusId(id)
    }

    fun updateGameSettings(gameSettings: GameSettings) {
        gameSettingsDao.updateGameSettings(gameSettings)
    }

    //GameSet
    fun insertGameSet(gameSet: GameSet) {
        gameSetDao.insertGameSet(gameSet)
    }

    fun updateGameSet(gameSet: GameSet) {
        gameSetDao.updateGameSet(gameSet)
    }

    fun getActualHintsGameSet(): Int {
        return gameSetDao.getActualHintsGameSet()
    }

    fun getHintsGameSet(): GameSet {
        return gameSetDao.getHintsGameSet()
    }

    fun getScoreGameSet(): GameSet {
        return gameSetDao.getScoreGameSet()
    }

    fun getActualHintStreakGameSet(): Int {
        return gameSetDao.getActualHintStreakGameSet()
    }

    fun getHintStreakGameSet(): GameSet {
        return gameSetDao.getHintStreakGameSet()
    }

    fun getActualIndexGameSet(): Int {
        return gameSetDao.getActualIndexGameSet()
    }

    fun getHintsUsedGameSet(): GameSet {
        return gameSetDao.getHintsUsedGameSet()
    }

    fun getActualHintsUsedGameSet(): Int {
        return gameSetDao.getActualHintsUsedGameSet()
    }

    fun getActualScoreGameSet(): Int {
        return gameSetDao.getActualScoreGameSet()
    }

    fun getAllGameSet(): List<GameSet> {
        return gameSetDao.getAllGameSet()
    }

    fun getAllNGameSet(): Int {
        return gameSetDao.getAllNGameSet()
    }

    fun getAllStatusGameSet(): List<Int> {
        return gameSetDao.getAllStatusGameSet()
    }

    fun deleteGameSet() {
        gameSetDao.deleteGameSet()
    }

    //SelectedQuestions
    fun insertSelectedQuestions(selectedQuestions: SelectedQuestions) {
        selectedQuestionsDao.insertSelectedQuestions(selectedQuestions)
    }

    fun getSelectedQuestionsId(id: Int): Questions {
        return selectedQuestionsDao.getSelectedQuestionsId(id)
    }

    fun getAllNSelectedQuestions(): Int {
        return selectedQuestionsDao.getAllNSelectedQuestions()
    }

    fun deleteSelectedQuestions() {
        selectedQuestionsDao.deleteSelectedQuestions()
    }

    fun getAllAnswersId(): List<String> {
        return listOf(
            selectedQuestionsDao.getallAnswers0Id(currentQuestionIndex),
            selectedQuestionsDao.getallAnswers1Id(currentQuestionIndex),
            selectedQuestionsDao.getallAnswers2Id(currentQuestionIndex),
            selectedQuestionsDao.getallAnswers3Id(currentQuestionIndex)
        )
    }

    fun getCorrectAnswerId(): String {
        return selectedQuestionsDao.getCorrectAnswerId(currentQuestionIndex)
    }

    fun getAllAnswer0Id(id: Int): String {
        return selectedQuestionsDao.getallAnswers0Id(id)
    }

    fun getAllAnswer1Id(id: Int): String {
        return selectedQuestionsDao.getallAnswers1Id(id)
    }

    fun getAllAnswer2Id(id: Int): String {
        return selectedQuestionsDao.getallAnswers2Id(id)
    }

    fun getAllAnswer3Id(id: Int): String {
        return selectedQuestionsDao.getallAnswers3Id(id)
    }

    fun getCurrentSelectedQuestions(): SelectedQuestions {
        return selectedQuestionsDao.getCurrentSelectedQuestions(currentQuestionIndex)
    }

    fun getAnsweredSelectedQuestions(): Boolean {
        return selectedQuestionsDao.getAnsweredSelectedQuestions(currentQuestionIndex)
    }

    fun getAllAnsweredSelectedQuestions(): List<Boolean> {
        return selectedQuestionsDao.getAllAnsweredSelectedQuestions()
    }

    fun getDifficultySelectedQuestions(): Int {
        return selectedQuestionsDao.getDifficultySelectedQuestions(currentQuestionIndex)
    }

    fun updateAnsweredSelectedQuestions(selectedQuestions: SelectedQuestions) {
        selectedQuestionsDao.updateSelectedQuestions(selectedQuestions)
    }

    fun getCurrentCorrectAnsweredSelectedQuestions(): Int {
        return selectedQuestionsDao.getCurrentCorrectAnsweredSelectedQuestions(currentQuestionIndex)
    }

    fun getBUsedHintsSelectedQuestions(): Boolean {
        return selectedQuestionsDao.getBUsedHintsSelectedQuestions(currentQuestionIndex)
    }

    //Score
    fun insertScore(score: Score) {
        scoreDao.insertScore(score)
    }

    fun getNScore(): Int {
        return scoreDao.getNScore()
    }

    fun getAllScore(): List<Score> {
        return scoreDao.getAllScore()
    }

    fun getTopFiveScore(): List<Score> {
        return scoreDao.getTopFiveScore()
    }
    //Queries

    //Cadena de texto para mostrar la pregunta y el total
    val currentQuestionCounter: String
        get() = " ${currentListIndex + 1} / ${indexList.size}"

    val actualQuestionIndex: Int
        get() = currentQuestionIndex

    var indexQuestionsList: List<Int>
        get() = indexList
        set(value) {
            indexList = value
            currentQuestionIndex = indexList[currentListIndex]
        }

    //Cambiar de pregunta, aumentando
    fun nextQuestion() {
        //currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
        currentListIndex = (currentListIndex + 1) % indexList.size
        currentQuestionIndex = indexList[currentListIndex]
        updateGameSet(GameSet(98, currentListIndex))
    }

    //Cambiar de pregunta, disminuyendo
    fun prevQuestion() {
        currentListIndex = (currentListIndex - 1) % indexList.size
        if (currentListIndex < 0) currentListIndex = indexList.size - 1
        currentQuestionIndex = indexList[currentListIndex]
        updateGameSet(GameSet(98, currentListIndex))
    }

    fun checkAllAnswered(): Boolean {
        val answeredSelectedQuestions = getAllAnsweredSelectedQuestions()
        if (answeredSelectedQuestions.all { it })
            return true
        return false
    }
}