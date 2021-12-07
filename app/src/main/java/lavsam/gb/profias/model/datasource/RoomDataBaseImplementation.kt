package lavsam.gb.profias.model.datasource

import io.reactivex.Observable
import lavsam.gb.profias.model.data.Vocabulary

class RoomDataBaseImplementation : DataSource<List<Vocabulary>> {

    override fun getData(word: String): Observable<List<Vocabulary>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}