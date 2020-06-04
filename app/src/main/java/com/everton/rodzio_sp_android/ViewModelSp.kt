package com.everton.rodzio_sp_android


import android.text.Editable

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel class, following Android jetpack orientations
 *
 */
class ViewModelSp : ViewModel() {

    /**
     * Function for verifying if the "4" array character from the license plate is a number or a
     * letter. This input is alphanumerical, and indicates if the license plate is and old model(number),
     * or a new model(letter).
     */

    fun isValidNumber(str: String): Boolean {

        return try {
            str.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }

    }


    val resultadoDoRodizio = MutableLiveData<Result>()


/**
* Main function for veryfing if the user's car is affirmative for the rotation system. It uses the
* license plate and day input, the function verifies the "6" array digit from the license place input
* and matches it with the day of the week.
*
*/
    fun verificaRod(editTextPlaca: String, dia: String) {
        var final = editTextPlaca[6].toString()
        var resultadoText = Result()


        when {
            (dia == resultadoText.saturday) || (dia == resultadoText.sunday) -> {
                resultadoText.mensagem = "Não há rodízio nos fins de semana"
            }

            (dia == resultadoText.monday) && ((final == "1") || (final == "2")) -> {
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