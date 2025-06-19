package com.example.abgusov_lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Вычисление суммы ряда
        val result = calculateSeries(1e-2) // Малый порог для остановки

        // Отображение результатов
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        resultTextView.text = """
            Сумма ряда: ${result.sum}
            Последнее слагаемое: ${result.lastTerm}
            Количество повторений цикла: ${result.iterations}
        """.trimIndent()
    }

    //Функция для вычисления суммы ряда 1 + 1/4 + 1/9 + 1/16 + 1/25 + ...
    private fun calculateSeries(epsilon: Double): SeriesResult {
        var sum = 0.0
        var n = 1
        var term: Double
        var iterations = 0

        do {
            term = 1.0 / (n * n) // Вычисляем текущее слагаемое: 1/n^2
            sum += term // Добавляем к сумме
            n++ // Увеличиваем n для следующего слагаемого
            iterations++ // Считаем итерации
        } while (Math.abs(term) >= epsilon) // Продолжаем, пока слагаемое по модулю >= epsilon

        return SeriesResult(sum, term, iterations)
    }

    //Класс для хранения результатов вычисления ряда
    data class SeriesResult(
        val sum: Double,           // Сумма ряда
        val lastTerm: Double,      // Последнее добавленное слагаемое
        val iterations: Int        // Количество итераций
    )
}