package com.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        resultButton.setOnClickListener {
            saveData(
                weightEditText.text.toString().toInt(),
                heightEditText.text.toString().toInt()
            )

            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }
    }

    private fun saveData(weight: Int, height: Int) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_WEIGHT", weight)
            .putInt("KEY_HEIGHT", height)
            .apply()
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val weight = pref.getInt("KEY_WEIGHT", 0)
        val height = pref.getInt("KEY_HEIGHT", 0)

        if (weight != 0 && height != 0) {
            weightEditText.setText(weight.toString())
            heightEditText.setText(height.toString())
        }
    }
}
