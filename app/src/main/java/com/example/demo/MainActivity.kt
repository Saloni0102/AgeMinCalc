package com.example.demo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    var txt : TextView? = null
    var txt1 : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.b1)
        txt = findViewById(R.id.tv4)
        txt1 = findViewById(R.id.tv6)
        btn.setOnClickListener {
            setDateDialogue()
        }

        }

    fun setDateDialogue(){

        val myCalendar=Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd =DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{
            view, year, month, dayOfMonth ->

            val selectedDate = "$dayOfMonth/${month+1}/$year"
            txt?.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val selDate = sdf.parse(selectedDate)

                selDate?.let {
                    val selDateInMin = selDate.time/60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMin = currentDate.time / 60000
                        val diff = currentDateInMin - selDateInMin

                        txt1?.setText(diff.toString())
                    }
                }

        }, year,month,day
        )

        dpd.datePicker.maxDate=System.currentTimeMillis() - 86400000
        dpd.show()


    }
}