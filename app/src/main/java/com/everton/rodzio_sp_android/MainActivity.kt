package com.everton.rodzio_sp_android

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorMatrix
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.view.View
import android.view.View.OnClickListener
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*
import java.util.Calendar.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(supportaction)

        editTextPlaca.setFilters(arrayOf<InputFilter>(AllCaps()))
        editTextPlaca.filters += InputFilter.LengthFilter(7)


        val placa = editTextPlaca
        val simpleMaskFormatterPlaca = SimpleMaskFormatter("LLLNANN")
        val maskTextWatcherPlaca = MaskTextWatcher(placa, simpleMaskFormatterPlaca)
        editTextPlaca.addTextChangedListener(maskTextWatcherPlaca)


        val calendar = getInstance()
        val dateSetListener = object : OnDateSetListener {

            override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
                calendar[YEAR] = year
                calendar[MONTH] = month
                calendar[DAY_OF_MONTH] = dayOfMonth
                updatelabel()
            }

            fun updatelabel() {
                var meuFormato = "dd/MM/yyyy"
                var sdf = SimpleDateFormat(meuFormato, Locale.FRANCE)
                editTextCalendario.setText(sdf.format(calendar.time))

            }
        }
        editTextCalendario.setOnClickListener(object : OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@MainActivity, dateSetListener,
                    calendar.get(YEAR),
                    calendar.get(MONTH),
                    calendar.get(DAY_OF_MONTH)
                ).show()

            }

        })

        fun isValidNumber(str: String): Boolean {
            return try {
                str.toDouble()
                true
            } catch (e: NumberFormatException) {
                false
            }
        }

        fun verificaRod(editTextPlaca: String, dia: String) {
            var final = editTextPlaca[6].toString()
            var sunday = "Sunday"
            var monday = "Monday"
            var tuesday = "Tuesday"
            var wednesday = "Wednesday"
            var thursday = "Thursday"
            var friday = "Friday"
            var saturday = "Saturday"
            var yRodizio = "Seu carro está no rodízio"
            var nRodizio = "Seu carro não está no rodízio"


            if ((dia == saturday) || (dia == sunday)) {
                textViewResultado.text = "Não há rodízio nos fins de semana"
            } else if ((dia == monday) && ((final == "1") || (final == "2"))) {
                textViewResultado.text = yRodizio
                textViewResultado.setTextColor(Color.RED)
            } else if ((dia == tuesday) && ((final == "3") || (final == "4"))) {
                textViewResultado.text = yRodizio
                textViewResultado.setTextColor(Color.RED)
            } else if ((dia == wednesday) && ((final == "5") || (final == "6"))) {
                textViewResultado.text = yRodizio
                textViewResultado.setTextColor(Color.RED)
            } else if ((dia == thursday) && ((final == "7") || (final == "8"))) {
                textViewResultado.text = yRodizio
                textViewResultado.setTextColor(Color.RED)
            } else if ((dia == friday) && ((final == "9") || (final == "0"))) {
                textViewResultado.text = yRodizio
                textViewResultado.setTextColor(Color.RED)
            } else {
                textViewResultado.text = nRodizio
                textViewResultado.setTextColor(Color.BLUE)
            }
        }

        button.setOnClickListener {
            var placa22 = editTextPlaca.text.toString()
            if (placa22.isNullOrEmpty()) {
                Toast.makeText(this, "Placa inválida", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            var checkIfMercosul = isValidNumber(placa22[4].toString())
            if (checkIfMercosul == false) {
                textViewValidade.text = "Padrão Mercosul"
            } else {
                textViewValidade.text = "Padrão Antigo"
            }


            val input_date = editTextCalendario.text
            val format1 = SimpleDateFormat("dd/MM/yyyy")
            if (input_date.isNullOrEmpty()){
                Toast.makeText(this, "Data inválida", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            val dt1 = format1.parse(input_date.toString())
            val format2 = SimpleDateFormat("EEEE")
            val finalDay: String = format2.format(dt1)


            var placaFunc = editTextPlaca.text.toString()
            try {
                verificaRod(placaFunc, finalDay)
            } catch (err: NumberFormatException) {
                textViewResultado.text = "erro"
            }


        }
    }
}


