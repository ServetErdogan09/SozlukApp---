package com.example.sozlukuygulamasi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sozlukuygulamasi.databinding.ActivityDetayBinding
import java.io.Serializable

class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // aldığım nesnyei kelimeler nesnesine dönüştür
        val kelime = intent.getSerializableExtra("KelimeNesnesi") as? Kelimeler
        if (kelime != null) {
            binding.ingilizceTextVie.text = kelime.ingilizce
            binding.turkceTextView.text = kelime.turkce
        } else {
            // Hata mesajı veya başka bir işlem
            binding.ingilizceTextVie.text = "Kelime bulunamadı"
            binding.turkceTextView.text = ""
        }
    }
}