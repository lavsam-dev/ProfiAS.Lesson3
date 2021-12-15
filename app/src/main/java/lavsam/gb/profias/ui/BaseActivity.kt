package lavsam.gb.profias.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import lavsam.gb.profias.R
import lavsam.gb.profias.databinding.LoadingLayoutBinding
import lavsam.gb.profias.interactor.Interactor
import lavsam.gb.profias.model.data.AppState
import lavsam.gb.profias.model.data.Vocabulary
import lavsam.gb.profias.utils.network.isOnline
import lavsam.gb.profias.utils.ui.AlertDialogFragment
import lavsam.gb.profias.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    private lateinit var binding: LoadingLayoutBinding
    abstract val model: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        binding = LoadingLayoutBinding.inflate(layoutInflater)
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun isDialogNull(): Boolean =
        supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null

    //    abstract fun renderData(dataModel: T)
    protected fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showProgressBar(HIDE_PROGRESS_BAR)
                val data = appState.data
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showProgressBar(SHOW_PROGRESS_BAR)
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showProgressBar(HIDE_PROGRESS_BAR)
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    private fun showProgressBar(show: Boolean) = if (show) {
        binding.loadingFrameLayout.visibility = View.VISIBLE
    } else {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
        private const val SHOW_PROGRESS_BAR = true
        private const val HIDE_PROGRESS_BAR = false
    }

    abstract fun setDataToAdapter(data: List<Vocabulary>)
}