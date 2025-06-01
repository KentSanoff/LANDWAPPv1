package example.landwapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF4CAF50),    // Grün
    secondary = Color(0xFF388E3C)   // Dunkleres Grün
    // weitere Farben nach Bedarf
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF388E3C),    // Dunkleres Grün
    secondary = Color(0xFF4CAF50)   // Grün
    // weitere Farben nach Bedarf
)

@Composable
fun LandwappTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography(), // Default Typography, kann angepasst werden
        shapes = Shapes(),         // Default Shapes, kann angepasst werden
        content = content
    )
}
