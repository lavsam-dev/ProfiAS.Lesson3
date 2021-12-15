package lavsam.gb.profias.interactor

import lavsam.gb.profias.model.data.AppState
import lavsam.gb.profias.model.data.Vocabulary
import lavsam.gb.profias.model.repository.Repository
import lavsam.gb.profias.model.repository.RepositoryLocal

class HistoryInteractor (
    private val repositoryRemote: Repository<List<Vocabulary>>,
    private val repositoryLocal: RepositoryLocal<List<Vocabulary>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word)
        )
    }
}