package hr.pyro.chuckjokes.presentation.util;

import android.content.Context;
import android.text.TextUtils;

import javax.inject.Inject;

import hr.pyro.chuckjokes.presentation.R;
import hr.pyro.chuckjokes.presentation.di.qualifier.ForApplication;
import retrofit.RetrofitError;

public final class ErrorMessageFactoryImpl implements ErrorMessageFactory {

    private final Context context;

    @Inject
    public ErrorMessageFactoryImpl(final @ForApplication Context context) {
        this.context = context;
    }

    @Override
    public String createMessageForError(final Throwable error) {
        if (error == null) {
            return "";
        }

        final String errorMessage = error.getMessage();
        if (TextUtils.isEmpty(errorMessage)) {
            return "";
        }

        if (error instanceof RetrofitError) {
            return context.getString(R.string.check_internet);
        }

        return errorMessage;
    }
}
