package com.everton.rodzio_sp_android

import android.graphics.Color
import android.text.Editable
import android.widget.TextView
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class ViewModelSp: ViewModel() {

    fun isValidNumber(str: String): Boolean {
        return try {
            str.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }


    fun verificaRod(editTextPlaca: String, dia: String, textViewResultado: TextView) {
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

}