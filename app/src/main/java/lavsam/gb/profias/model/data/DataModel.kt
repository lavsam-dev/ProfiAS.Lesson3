package lavsam.gb.profias.model.data

import com.google.gson.annotations.SerializedName

private const val URL_TEXT = "text"
private const val URL_MEANINGS = "meanings"

private const val URL_TRANSLATION = "translation"
private const val URL_IMAGE_URL = "imageUrl"

private const val URL_TRANSLATION_TEXT = "text"

class Vocabulary(
    @SerializedName(URL_TEXT) val text: String?,
    @SerializedName(URL_MEANINGS) val meanings: List<Meanings>?
)

class Meanings(
    @SerializedName(URL_TRANSLATION) val translation: Translation?,
    @SerializedName(URL_IMAGE_URL) val imageUrl: String?
)

class Translation(@SerializedName(URL_TRANSLATION_TEXT) val translation: String?)