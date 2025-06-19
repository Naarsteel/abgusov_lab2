package com.example.abgusov_lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация UI элементов
        val epsilonInput = findViewById<EditText>(R.id.epsilonInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        // Установка начального значения в EditText
        epsilonInput.setText("0.01")

        // Обработчик нажатия кнопки
        calculateButton.setOnClickListener {
            try {
                val epsilon = epsilonInput.text.toString().toDouble()

                // Проверка на корректность epsilon
                if (epsilon <= 0) {
                    Toast.makeText(this, "МЗ должно быть положительным", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Вычисление суммы ряда
                val result = calculateSeries(epsilon)

                // Отображение результатов
                resultTextView.text = """
                    Сумма ряда: ${"%.6f".format(result.sum)}
                    Последнее слагаемое: ${result.lastTerm}
                    Количество повторений цикла: ${result.iterations}
                    Минимальное значение: $epsilon
                """.trimIndent()

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Введите корректное число", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Функция для вычисления суммы ряда 1 + 1/4 + 1/9 + 1/16 + 1/25 + ...
    private fun calculateSeries(epsilon: Double): SeriesResult {
        var sum = 0.0
        var n = 1
        var term: Double
        var iterations = 0

        do {
            term = 1.0 / (n * n)
            sum += term
            n++
            iterations++
        } while (term >= epsilon)

        return SeriesResult(sum, term, iterations)
    }

    //Класс для хранения результатов вычисления ряда
    data class SeriesResult(
        val sum: Double,
        val lastTerm: Double,
        val iterations: Int
    )
}