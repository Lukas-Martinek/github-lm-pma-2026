package com.example.myapp004fragments// Zkontrolujte si svůj název balíčku!

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class fragment_a : Fragment() {

    // Nejdůležitější metoda Fragmentu - zde se propojuje Kotlin kód s XML vzhledem
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // "Nafoukne" (zobrazí) layout pro tento fragment
        return inflater.inflate(R.layout.fragment_a, container, false)
    }
}
