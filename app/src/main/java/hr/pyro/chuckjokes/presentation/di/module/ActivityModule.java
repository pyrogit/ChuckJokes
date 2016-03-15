package hr.pyro.chuckjokes.presentation.di.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import hr.pyro.chuckjokes.presentation.di.qualifier.ForActivity;
import hr.pyro.chuckjokes.presentation.di.scope.ActivityScope;
import hr.pyro.chuckjokes.presentation.router.Router;
import hr.pyro.chuckjokes.presentation.router.RouterImpl;
import hr.pyro.chuckjokes.presentation.view.activity.InjectorActivity;

@Module
public final class ActivityModule {

    private final InjectorActivity activity;

    public ActivityModule(InjectorActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    @ForActivity
    Context provideActivityContext() {
        return this.activity;
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater() {
        return activity.getLayoutInflater();
    }

    @Provides
    @ActivityScope
    @ForActivity
    Router provideRouter(RouterImpl router) {
        return router;
    }

//    @Provides
//    @ActivityScope
//    MainActivityPresenter providePresenter(){
//        MainActivityView view = new MainActivityView() {
//            @Override
//            public void requestTestData() {
//
//            }
//        };
//        return new MainActivityPresenterImpl(view);
//    }
}
