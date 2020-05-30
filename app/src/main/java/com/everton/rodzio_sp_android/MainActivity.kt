package com.everton.rodzio_sp_android

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.view.View
import android.view.View.OnClickListener
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*


class MainActivity : AppCompatActivity() {
    lateinit var model: ViewModelSp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(supportaction)

        editTextPlaca.setFilters(arrayOf<InputFilter>(AllCaps()))
        editTextPlaca.filters += InputFilter.LengthFilter(7)

        this.model = ViewModelProvider(this).get(ViewModelSp::class.java)
        val editTextPlacaFunc = editTextPlaca.text.toString()
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


        button.setOnClickListener {
            var placa22 = editTextPlaca.text.toString()
            if (placa22.isNullOrEmpty()) {
                Toast.makeText(this, "Placa inválida", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            var checkIfMercosul = model.isValidNumber(placa22[4].toString())
            if (!checkIfMercosul) {
                textViewValidade.text = "Padrão Mercosul"
            } else {
                textViewValidade.text = "Padrão Antigo"
            }


            val inputDate = editTextCalendario.text
            val format1 = SimpleDateFormat("dd/MM/yyyy")
            if (inputDate.isNullOrEmpty()){
                Toast.makeText(this, "Data inválida", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            val dt1 = format1.parse(inputDate.toString())
            val format2 = SimpleDateFormat("EEEE")
            val finalDay: String = format2.format(dt1)


            var placaFunc = editTextPlaca.text.toString()
            try {
                model.verificaRod(dia = finalDay, textViewResultado = textViewResultado, editTextPlaca = placaFunc)
            } catch (err: NumberFormatException) {
                textViewResultado.text = "erro"
            }


        }
    }
}


