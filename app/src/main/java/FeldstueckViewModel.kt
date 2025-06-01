fun importiereFeldstuecke(liste: List<Feldstueck>) {
    viewModelScope.launch {
        repository.insertiereMehrereFeldstuecke(liste)
    }
}
