package com.example.lab2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lab2.R

class NaturalGasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_natural_gas, container, false)

        val editTextCoalInput: EditText = view.findViewById(R.id.gasTextEdit)
        val resultCoalValue: TextView = view.findViewById(R.id.resultGasValue)
        val buttonCoalCalculate: Button = view.findViewById(R.id.resultGasButton)

        buttonCoalCalculate.setOnClickListener {
            val input = editTextCoalInput.text.toString().trim().toDouble()

            // Обробка введених даних для вугілля
            val result = calculateGasEmissions(input)

            resultCoalValue.text = "$result"
        }

        return view
    }

    private fun calculateGasEmissions(input: Double): Double{
        // Специфічний показник емісії твердих частинок для газу
        val specificEmission: Double

        val Q_r_i:Double = 33.08 // нижча робоча теплота згоряння палива
        val A_r: Double = 0.0 // масовий вміст золи в паливі на робочу масу
        val a_vyn:Double = 0.0 // частка золи, яка виходить з котла у вигляді леткої золи
        val n_zy: Double = 0.985 // ефективність очищення димових газів від твердих частинок
        val G_vyn: Double = 0.0 // масовий вміст горючих речовин у викидах твердих частинок, %
        val k_tvS: Double // показник емісії твердих продуктів взаємодії сорбенту та оксидів сірки і твердих частинок сорбенту, г/ГДж


        specificEmission = Math.pow(10.0, 6.0) / Q_r_i * a_vyn * A_r / (100 - G_vyn) * (1 - n_zy)

        // Валовий викид при спалюванні газу
        val totalEmission: Double

        totalEmission = Math.pow(10.0, -6.0) * Q_r_i * input * specificEmission

        return totalEmission
    }
}
