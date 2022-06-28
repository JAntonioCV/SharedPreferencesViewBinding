package com.jantonioc.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jantonioc.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.save.setOnClickListener {
            savePreferences("save",binding.enterData.text.toString())
            binding.show.isEnabled = true
        }

        binding.show.setOnClickListener {
            binding.loadData.setText(loadPreferences())
        }

        binding.show.isEnabled = false

        if(loadPreferences()?.isNotEmpty() == true)
            binding.show.isEnabled= true

    }

    private fun savePreferences(key: String, value: String) {
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    private fun loadPreferences(): String?
    {
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val savedData = sharedPreferences.getString("save"," ")
        return savedData
    }
}