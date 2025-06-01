import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class FeldstueckViewModel(private val repo: FeldstueckRepository) : ViewModel() {
    var felder = mutableStateListOf<Feldstueck>()

    fun ladeFelder() {
        viewModelScope.launch {
            felder.clear()
            felder.addAll(repo.getAll())
        }
    }

    fun neuesFeld(feld: Feldstueck) {
        viewModelScope.launch {
            repo.insert(feld)
            ladeFelder()
        }
    }
}
