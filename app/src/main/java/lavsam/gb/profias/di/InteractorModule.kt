package lavsam.gb.profias.di

import dagger.Module
import dagger.Provides
import lavsam.gb.profias.interactor.MainInteractor
import lavsam.gb.profias.model.data.Vocabulary
import lavsam.gb.profias.model.repository.Repository
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<Vocabulary>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<Vocabulary>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}
