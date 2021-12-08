package lavsam.gb.profias.model.datasource

interface DataSource<T> {

    suspend fun getData(word: String): T
}