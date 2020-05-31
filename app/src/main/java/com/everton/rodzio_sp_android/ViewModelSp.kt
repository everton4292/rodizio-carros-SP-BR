package com.everton.rodzio_sp_android

import android.graphics.Color
import android.text.Editable
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class ViewModelSp : ViewModel() {

    fun isValidNumber(str: String): Boolean {
        return try {
            str.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }


    val resultadoDoRodizio = MutableLiveData<Result>()


    fun verificaRod(editTextPlaca: String, dia: String) {
        var final = editTextPlaca[6].toString()
        var resultadoText = Result()


        when {
            (dia == resultadoText.saturday) || (dia == resultadoText.sunday.toString()) -> {
                resultadoText.mensagem = "Não há rodízio nos fins de semana"
            }

            (dia == resultadoText.monday.toString()) && ((final == "1") || (final == "2")) -> {
                resultadoText.mensagem = "Seu carro está no rodízio"


            }

            (dia == resultadoText.tuesday) && ((final == "3") || (final == "4")) -> {
                resultadoText.mensagem = "Seu carro está no rodízio"
            }

            (dia == resultadoText.wednesday) && ((final == "5") || (final == "6")) -> {
                resultadoText.mensagem = "Seu carro está no rodízio"
            }

            (dia == resultadoText.thursday) && ((final == "7") || (final == "8")) -> {
                resultadoText.mensagem = "Seu carro está no rodízio"
            }

            (dia == resultadoText.friday) && ((final == "9") || (final == "0")) -> {
                resultadoText.mensagem = "Seu carro está no rodízio"

            }
            else -> {
                resultadoText.mensagem = "Seu carro não está no rodízio"

            }

        }
        resultadoDoRodizio.value = resultadoText
    }
}