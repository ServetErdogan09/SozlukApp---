package com.example.sozlukuygulamasi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sozlukuygulamasi.databinding.VardTasarimBinding

class KelimeAdapter(private val mContext: Context , private  val kelimelerListe : List<Kelimeler>) :
    RecyclerView.Adapter<KelimeAdapter.CardViewTutucu>() {


        // buradan da görünümlere eriştik
        class CardViewTutucu( val binding : VardTasarimBinding) : RecyclerView.ViewHolder(binding.root){

        }

    //tasarımı bağladık
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewTutucu {

        val binding = VardTasarimBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return  CardViewTutucu(binding)
    }

    // kaç boyutunu belrledik
    override fun getItemCount(): Int {

        return kelimelerListe.size
    }

    // görünümlerLE İLGİLİ İŞLEMLERİ BURADA YAPTIK
    override fun onBindViewHolder(holder: CardViewTutucu, position: Int) {
        val kelime = kelimelerListe[position]

        holder.binding.ingilizceText.text =  kelime.ingilizce
        holder.binding.turkceText.text = kelime.turkce

        // kelime kartına tıklandığında nesneyle birlikte diğer activitye geç
        holder.binding.cardView.setOnClickListener {
            val intent = Intent(mContext,DetayActivity::class.java)
            intent.putExtra("KelimeNesnesi",kelime)
            holder.itemView.context.startActivity(intent)
        }
    }

}