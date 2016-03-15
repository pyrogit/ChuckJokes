package hr.pyro.chuckjokes.presentation.util;

import android.text.TextUtils;

import javax.inject.Inject;

public final class ErrorMessageFactoryImpl implements ErrorMessageFactory {

    @Inject
    public ErrorMessageFactoryImpl() {
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

        return errorMessage;
    }
}
