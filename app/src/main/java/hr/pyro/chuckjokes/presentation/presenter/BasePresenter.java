package hr.pyro.chuckjokes.presentation.presenter;

import android.text.TextUtils;
import android.util.Log;

import hr.pyro.chuckjokes.presentation.util.ErrorMessageFactory;
import hr.pyro.chuckjokes.presentation.view.BaseView;

/**
 * Created on 08.03.16..
 */
public abstract class BasePresenter extends SubscribingPresenter {

    private static final String TAG = BasePresenter.class.getSimpleName();

    private final ErrorMessageFactory errorMessageFactory;

    public BasePresenter(ErrorMessageFactory errorMessageFactory) {
        this.errorMessageFactory = errorMessageFactory;
    }

    abstract protected BaseView getView();

    protected void showLoadingView() {
        final BaseView view = getView();
        if (view != null) {
            view.showLoading();
        }
    }

    protected void responseCompleted() {
        hideLoadingView();
    }

    protected void responseError(final Throwable error) {
        Log.e(TAG, "An error occurred in the presenter. ", error);
        hideLoadingView();
        showError(errorMessageFactory.createMessageForError(error));
    }

    private void hideLoadingView() {
        final BaseView view = getView();
        if (view != null) {
            view.hideLoading();
        }
    }

    private void showError(final String errorMessage) {
        final BaseView view = getView();
        if (view != null && !TextUtils.isEmpty(errorMessage)) {
            view.showError(errorMessage);
        }
    }

}