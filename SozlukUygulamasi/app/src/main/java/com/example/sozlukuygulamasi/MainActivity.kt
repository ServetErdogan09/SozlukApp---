package com.example.sozlukuygulamasi

import Kelimelerdao
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sozlukuygulamasi.databinding.ActivityMainBinding
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var kelimeAdapter: KelimeAdapter
    private lateinit var kelimeListesi: ArrayList<Kelimeler>
    private lateinit var kelimelerdao: Kelimelerdao
    private lateinit var vt: VeritabaniYardimci

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        kelimeListesi = ArrayList()
        veritabaniKopyala()

        vt = VeritabaniYardimci(this)

        // Toolbar'ı ayarlayın
        setSupportActionBar(binding.toolbar)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        kelimelerdao = Kelimelerdao()

        kelimeListesi = kelimelerdao.kelimeleriYazdir(vt)

        kelimeAdapter = KelimeAdapter(this, kelimeListesi)
        binding.recyclerView.adapter = kelimeAdapter
    }

    private fun veritabaniKopyala() {
        val dch = DatabaseCopyHelper(this)

        try {
            dch.createDataBase()
            dch.openDataBase()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.arama_menu, menu)
        val item = menu?.findItem(R.id.action_ara)
        val searchView = item?.actionView as? SearchView

        searchView?.setOnQueryTextListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            val kelime = kelimelerdao.kelimeGetir(vt, query)
            if (kelime != null) {
                kelimeListesi.clear()
                kelimeListesi.add(kelime)
                kelimeAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Kelime bulunamadı", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Kelime Giriniz", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
            val aramaSonucu = kelimelerdao.harfArama(vt, newText)
            kelimeListesi.clear()
            kelimeListesi.addAll(aramaSonucu)
            kelimeAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "Kelime Giriniz", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
