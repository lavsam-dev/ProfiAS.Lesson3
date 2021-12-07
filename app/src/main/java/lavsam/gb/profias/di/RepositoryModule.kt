package lavsam.gb.profias.di

import dagger.Module
import dagger.Provides
import lavsam.gb.profias.model.data.Vocabulary
import lavsam.gb.profias.model.datasource.DataSource
import lavsam.gb.profias.model.datasource.RetrofitImplementation
import lavsam.gb.profias.model.datasource.RoomDataBaseImplementation
import lavsam.gb.profias.model.repository.Repository
import lavsam.gb.profias.model.repository.RepositoryImplementation
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(
        @Named(NAME_REMOTE) dataSourceRemote:
        DataSource<List<Vocabulary>>
    ): Repository<List<Vocabulary>> =
        RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(
        @Named(NAME_LOCAL) dataSourceLocal:
        DataSource<List<Vocabulary>>
    ): Repository<List<Vocabulary>> =
        RepositoryImplementation(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<Vocabulary>> =
        RetrofitImplementation()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<Vocabulary>> =
        RoomDataBaseImplementation()
}