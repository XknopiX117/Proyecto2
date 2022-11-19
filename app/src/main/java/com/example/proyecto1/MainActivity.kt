package com.example.proyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private val model: QuizViewModel by viewModels()

    private lateinit var btnPlay: Button
    private lateinit var btnOption: Button
    private lateinit var btnScores: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model.getModelContext = this
        model.startModelDatabase()

        btnPlay = findViewById(R.id.btnPlay)
        btnOption = findViewById(R.id.btnOption)
        btnScores = findViewById(R.id.btnScores)

        val alertDialog = AlertDialog.Builder(this)

        if (model.getAllNumberQuestions == 0) {
            getAllQuestions()
            getAllGameSettings()
        }

        btnPlay.setOnClickListener { _ ->

            if (model.getGameSettingsStatusId(4) == 1) {
                if (model.getAllNGameSet() != 0) {
                    model.deleteGameSet()
                    model.deleteSelectedQuestions()
                }

                val intent = Intent(this, GameActivity::class.java)
                val questionsInt = getThemeQuestions().shuffled()
                val randomInts =
                    generateSequence { Random.nextInt(1 until questionsInt.size) }.distinct().take(
                        model.getGameSettingsStatusId(1)
                    ).toSet().toList() as ArrayList<Int>

                for (i in 0 until randomInts.size) {
                    model.insertGameSet(GameSet(i, questionsInt[randomInts[i]]))
                }
                //Actual score
                model.insertGameSet(GameSet(97, 0))
                //Actual index
                model.insertGameSet(GameSet(98, 0))
                //Actual hints
                model.insertGameSet(GameSet(99, 5))
                //Actual hints used
                model.insertGameSet(GameSet(100, 0))
                //Actual hints streaks
                model.insertGameSet(GameSet(101, 0))

                for (i in 0 until randomInts.size) {
                    val questions = model.getQuestionsData(questionsInt[randomInts[i]])
                    model.insertSelectedQuestions(
                        SelectedQuestions(
                            questionsInt[randomInts[i]],
                            questions.correctAnswer,
                            questions.allAnswers0,
                            questions.allAnswers1,
                            questions.allAnswers2,
                            questions.allAnswers3,
                            difficulty = model.getGameSettingsStatusId(2)
                        )
                    )
                }

                startActivity(intent)
            } else {
                val intent = Intent(this, GameActivity::class.java)
                alertDialog.setTitle("Confirmación")
                alertDialog.setMessage("¿Quieres continuar la partida?").setPositiveButton(
                    "Sí"
                ) { _, _ ->
                    startActivity(intent)
                }.setNegativeButton("No") { _, _ ->
                    if (model.getAllNGameSet() != 0) {
                        model.deleteGameSet()
                        model.deleteSelectedQuestions()
                    }

                    val questionsInt = getThemeQuestions().shuffled()
                    val randomInts =
                        generateSequence { Random.nextInt(1 until questionsInt.size) }.distinct()
                            .take(
                                model.getGameSettingsStatusId(1)
                            ).toSet().toList() as ArrayList<Int>

                    for (i in 0 until randomInts.size) {
                        model.insertGameSet(GameSet(i, questionsInt[randomInts[i]]))
                    }
                    //Actual score
                    model.insertGameSet(GameSet(97, 0))
                    //Actual index
                    model.insertGameSet(GameSet(98, 0))
                    //Actual hints
                    model.insertGameSet(GameSet(99, 5))
                    //Actual hints used
                    model.insertGameSet(GameSet(100, 0))
                    //Actual hints streaks
                    model.insertGameSet(GameSet(101, 0))

                    for (i in 0 until randomInts.size) {
                        val questions = model.getQuestionsData(questionsInt[randomInts[i]])
                        model.insertSelectedQuestions(
                            SelectedQuestions(
                                questionsInt[randomInts[i]],
                                questions.correctAnswer,
                                questions.allAnswers0,
                                questions.allAnswers1,
                                questions.allAnswers2,
                                questions.allAnswers3,
                                difficulty = model.getGameSettingsStatusId(2)
                            )
                        )
                    }

                    startActivity(intent)
                }.show()

            }
        }

        btnOption.setOnClickListener { _ ->
            val intent = Intent(this, OptionActivity::class.java)
            startActivity(intent)
        }

        btnScores.setOnClickListener { _ ->
            val intent = Intent(this, ScoreActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAllQuestions() {
        model.insertQuestions(
            Questions(
                0,
                "¿La invasión de qué fortaleza por parte de los revolucionarios es considerada como el punto de inicio de la Revolución Francesa?",
                "Historia",
                "Bastilla",
                "Bastilla",
                "Palacio de Versalles",
                "El Castillo de Bonaguil",
                "La ciudadela de Carcasona"
            )
        )

        model.insertQuestions(
            Questions(
                1,
                "¿En qué año el hombre pisó la Luna por primera vez?",
                "Historia",
                "1969",
                "1979",
                "1969",
                "1959",
                "1971"
            )
        )

        model.insertQuestions(
            Questions(
                2,
                "¿Quién fue el primer presidente de la democracia española tras el franquismo?",
                "Historia",
                "Adolfo Suárez",
                "Adolfo Suárez",
                "Leopoldo Calvo",
                "Felipe González",
                "José María Aznar"
            )
        )

        model.insertQuestions(
            Questions(
                3,
                "¿Quién fue el primer presidente de Estados Unidos?",
                "Historia",
                "George Washington",
                "George Washington",
                "John Adams",
                "Thomas Jefferson",
                "James Madison"
            )
        )

        model.insertQuestions(
            Questions(
                4,
                "¿Cuánto duró la Guerra de los Cien Años?",
                "Historia",
                "116",
                "100",
                "96",
                "116",
                "153"
            )
        )

        model.insertQuestions(
            Questions(
                5,
                "¿Cuál era la ciudad hogar de Marco Polo?",
                "Historia",
                "Venecia",
                "Venecia",
                "Portugal",
                "Milán",
                "España"
            )
        )

        model.insertQuestions(
            Questions(
                6,
                "¿En qué año se disolvió la Unión Soviética?",
                "Historia",
                "1991",
                "1998",
                "1990",
                "1991",
                "1995"
            )
        )

        model.insertQuestions(
            Questions(
                7,
                "¿De qué país se independizó Eslovenia?",
                "Historia",
                "Yugoslavia",
                "Liberia",
                "Yugoslavia",
                "Rusia",
                "Eslovaquia"
            )
        )

        model.insertQuestions(
            Questions(
                8,
                "¿Qué carabela no regresó del primer viaje de Colón al Nuevo Mundo?",
                "Historia",
                "La Santa María",
                "La Niña",
                "La Pinta",
                "La Bonita",
                "La Santa María"
            )
        )

        model.insertQuestions(
            Questions(
                9,
                "¿Qué presidente de la Unión Soviética instauró la Perestroika?",
                "Historia",
                "Gorbachov",
                "Nikita",
                "Lenin",
                "Stalin",
                "Gorbachov"
            )
        )

        model.insertQuestions(
            Questions(
                10,
                "¿Cuál es el río más caudaloso del mundo?",
                "Geografía",
                "Amazonas",
                "Nilo",
                "Missisipi",
                "Amazonas",
                "Bravo"
            )
        )

        model.insertQuestions(
            Questions(
                11,
                "¿Cuál es el monte más alto del mundo?",
                "Geografía",
                "Everest",
                "Pico de Orizaba",
                "Everest",
                "Kilawea",
                "Olimpo"
            )
        )

        model.insertQuestions(
            Questions(
                12,
                "¿Cuál es la lengua más hablada del mundo?",
                "Geografía",
                "Chino",
                "Ingles",
                "Español",
                "Chino",
                "Hindi"
            )
        )

        model.insertQuestions(
            Questions(
                13,
                "¿Cuál es la capital de Brasil?",
                "Geografía",
                "Brasilia",
                "Río de Janeiro",
                "Sao Paolo",
                "Brasilia",
                "Caracas"
            )
        )

        model.insertQuestions(
            Questions(
                14,
                "¿Cuál es el país de mayor tamaño del mundo?",
                "Geografía",
                "Rusia",
                "Canada",
                "Brasil",
                "Estados Unidos",
                "Rusia"
            )
        )

        model.insertQuestions(
            Questions(
                15,
                "¿Cuál es el país de mayor tamaño del mundo?",
                "Geografía",
                "Rusia",
                "Canada",
                "Brasil",
                "Estados Unidos",
                "Rusia"
            )
        )

        model.insertQuestions(
            Questions(
                16,
                "¿Qué país está entre Perú y Colombia?",
                "Geografía",
                "Ecuador",
                "Perú",
                "Bolivia",
                "Panamá",
                "Ecuador"
            )
        )

        model.insertQuestions(
            Questions(
                17,
                "¿En qué país se encuentra el río Po?",
                "Geografía",
                "Italia",
                "Alemania",
                "Francia",
                "España",
                "Italia"
            )
        )

        model.insertQuestions(
            Questions(
                18,
                "¿A qué país pertenece la isla de Creta?",
                "Geografía",
                "Grecia",
                "Italia",
                "Francia",
                "Grecia",
                "Alemania"
            )
        )

        model.insertQuestions(
            Questions(
                19,
                "¿Cuál es el país más visitado del mundo?",
                "Geografía",
                "Francia",
                "México",
                "Francia",
                "Grecia",
                "Alemania"
            )
        )

        model.insertQuestions(
            Questions(
                20,
                "¿Quién fue el famoso cantante del grupo musical Queen?",
                "Entretenimiento",
                "Freddy Mercury",
                "Freddy Mercury",
                "Bob Dylan",
                "John Lenon",
                "Bono"
            )
        )

        model.insertQuestions(
            Questions(
                21,
                "¿Cómo se llama la madre de Simba en la película de Disney “El Rey León”?",
                "Entretenimiento",
                "Sarabi",
                "Sarabi",
                "Kimba",
                "Mufasa",
                "Kaya"
            )
        )

        model.insertQuestions(
            Questions(
                22,
                "¿A qué banda de música metal pertenece el disco Master of Puppets?",
                "Entretenimiento",
                "Metallica",
                "Queen",
                "Kiss",
                "Metallica",
                "AC/DC"
            )
        )

        model.insertQuestions(
            Questions(
                23,
                "¿Cómo se llama la protagonista de la saga de videojuegos \"The Legend of Zelda\"?",
                "Entretenimiento",
                "Link",
                "Zelda",
                "Ganon",
                "Link",
                "Furbo"
            )
        )

        model.insertQuestions(
            Questions(
                24,
                "¿En qué país transcurre la acción de la película \"Chappie\"?",
                "Entretenimiento",
                "Sudafrica",
                "Francia",
                "Mexico",
                "Estados Unidos",
                "Reino Unido"
            )
        )

        model.insertQuestions(
            Questions(
                25,
                "¿En qué ciudad vive el mago de Oz?",
                "Entretenimiento",
                "Esmeralda",
                "Esmeralda",
                "Oz",
                "Kansas",
                "Ciudad mágica"
            )
        )

        model.insertQuestions(
            Questions(
                26,
                "¿En qué año se emitió el último episodio de la serie \"The Office\"?",
                "Entretenimiento",
                "2013",
                "2015",
                "2016",
                "2013",
                "2010"
            )
        )

        model.insertQuestions(
            Questions(
                27,
                "¿En qué calle ficticia vivía Sherlock Holmes?",
                "Entretenimiento",
                "Baker Street",
                "Baker Street",
                "Savile Row",
                "Carnaby Street",
                "King's Road"
            )
        )

        model.insertQuestions(
            Questions(
                28,
                "¿Cómo se llama el perro de Tintín?",
                "Entretenimiento",
                "Milú",
                "Clifford",
                "Rocko",
                "Milú",
                "Lazy"
            )
        )

        model.insertQuestions(
            Questions(
                29,
                "¿Cuál fue la primera película de Disney?",
                "Entretenimiento",
                "Blancanieves",
                "La Cenicienta",
                "Blancanieves",
                "La sirenita",
                "Mickey Mouse"
            )
        )

        model.insertQuestions(
            Questions(
                30,
                "¿Quién escribió la Ilíada y la Odisea?",
                "Arte y Literatura",
                "Homero",
                "Odiseo",
                "Homero",
                "Antipas",
                "Herodoto"
            )
        )

        model.insertQuestions(
            Questions(
                31,
                "¿Quién pintó el “Guernica”?",
                "Arte y Literatura",
                "Pablo Picasso",
                "Miguel Ángel",
                "Andy Warhol",
                "Pablo Picasso",
                "Salvador Dalí"
            )
        )

        model.insertQuestions(
            Questions(
                32,
                "¿Qué nombre tenía el caballo de Don Quijote de la Mancha?",
                "Arte y Literatura",
                "Rocinante",
                "Blanco",
                "Rocinante",
                "Salvador",
                "Aventurero"
            )
        )

        model.insertQuestions(
            Questions(
                33,
                "¿De qué país es originario el tipo de poesía conocido como haiku?",
                "Arte y Literatura",
                "Japón",
                "China",
                "Corea",
                "Japón",
                "India"
            )
        )

        model.insertQuestions(
            Questions(
                34,
                " ¿Qué personaje del universo literario de Harry Potter tiene una rata llamada Scabbers?",
                "Arte y Literatura",
                "Ron",
                "Harry",
                "Ron",
                "Hermione",
                "Draco"
            )
        )

        model.insertQuestions(
            Questions(
                35,
                " ¿Quién escribió “La Guerra de los Mundos”?",
                "Arte y Literatura",
                "H. G. Wells",
                "H. G. Wells",
                "George Orwell",
                "Aldous Huxley",
                "Ray Bradbury"
            )
        )

        model.insertQuestions(
            Questions(
                36,
                " ¿Con qué nombre firmaba Van Gogh sus obras?",
                "Arte y Literatura",
                "vincent",
                "Van Gogh",
                "V. Van",
                "V.",
                "V. V. G."
            )
        )

        model.insertQuestions(
            Questions(
                37,
                "  ¿Qué tipo de instrumento es una cítara?",
                "Arte y Literatura",
                "Cuerda",
                "Viento",
                "Cuerda",
                "Percusión",
                "Baquetas"
            )
        )

        model.insertQuestions(
            Questions(
                38,
                "  ¿Qué pintor noruego pintó “El grito”?",
                "Arte y Literatura",
                "Edvard Munch",
                "Edvard Munch",
                "Vincent Van Gogh",
                "Claude Monet",
                "Vasili Kandinski"
            )
        )

        model.insertQuestions(
            Questions(
                39,
                " ¿Quién escribió “Sueño de una noche de verano”?",
                "Arte y Literatura",
                "Shakespare",
                "Shakespare",
                "Cervantes",
                "Dickens",
                "Maquiavelo"
            )
        )

        model.insertQuestions(
            Questions(
                40,
                "¿Cuál es el ave de mayor envergadura que sigue viva actualmente?",
                "Ciencias",
                "Albatros",
                "Aguila",
                "Pelicano",
                "Buitre",
                "Albatros"
            )
        )

        model.insertQuestions(
            Questions(
                41,
                "¿Cómo se llama la planta a partir de la cual suele ser elaborado el tequila?",
                "Ciencias",
                "Agave",
                "Agave",
                "Maiz",
                "Pulque",
                "Cebada"
            )
        )

        model.insertQuestions(
            Questions(
                42,
                "¿Cómo se llama el tipo de célula nerviosa más abundante en el cerebro humano?",
                "Ciencias",
                "Glía",
                "Glía",
                "Neuronas",
                "Fibroblastos",
                "Plaquetas"
            )
        )

        model.insertQuestions(
            Questions(
                43,
                "¿Qué nombre recibe el sistema de transcripción fonética usado en el chino mandarín?",
                "Ciencias",
                "Pinyin",
                "Pinyin",
                "Hiragana",
                "Katakana",
                "Ideograma"
            )
        )

        model.insertQuestions(
            Questions(
                44,
                "¿Cuál es el nombre técnico del miedo o fobia a las alturas?",
                "Ciencias",
                "Acrofobia",
                "Alturafobia",
                "Acrofobia",
                "Hirofobia",
                "Valofobia"
            )
        )

        model.insertQuestions(
            Questions(
                45,
                " ¿En qué mes el Sol está más cerca de la Tierra?",
                "Ciencias",
                "Diciembre",
                "Enero",
                "Julio",
                "Agosto",
                "Diciembre"
            )
        )

        model.insertQuestions(
            Questions(
                46,
                " ¿Cuántos elementos tiene la tabla periódica?",
                "Ciencias",
                "118",
                "115",
                "119",
                "118",
                "100"
            )
        )

        model.insertQuestions(
            Questions(
                47,
                " ¿Qué número viene después del 14 en los decimales del Pi?",
                "Ciencias",
                "1",
                "5",
                "6",
                "9",
                "3"
            )
        )

        model.insertQuestions(
            Questions(
                48,
                " ¿Qué elemento está presente en absolutamente todas las moléculas orgánicas?",
                "Ciencias",
                "carbono",
                "Oxígeno",
                "Hidrógeno",
                "Carbono",
                "Flúor"
            )
        )

        model.insertQuestions(
            Questions(
                49,
                " ¿A partir de qué planta se elabora el tequila?",
                "Ciencias",
                "Agave",
                "Sávila",
                "Magüey",
                "Agave",
                "Penca"
            )
        )
    }

    private fun getAllGameSettings() {
        model.insertGameSettings(GameSettings(0, "Temas", 1))

        model.insertGameSettings(GameSettings(1, "No preguntas", 5))

        model.insertGameSettings(GameSettings(2, "Dificultad", 0))

        model.insertGameSettings(GameSettings(3, "Pistas", 0))

        model.insertGameSettings(GameSettings(4, "Finalizado", 1))

    }

    private fun getThemeQuestions(): List<Int> {
        when (model.getGameSettingsStatusId(0)) {
            1 -> return model.getQuestionsThemeSelected("Historia")

            5 -> return model.getQuestionsAllThemeSelected()

            10 -> return model.getQuestionsThemeSelected("Arte y Literatura")

            11 -> return model.getQuestionsThemeSelected2("Historia", "Arte y Literatura")

            100 -> return model.getQuestionsThemeSelected("Geografía")

            101 -> return model.getQuestionsThemeSelected2("Historia", "Geografía")

            110 -> return model.getQuestionsThemeSelected2("Arte y Literatura", "Geografía")

            111 -> return model.getQuestionsThemeSelected3(
                "Historia",
                "Arte y Literatura",
                "Geografía"
            )

            1000 -> return model.getQuestionsThemeSelected("Entretenimiento")

            1001 -> return model.getQuestionsThemeSelected2("Historia", "Entretenimiento")

            1010 -> return model.getQuestionsThemeSelected2("Arte y Literatura", "Entretenimiento")

            1011 -> return model.getQuestionsThemeSelected3(
                "Historia",
                "Arte y Literatura",
                "Entretenimiento"
            )

            1100 -> return model.getQuestionsThemeSelected2("Geografía", "Entretenimiento")

            1101 -> return model.getQuestionsThemeSelected3(
                "Historia",
                "Geografía",
                "Entretenimiento"
            )

            1110 -> return model.getQuestionsThemeSelected3(
                "Arte y Literatura",
                "Geografía",
                "Entretenimiento"
            )

            1111 -> return model.getQuestionsThemeSelected4(
                "Historia",
                "Arte y Literatura",
                "Geografía",
                "Entretenimiento"
            )

            10000 -> return model.getQuestionsThemeSelected("Ciencias")

            10001 -> return model.getQuestionsThemeSelected2("Historia", "Ciencias")

            10010 -> return model.getQuestionsThemeSelected2("Arte y Literatura", "Ciencias")

            10011 -> return model.getQuestionsThemeSelected3(
                "Historia",
                "Arte y Literatura",
                "Ciencias"
            )

            10100 -> return model.getQuestionsThemeSelected2("Geografía", "Ciencias")

            10101 -> return model.getQuestionsThemeSelected3("Historia", "Geografía", "Ciencias")

            10110 -> return model.getQuestionsThemeSelected3(
                "Arte y Literatura",
                "Geografía",
                "Ciencias"
            )

            10111 -> return model.getQuestionsThemeSelected4(
                "Historia",
                "Arte y Literatura",
                "Geografía",
                "Ciencias"
            )

            11000 -> return model.getQuestionsThemeSelected2("Entretenimiento", "Ciencias")

            11001 -> return model.getQuestionsThemeSelected3(
                "Historia",
                "Entretenimiento",
                "Ciencias"
            )

            11010 -> return model.getQuestionsThemeSelected3(
                "Arte y Literatura",
                "Entretenimiento",
                "Ciencias"
            )

            11011 -> return model.getQuestionsThemeSelected4(
                "Historia",
                "Arte y Literatura",
                "Entretenimiento",
                "Ciencias"
            )

            11100 -> return model.getQuestionsThemeSelected3(
                "Geografía",
                "Entretenimiento",
                "Ciencias"
            )

            11101 -> return model.getQuestionsThemeSelected4(
                "Historia",
                "Geografía",
                "Entretenimiento",
                "Ciencias"
            )

            11110 -> return model.getQuestionsThemeSelected4(
                "Arte y Literatura",
                "Geografía",
                "Entretenimiento",
                "Ciencias"
            )

            11111 -> return model.getQuestionsAllThemeSelected()

        }

        return model.getQuestionsAllThemeSelected()
    }

}
