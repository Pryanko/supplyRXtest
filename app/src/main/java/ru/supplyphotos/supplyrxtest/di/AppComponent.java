package ru.supplyphotos.supplyrxtest.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author libgo (19.03.2018)
 */
@Singleton
@Component(modules = RepositoryModule.class)
public interface AppComponent {

    AppRepository getAppRepository();

}
