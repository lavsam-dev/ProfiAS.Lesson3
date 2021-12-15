package lavsam.gb.profias.model.repository

import lavsam.gb.profias.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}