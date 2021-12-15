package lavsam.gb.profias.model.repository

import lavsam.gb.profias.model.data.AppState
import lavsam.gb.profias.model.data.Vocabulary
import lavsam.gb.profias.model.datasource.DataSourceLocal

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal<List<Vocabulary>>) :
    RepositoryLocal<List<Vocabulary>> {

    override suspend fun getData(word: String): List<Vocabulary> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}
