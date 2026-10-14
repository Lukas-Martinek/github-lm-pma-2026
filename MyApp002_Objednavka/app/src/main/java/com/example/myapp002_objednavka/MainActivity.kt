package com.example.myapp002_objednavka

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapp002_objednavka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Přepínání obrázků při výběru objektivu
        binding.rbLens1.setOnClickListener {
            binding.ivLens.setImageResource(R.drawable.rf24_70)
        }

        binding.rbLens2.setOnClickListener {
            binding.ivLens.setImageResource(R.drawable.rf24_105)
        }

        binding.rbLens3.setOnClickListener {
            binding.ivLens.setImageResource(R.drawable.rf50)
        }

        // Zpracování objednávky s výpočtem ceny
        binding.btnOrder.setOnClickListener {
            var totalPrice = 0

            // Zjištění vybraného objektivu a přičtení základní ceny
            val lens = when (binding.rgLens.checkedRadioButtonId) {
                binding.rbLens1.id -> {
                    totalPrice += 60000
                    binding.rbLens1
                }
                binding.rbLens2.id -> {
                    totalPrice += 35000
                    binding.rbLens2
                }
                binding.rbLens3.id -> {
                    totalPrice += 5000
                    binding.rbLens3
                }
                else -> {
                    totalPrice += 60000
                    binding.rbLens1
                }
            }

            // Zjištění doplňků a přičtení jejich cen
            val filter = binding.cbFiltr.isChecked
            if (filter) totalPrice += 1500

            val clean = binding.cbClean.isChecked
            if (clean) totalPrice += 500

            val warranty = binding.cbWarranty.isChecked
            if (warranty) totalPrice += 2000

            // Složení základního textu ze slovníku
            val orderText = "${getString(R.string.summary_prefix)} ${lens.text}" +
                    (if (filter) getString(R.string.addon_filter) else "") +
                    (if (clean) getString(R.string.addon_cleaning) else "") +
                    (if (warranty) getString(R.string.addon_warranty) else "")

            // Přidání celkové ceny na konec (s využitím %d parametru ve strings.xml)
            val finalSummaryText = orderText + getString(R.string.total_price, totalPrice)

            // Zobrazení v aplikaci
            binding.tvOrder.text = finalSummaryText

            // Zviditelnění tlačítka Sdílet
            binding.btnShare.visibility = View.VISIBLE

            // Toast hláška
            Toast.makeText(this, getString(R.string.toast_order_sent, lens.text), Toast.LENGTH_SHORT).show()
        }

        // Funkce pro sdílení objednávky do jiných aplikací
        binding.btnShare.setOnClickListener {
            val textToShare = binding.tvOrder.text.toString()

            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textToShare)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}