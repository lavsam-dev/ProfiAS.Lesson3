package lavsam.gb.profias.model.data

sealed class AppState {
    data class Success(val data: List<Vocabulary>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}