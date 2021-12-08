package lavsam.gb.profias.interactor

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}