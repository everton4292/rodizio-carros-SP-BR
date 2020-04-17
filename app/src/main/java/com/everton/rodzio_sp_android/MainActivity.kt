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
                var meuFormato = "dd/MM/YY"
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


        button.setOnClickListener {
            var placa22 = editTextPlaca.text.toString()
            var checkIfMercosul = isValidNumber(placa22[4].toString())
            if (checkIfMercosul == false) {
                textViewValidade.text = "Padrão Mercosul"
            } else {
                textViewValidade.text = "Padrão Antigo"
            }


        }
    }
}


