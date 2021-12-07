package lavsam.gb.profias.model.repository

import io.reactivex.Observable
import lavsam.gb.profias.model.data.Vocabulary
import lavsam.gb.profias.model.datasource.DataSource

class RepositoryImplementation(
    private val dataSource: DataSource<List<Vocabulary>>
) : Repository<List<Vocabulary>> {
    override fun getData(word: String): Observable<List<Vocabulary>> {
        return dataSource.getData(word)
    }
}