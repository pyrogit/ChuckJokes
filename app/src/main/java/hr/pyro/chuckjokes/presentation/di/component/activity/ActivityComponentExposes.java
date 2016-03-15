package hr.pyro.chuckjokes.presentation.di.component.activity;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import hr.pyro.chuckjokes.presentation.di.qualifier.ForActivity;
import hr.pyro.chuckjokes.presentation.router.Router;

public interface ActivityComponentExposes {

    // Expose main activity component classes that are used by lower scope modules
    LayoutInflater layoutInflater();

    @ForActivity
    Context provideContext();

    FragmentManager provideFragmentManager();

    @ForActivity
    Router provideRouter();

//    MainActivityPresenter provideMainActivityPresenter();

}
