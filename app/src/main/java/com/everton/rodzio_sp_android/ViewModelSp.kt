package com.everton.rodzio_sp_android


import android.text.Editable

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ViewModelSp : ViewModel() {

    fun isValidNumber(str: String): Boolean {

        return try {
            str.toDouble()
            true
        } catch (e: Exception) {
            false
        }

    }


    val resultadoDoRodizio = MutableLiveData<Result>()
    val errorPlaca = MutableLiveData<Unit>()
    val errorData = MutableLiveData<Unit>()





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