package lavsam.gb.profias.model.repository

import lavsam.gb.profias.model.data.Vocabulary
import lavsam.gb.profias.model.datasource.DataSource

class RepositoryImplementation(
    private val dataSource: DataSource<List<Vocabulary>>
) : Repository<List<Vocabulary>> {
    override suspend fun getData(word: String): List<Vocabulary> {
        return dataSource.getData(word)
    }
}