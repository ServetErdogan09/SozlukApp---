import com.example.sozlukuygulamasi.Kelimeler
import com.example.sozlukuygulamasi.VeritabaniYardimci

class Kelimelerdao {

    fun kelimeleriYazdir(vt: VeritabaniYardimci): ArrayList<Kelimeler> {
        val kelimelerList = ArrayList<Kelimeler>()
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM kelimeler", null)

        while (cursor.moveToNext()) {
            val kelime = Kelimeler(
                cursor.getInt(cursor.getColumnIndexOrThrow("kelime_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("ingilizce")),
                cursor.getString(cursor.getColumnIndexOrThrow("turkce"))
            )
            kelimelerList.add(kelime)
        }
        cursor.close()
        return kelimelerList
    }

    fun kelimeGetir(vt: VeritabaniYardimci, kelime_ad: String): Kelimeler? {
        val db = vt.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce = ?", arrayOf(kelime_ad))
        var kelime: Kelimeler? = null
        if (cursor.moveToFirst()) {
            kelime = Kelimeler(
                cursor.getInt(cursor.getColumnIndexOrThrow("kelime_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("ingilizce")),
                cursor.getString(cursor.getColumnIndexOrThrow("turkce"))
            )
        }
        cursor.close()
        return kelime
    }

    fun harfArama(vt: VeritabaniYardimci, aramaTerimi: String): ArrayList<Kelimeler> {
        val kelimelerList = ArrayList<Kelimeler>()
        val db = vt.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM kelimeler WHERE ingilizce LIKE ?", arrayOf("%$aramaTerimi%"))

        while (cursor.moveToNext()) {
            val kelime = Kelimeler(
                cursor.getInt(cursor.getColumnIndexOrThrow("kelime_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("ingilizce")),
                cursor.getString(cursor.getColumnIndexOrThrow("turkce"))
            )
            kelimelerList.add(kelime)
        }
        cursor.close()
        return kelimelerList
    }
}
