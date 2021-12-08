package lavsam.gb.profias.model.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}