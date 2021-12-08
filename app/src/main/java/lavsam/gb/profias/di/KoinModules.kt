package lavsam.gb.profias.di

import lavsam.gb.profias.interactor.MainInteractor
import lavsam.gb.profias.model.data.Vocabulary
import lavsam.gb.profias.model.datasource.RetrofitImplementation
import lavsam.gb.profias.model.datasource.RoomDataBaseImplementation
import lavsam.gb.profias.model.repository.Repository
import lavsam.gb.profias.model.repository.RepositoryImplementation
import lavsam.gb.profias.viewmodel.MainActivityViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<Vocabulary>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(
            RetrofitImplementation()
        )
    }
    single<Repository<List<Vocabulary>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(
            RoomDataBaseImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainActivityViewModel(get()) }
}