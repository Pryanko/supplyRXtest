package ru.supplyphotos.supplyrxtest.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author libgo (19.03.2018)
 */
@Module
@Singleton
public class RepositoryModule {

    @Provides
    @Singleton
    AppRepository appRepository(){
        return new AppRepository();
    }


}
