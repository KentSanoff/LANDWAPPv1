package com.example.landwapp.util

data class KulturGrenzwerte(
    val n: Double,
    val p: Double,
    val k: Double
)

object Grenzwerte {
    // Grenzwerte pro Kultur
    val werte = mapOf(
        "Mais" to KulturGrenzwerte(n = 170.0, p = 80.0, k = 120.0),
        "Winterweizen" to KulturGrenzwerte(160.0, 70.0, 100.0),
        "Wintergerste" to KulturGrenzwerte(150.0, 60.0, 90.0),
        "Wiese" to KulturGrenzwerte(170.0, 80.0, 130.0),
        "Soja" to KulturGrenzwerte(50.0, 40.0, 60.0),
        "Zuckerrübe" to KulturGrenzwerte(100.0, 80.0, 140.0),
        "Hirse" to KulturGrenzwerte(130.0, 50.0, 70.0),
        "Feldfutter" to KulturGrenzwerte(150.0, 60.0, 100.0)
    )

    fun get(kultur: String): KulturGrenzwerte {
        return werte[kultur] ?: KulturGrenzwerte(170.0, 80.0, 120.0)
    }
}
