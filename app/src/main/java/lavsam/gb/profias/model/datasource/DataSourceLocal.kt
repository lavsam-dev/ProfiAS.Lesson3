package lavsam.gb.profias.model.datasource

import lavsam.gb.profias.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}
