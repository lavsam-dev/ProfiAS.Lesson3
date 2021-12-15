package lavsam.gb.profias.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import lavsam.gb.profias.R
import lavsam.gb.profias.databinding.ActivityMainBinding
import lavsam.gb.profias.interactor.MainInteractor
import lavsam.gb.profias.model.data.AppState
import lavsam.gb.profias.model.data.Vocabulary
import lavsam.gb.profias.ui.BaseActivity
import lavsam.gb.profias.ui.DescriptionActivity
import lavsam.gb.profias.ui.SearchDialogFragment
import lavsam.gb.profias.utils.convertMeaningsToString
import lavsam.gb.profias.utils.network.isOnline
import lavsam.gb.profias.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

//private const val SHOW_PROGRESS_BAR = true
//private const val HIDE_PROGRESS_BAR = false

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainActivityViewModel
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: Vocabulary) {
//                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings[0].imageUrl
                    )
                )
            }
        }
    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException(getString(R.string.ErrorViewModelInit))
        }
        val viewModel: MainActivityViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@MainActivity, { renderData(it) })

        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    override fun setDataToAdapter(data: List<Vocabulary>) {
        adapter.setData(data)
    }

//    override fun renderData(appState: AppState) {
//        when (appState) {
//            is AppState.Success -> {
//                showProgressBar(HIDE_PROGRESS_BAR)
//                val data = appState.data
//                if (data.isNullOrEmpty()) {
//                    showAlertDialog(
//                        getString(R.string.dialog_tittle_sorry),
//                        getString(R.string.empty_server_response_on_success)
//                    )
//                } else {
//                    adapter.setData(data)
//                }
//            }
//            is AppState.Loading -> {
//                showProgressBar(SHOW_PROGRESS_BAR)
//                if (appState.progress != null) {
//                    binding.progressBarHorizontal.visibility = View.VISIBLE
//                    binding.progressBarRound.visibility = View.GONE
//                    binding.progressBarHorizontal.progress = appState.progress
//                } else {
//                    binding.progressBarHorizontal.visibility = View.GONE
//                    binding.progressBarRound.visibility = View.VISIBLE
//                }
//            }
//            is AppState.Error -> {
//                showProgressBar(HIDE_PROGRESS_BAR)
//                showAlertDialog(getString(R.string.error_stub), appState.error.message)
//            }
//        }
//    }
//
//    private fun showProgressBar(show: Boolean) = if (show) {
//        binding.loadingFrameLayout.visibility = View.VISIBLE
//    } else {
//        binding.loadingFrameLayout.visibility = View.GONE
//    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}