package com.example.myapp_001bdicethrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                DiceApp()
            }
        }
    }
}

@Composable
fun DiceApp() {
    val diceSymbols = listOf("⚀", "⚁", "⚂", "⚃", "⚄", "⚅")

    var diceValue by remember { mutableStateOf(1) }
    var isRolling by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    // --- ZMĚNA BAREV: Retro filmová paleta ---
    val backgroundColor = Color(0xFFF2E3C6) // Teplá krémová
    val primaryColor = Color(0xFF8C2727) // Tmavší vínová/cihlová
    val textColor = Color(0xFF2C3E50) // Tmavě modro-šedá pro nadpis

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .safeDrawingPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Zkus štěstí", // Úprava textu
            fontSize = 34.sp,     // Zvětšení písma
            fontFamily = FontFamily.Serif, // ZMĚNA FONTU na patkový
            fontWeight = FontWeight.Black, // Zesílení
            color = textColor
        )

        Text(
            text = diceSymbols[diceValue - 1],
            fontSize = 150.sp, // Zvětšená kostka (původně 120)
            color = primaryColor,
            modifier = Modifier.padding(vertical = 32.dp) // Větší mezery
        )

        Button(
            enabled = !isRolling,
            shape = RoundedCornerShape(12.dp), // ZMĚNA TVARU: Tlačítko teď není pilulka, ale má jen zaoblené rohy
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                contentColor = Color.White
            ),
            onClick = {
                isRolling = true
                scope.launch {
                    repeat(10) {
                        diceValue = (1..6).random()
                        delay(200) // Trochu zrychlená animace
                    }
                    diceValue = (1..6).random()
                    isRolling = false
                }
            }
        ) {
            Text(
                text = "Házet", // Úprava textu
                fontSize = 22.sp,
                fontFamily = FontFamily.Serif, // Font sjednocený s nadpisem
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp) // Zvětšení plochy tlačítka
            )
        }
    }
}