package lavsam.gb.profias.model.data.api

import kotlinx.coroutines.Deferred
import lavsam.gb.profias.model.data.Vocabulary
import retrofit2.http.GET
import retrofit2.http.Query

private const val QUERY_URL = "words/search"
private const val QUERY_SEARCH = "search"

interface ApiService {

    @GET(QUERY_URL)
    fun searchAsync(@Query(QUERY_SEARCH) wordToSearch: String): Deferred<List<Vocabulary>>
}
