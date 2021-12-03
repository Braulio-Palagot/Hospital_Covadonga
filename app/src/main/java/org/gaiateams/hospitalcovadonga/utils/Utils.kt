package org.gaiateams.hospitalcovadonga.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import org.gaiateams.hospitalcovadonga.R
import java.text.SimpleDateFormat
import java.util.*

lateinit var auth: FirebaseAuth
lateinit var preferences: SharedPreferences

var cal: Calendar = GregorianCalendar()
var date = cal.time
var df = SimpleDateFormat("dd/MMMM/yyyy")
var hf = SimpleDateFormat("HH:mm")
var ACTUAL_DATE = setDate(df.format(date).toString().lowercase())
var ACTUAL_HOUR = hf.format(date)

val areas = mutableListOf<String>("Pediatría", "Urología", "Ginecología")

const val PREFS_NAME = "org.gaiateams.hospitalcovadonga.sharedpreferences"
const val IS_LOGGED = "IS_LOGGED"
const val SAVED_USER = "SAVED_USER"

fun buildResumeDialog(
    context: Context, title: Int, hour: String, day: String, doctorId: Int,
    doctorName: String, doctorArea: String, userName: String
): MaterialAlertDialogBuilder {
    return MaterialAlertDialogBuilder(context)
        .setTitle(title)
        .setMessage(context.resources.getString(R.string.resume_message, day, hour, doctorId.toString(), doctorName, userName, doctorArea))
}

fun buildDialog(context: Context, title: Int, message: Int): MaterialAlertDialogBuilder {
    return MaterialAlertDialogBuilder(context)
        .setTitle(title)
        .setMessage(message)
}

private fun setDate(selectedDate: String): String {
    var newDate: String = selectedDate

    var month = newDate.substring(3, newDate.indexOf('/', 3))
    var monthNumber = when (month) {
        "enero" -> 1
        "febrero" -> 2
        "marzo" -> 3
        "abril" -> 4
        "mayo" -> 5
        "junio" -> 6
        "julio" -> 7
        "agosto" -> 8
        "septiembre" -> 9
        "octubre" -> 10
        "noviembre" -> 11
        "diciembre" -> 12
        else -> 0
    }
    var day = newDate.substring(0, 2)
    when (day.toInt()) {
        in 1..9 -> {
            day = "25"
            monthNumber--
        }
        in 10..24 -> {
            day = "10"
        }
        in 25..31 -> {
            day = "25"
        }
    }
    when (monthNumber) {
        1 -> month = "Enero"
        2 -> month = "Febrero"
        3 -> month = "Marzo"
        4 -> month = "Abril"
        5 -> month = "Mayo"
        6 -> month = "Junio"
        7 -> month = "Julio"
        8 -> month = "Agosto"
        9 -> month = "Septiembre"
        10 -> month = "Octubre"
        11 -> month = "Noviembre"
        12 -> month = "Diciembre"
    }
    val year = newDate.substring(newDate.indexOf('/', 3) + 1)
    newDate = "$day/$month/$year"

    return newDate
}

fun setHora(hora: String, minuto: String): String {
    var horaReal = ""
    var nuevaHora = hora
    var nuevoMinuto = minuto
    if (hora.toCharArray().size<2)
        nuevaHora = "0$hora"
    if (minuto.toCharArray().size<2)
        nuevoMinuto = "0$minuto"

    horaReal = "$nuevaHora:$nuevoMinuto hrs."

    return horaReal
}