package com.everton.rodzio_sp_android

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.view.View
import android.view.View.OnClickListener
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextPlaca.setFilters(arrayOf<InputFilter>(AllCaps()))
        editTextPlaca.filters += InputFilter.LengthFilter(7)


        val placa = editTextPlaca
        val simpleMaskFormatterPlaca = SimpleMaskFormatter("LLLNANN")
        val maskTextWatcherPlaca = MaskTextWatcher(placa, simpleMaskFormatterPlaca)
        editTextPlaca.addTextChangedListener(maskTextWatcherPlaca)


        val calendar = Calendar.getInstance()
        val dateSetListener = object : OnDateSetListener {

            override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
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
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
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

        fun verificaRod(placa: String, dia: Int) {
            var final = placa[5].toInt()
            var yRodizio = "Seu carro está no rodízio"

            if ((dia == 5) || (dia == 6)) {
                textViewResultado.text = "Não há rodízio nos fins de semana"
            } else if ((dia == 0) && (final == 1) || (final == 2)) {
                textViewResultado.text = yRodizio
            } else if ((dia == 1) && (final == 3) || (final == 4)) {
                textViewResultado.text = yRodizio
            } else if ((dia == 2) && (final == 5) || (final == 6)) {
                textViewResultado.text = yRodizio
            } else if ((dia == 3) && (final == 7) || (final == 8)) {
                textViewResultado.text = yRodizio
            } else if ((dia == 4) && (final == 9) || (final == 0)) {
                textViewResultado.text = yRodizio
            } else {
                textViewResultado.text = "Seu carro não está no rodízio"
            }
        }



        button.setOnClickListener {
            var placa22 = editTextPlaca.text.toString()
            var checkIfMercosul = isValidNumber(placa22[4].toString())
            if (checkIfMercosul == false) {
                textViewValidade.text = "Padrão Mercosul"
            } else {
                textViewValidade.text = "Padrão Antigo"
            }
            var placaFunc = placa.toString()

            /*val c = Calendar.getInstance()
            var format1 = SimpleDateFormat("dd/MM/yyyy")
            var dt1: Date = format1.parse(c.toString())
            c.time = dt1
            var dataFunc = dt1*/




            var input_date = editTextCalendario.text
            var format1 = SimpleDateFormat("dd/MM/yyyy")
            var dt1: Date = format1.parse(input_date)
            var format2 = SimpleDateFormat("EEEE")
            var finalDay = format2.format(dt1)

            verificaRod(placaFunc, finalDay)




        }
    }
}


