package hr.pyro.chuckjokes.presentation.view;

/**
 * Created on 08.03.16..
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    void showError(String errorMessage);
}