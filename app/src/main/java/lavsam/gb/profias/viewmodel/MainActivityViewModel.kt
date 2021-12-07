package lavsam.gb.profias.viewmodel

import androidx.lifecycle.LiveData
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import lavsam.gb.profias.interactor.MainInteractor
import lavsam.gb.profias.model.data.AppState
import lavsam.gb.profias.utils.parseSearchResults
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(doOnSubscribe())
                .subscribeWith(getObserver())
        )
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { liveDataForViewToObserve.postValue(AppState.Loading(null)) }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(state: AppState) {
                appState = parseSearchResults(state)
                liveDataForViewToObserve.postValue(appState)
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.postValue(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}