package hr.pyro.chuckjokes.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hr.pyro.chuckjokes.presentation.di.component.activity.ActivityComponent;

public abstract class InjectorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

//    private void injectMe() {
//        inject(getActivityComponent());
//    }

    private ActivityComponent activityComponent;

    protected abstract void inject();

//    public final ActivityComponent getActivityComponent() {
//        /*
//            This can happen when system kills activity. In that case all components depending on
//            this one would crash. You can simulate this by checking
//            Settings->Developer options->Don't keep activities
//         */
//        if (activityComponent == null) {
//            activityComponent = ComponentFactory.createActivityComponent(this);
//        }
//        return activityComponent;
//    }
//
//    protected final KSETEventsApplication getMosApplication() {//TODO rename
//        return (KSETEventsApplication) getApplication();
//    }
}
