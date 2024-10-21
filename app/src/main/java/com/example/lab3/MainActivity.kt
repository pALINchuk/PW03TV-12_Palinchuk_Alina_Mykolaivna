package com.example.lab3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val averagePowerInput = findViewById<EditText>(R.id.averagePowerInput)
        val stdDeviationInput = findViewById<EditText>(R.id.stdDeviationInput)
        val сostInput = findViewById<EditText>(R.id.сostInput)
        val result = findViewById<TextView>(R.id.resultTextView)


        // Обробка натискання кнопки "Розрахувати"
        calculateButton.setOnClickListener {
            val averagePower = averagePowerInput.text.toString().toDoubleOrNull()
            val stdDeviation = stdDeviationInput.text.toString().toDoubleOrNull()
            val cost = сostInput.text.toString().toDouble()

            if (averagePower != null && stdDeviation != null) {
                val energyShare = calculateEnergyShare(averagePower, stdDeviation)
                val energyWithoutImbalance = averagePower * 24 * energyShare // Загальна кількість МВт·год електроенергії, яка генерується без небалансів
                val profit = energyWithoutImbalance * cost * 1000 // Вартість 1 кВт·год = 7 грн, та переводимо в кВт енергію

                val energyWithImbalance = averagePower * 24 * (1-energyShare)
                val penalty = energyWithImbalance * cost * 1000

                val totalProfit = profit - penalty

                // Перевірка на прибуток чи збиток
                val resultText = if (totalProfit >= 0) {
                    "Прибуток від енергії без небалансів: %.2f грн\nШтраф: %.2f грн\nФінальний результат: %.2f грн (прибуток)"
                } else {
                    "Прибуток від енергії без небалансів: %.2f грн\nШтраф: %.2f грн\nФінальний результат: %.2f грн (збиток)"
                }

                result.text = resultText.format(profit, penalty, Math.abs(totalProfit))

//                result.text = ("Прибуток від енергії без небалансів: %.2f грн\n" +
//                        "штраф - %.2f\n" +
//                        "фінальний результат прибутку - %.2f")
//                    .format(profit, penalty, totalProfit)

            } else {
                result.text = "Будь ласка, введіть коректні значення."
            }
        }
    }

    // Функція для обчислення частки енергії
    private fun calculateEnergyShare(Pc: Double, q1: Double, percentage: Double = 0.05): Double {
        val delta:Double = Pc * percentage
        val lowerBound:Double = Pc - delta
        val upperBound:Double = Pc + delta

        val step:Double = 0.001 // Крок інтегрування
        var integral:Double = 0.0
        var p:Double = lowerBound

        while (p <= upperBound) {
            val pd = (1 / (q1 * sqrt(2 * PI))) * exp(-1 * (p - Pc) * (p - Pc) / (2 * q1 * q1))
            integral += pd * step
            p += step
        }
        return integral
    }

}