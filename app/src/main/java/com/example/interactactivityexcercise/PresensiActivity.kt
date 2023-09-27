package com.example.interactactivityexcercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.interactactivityexcercise.databinding.ActivityPresensiBinding
import java.text.SimpleDateFormat
import java.util.*

class PresensiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPresensiBinding


    private val jenisKehadiran = arrayOf(
        "Hadir tepat waktu",
        "Sakit",
        "Terlambat",
        "izin"
    )



    var selectedDate : String = ""
    var selectedTime : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPresensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val adapterPresensi = ArrayAdapter(
                this@PresensiActivity,
                android.R.layout.simple_spinner_item, jenisKehadiran
            )
            adapterPresensi.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )
            presensiSpinner.adapter = adapterPresensi

            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id")) // "id" adalah kode bahasa Indonesia
            selectedDate = dateFormat.format(Calendar.getInstance().time)

            presensiDatePicker.init(
                presensiDatePicker.year,
                presensiDatePicker.month,
                presensiDatePicker.dayOfMonth
            ) { _, year, monthOfYear, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, monthOfYear, dayOfMonth)
                selectedDate = dateFormat.format(calendar.time)
            }

            presensiTimePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
                 selectedTime = String.format("%02d:%02d", hourOfDay, minute)
            }

            presensiSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        if(p2 != 0){
                            presensiKeterangan.visibility = View.VISIBLE
                        }
                        else{
                            presensiKeterangan.visibility = View.INVISIBLE
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                }

            presensiBtn.setOnClickListener{
                Toast.makeText(this@PresensiActivity, "Presensi berhasil $selectedDate jam $selectedTime", Toast.LENGTH_SHORT).show()
            }

        }
    }
}