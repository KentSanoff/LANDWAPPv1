package com.example.landwapp.util

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.landwapp.data.model.Feldstueck
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.InputStream

object PdfImportUtils {

    fun parseFeldstueckeFromPdf(context: Context, uri: Uri): List<Feldstueck> {
        val feldstuecke = mutableListOf<Feldstueck>()

        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val document = PDDocument.load(inputStream)
            val pdfText = PDFTextStripper().getText(document)

            // Beispiel-MFA-Zeile (anpassen je nach Format):
            // 12345/1  Dauerwiese  1.84 ha
            val regex = Regex("""(\d{5}/\d+)\s+([A-Za-zäöüÄÖÜß ]+)\s+([\d,.]+)\s*ha""")

            regex.findAll(pdfText).forEach { match ->
                val nummer = match.groupValues[1]
                val kultur = match.groupValues[2].trim()
                val flaeche = match.groupValues[3].replace(",", ".").toDoubleOrNull() ?: 0.0

                feldstuecke.add(
                    Feldstueck(
                        name = nummer,
                        kultur = kultur,
                        nutzung = kultur,
                        flaeche = flaeche
                    )
                )
            }

            document.close()
        } catch (e: Exception) {
            Log.e("PDF Import", "Fehler beim Import: ${e.message}")
        }

        return feldstuecke
    }
}
