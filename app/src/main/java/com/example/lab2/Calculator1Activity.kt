package com.example.lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Calculator1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_1_activity)





        // показник емісії твердих частинок для мазуту



        // валовий викид при спалюванні мазуту



        // показник емісії твердих частинок для газу



        // валовий викид при спалюванні газу






        val input1: EditText = findViewById(R.id.number_1_value)
        val input2: EditText = findViewById(R.id.number_2_value)
        val input3: EditText = findViewById(R.id.number_3_value)
        val input4: EditText = findViewById(R.id.number_4_value)
        val input5: EditText = findViewById(R.id.number_5_value)
        val input6: EditText = findViewById(R.id.number_6_value)
        val input7: EditText = findViewById(R.id.number_7_value)

        val result_field: TextView = findViewById(R.id.result_value)

        val backButton: Button = findViewById(R.id.back_button_1)
        val sumButton: Button = findViewById(R.id.result_button)

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        sumButton.setOnClickListener {
            val result = String()
            val h_p = input1.text.toString().toFloat()
            val c_p = input2.text.toString().toFloat()
            val s_p = input3.text.toString().toFloat()
            val n_p = input4.text.toString().toFloat()
            val o_p = input5.text.toString().toFloat()
            val w_p = input6.text.toString().toFloat()
            val a_p = input7.text.toString().toFloat()

            val k_pc = 100/(100-w_p)
            val k_pg = 100/(100-w_p-a_p)

            val h_c = h_p*k_pc
            val c_c = c_p*k_pc
            val s_c = s_p*k_pc
            val n_c = n_p*k_pc
            val o_c = o_p*k_pc
            val a_c = a_p*k_pc

            val check1 = h_c+c_c+s_c+n_c+o_c+a_c
            if (Math.abs(check1 - 100) > 0.01) {
                println("h_c " + h_c)
                println("c_c " + c_c)
                println("s_c " + s_c)
                println("n_c " + n_c)
                println("o_c " + o_c)
                println("a_c " + a_c)
                println("=$check1")

                println("error 1")
                result_field.text = "Please enter valid numbers."
                return@setOnClickListener
            }

            val h_g = h_p*k_pg
            val c_g = c_p*k_pg
            val s_g = s_p*k_pg
            val n_g = n_p*k_pg
            val o_g = o_p*k_pg

            val check2 = h_g+c_g+s_g+n_g+o_g
            if (Math.abs(check2 - 100) > 0.01) {
                println("hg " + h_g)
                println("cg " + c_g)
                println("sg " + s_g)
                println("ng " + n_g)
                println("og " + o_g)
                println("=$check2")

                println("error 2")
                result_field.text = "Please enter valid numbers."
                return@setOnClickListener
            }

            val q_p_h = (339*c_p + 1030*h_p - 108.8*(o_p-s_p) - 25*w_p )/ 1000
            val q_c_h = (q_p_h + 0.025*w_p)*100/(100-w_p)
            val q_g_h = (q_p_h + 0.025*w_p)*100/(100-w_p-a_p)


            result_field.text = "1. $k_pc,\n" +
                    "2. $k_pg,\n" +
                    "3. Склад сухої маси: hc - $h_c, cc - $c_c, sc - $s_c, nc - $n_c, oc - $o_c, ac - $a_c,\n" +
                    "4. Склад горючої маси: hg - $h_g, cg - $c_g, sg - $s_g, ng - $n_g, og - $o_g,\n" +
                    "5. Нижча теплота згорання для робочої маси - $q_p_h,\n" +
                    "6. Нижча теплота згорання для сухої маси - $q_c_h,\n" +
                    "7. Нижча теплота згорання для горючої маси - $q_g_h\n"
        }

    }
}