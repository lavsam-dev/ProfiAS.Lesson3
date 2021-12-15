package lavsam.gb.profias.ui.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_history.*
import lavsam.gb.profias.R
import lavsam.gb.profias.interactor.HistoryInteractor
import lavsam.gb.profias.model.data.AppState
import lavsam.gb.profias.model.data.Vocabulary
import lavsam.gb.profias.ui.BaseActivity
import lavsam.gb.profias.ui.HistoryAdapter
import lavsam.gb.profias.viewmodel.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        val bundle = intent.extras
        var word = bundle?.getString(HistoryActivity.SEARCH_WORD_EXTRA)
        if (word == null) word = ""
        model.getData(word, false)
    }

    override fun setDataToAdapter(data: List<Vocabulary>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (history_activity_recyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        history_activity_recyclerview.adapter = adapter
    }

    companion object {

        private const val SEARCH_WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b1"

        fun getIntent(
            context: Context,
            word: String?
        ): Intent = Intent(context, HistoryActivity::class.java).apply {
            putExtra(SEARCH_WORD_EXTRA, word)
        }
    }
}
