package com.everton.rodzio_sp_android

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color.*
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.view.View
import android.view.View.OnClickListener
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*


class MainActivity : AppCompatActivity() {
    private val viewModel: ViewModelSp by lazy {
        ViewModelProvider(this).get(ViewModelSp::class.java)
    }
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


        button.setOnClickListener {
            var placaDigitada = editTextPlaca.text.toString()
            if (placaDigitada.isNullOrEmpty()) {
                Toast.makeText(this, "Placa inválida", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            var checkIfMercosul = viewModel.isValidNumber(placaDigitada[4].toString())
            if (!checkIfMercosul) {
                textViewValidade.text = "Padrão Mercosul"
            } else {
                textViewValidade.text = "Padrão Antigo"
            }


            val inputDate = editTextCalendario.text
            val format1 = SimpleDateFormat("dd/MM/yyyy")
            if (inputDate.isNullOrEmpty()) {
                Toast.makeText(this, "Data inválida", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            val dt1 = format1.parse(inputDate.toString())
            val formatFinalDay = SimpleDateFormat("EEEE")
            val finalDay: String = formatFinalDay.format(dt1)


            var placaFunc = editTextPlaca.text.toString()
            try {
                viewModel.verificaRod(dia = finalDay, editTextPlaca = placaFunc)
            } catch (err: NumberFormatException) {
                textViewResultado.text = "erro"
            }

        }
        viewModel.resultadoDoRodizio.observe(this, androidx.lifecycle.Observer {
            result1 -> textViewResultado.text = result1.mensagem
            if(result1.mensagem == "Seu carro está no rodízio") {
                textViewResultado.setTextColor(RED)
            } else if (result1.mensagem == "Seu carro não está no rodízio") {
                textViewResultado.setTextColor(BLUE)
            } else {
                textViewResultado.setTextColor(BLACK)
            }
        })
    }
}


