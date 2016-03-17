package hr.pyro.chuckjokes.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class InjectorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    protected abstract void inject();

}
