package com.example.myapp004fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp004fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Výchozí zobrazení po spuštění aplikace
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                // Změněno na tvůj název třídy: Moodboard()
                .replace(R.id.fragmentContainer, Moodboard())
                .commit()
        }

        // Tlačítko 1: Moodboard
        binding.btnMoodboard.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, Moodboard())
                .commit()
        }

        // Tlačítko 2: Lokace
        binding.btnLocation.setOnClickListener {
            supportFragmentManager.beginTransaction()
                // Předpokládám, že soubor se jmenuje Location.kt
                .replace(R.id.fragmentContainer, location())
                .commit()
        }

        // Tlačítko 3: Technika
        binding.btnGear.setOnClickListener {
            supportFragmentManager.beginTransaction()
                // Předpokládám, že soubor se jmenuje Gear.kt
                .replace(R.id.fragmentContainer, gear())
                .commit()
        }
    }
}