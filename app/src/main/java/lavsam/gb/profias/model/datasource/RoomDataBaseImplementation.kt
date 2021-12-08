package lavsam.gb.profias.model.datasource

import lavsam.gb.profias.model.data.Vocabulary

class RoomDataBaseImplementation : DataSource<List<Vocabulary>> {

    override suspend fun getData(word: String): List<Vocabulary> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}