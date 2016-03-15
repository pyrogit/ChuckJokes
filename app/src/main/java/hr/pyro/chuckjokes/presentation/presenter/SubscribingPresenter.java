package hr.pyro.chuckjokes.presentation.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created on 08.03.16..
 */
public abstract class SubscribingPresenter implements ScopedPresenter {

    private CompositeSubscription subscriptions;

    @Override
    public void deactivate() {
        unSubscribe();
    }

    /**
     * Adds subscription to composite subscription
     *
     * @param subscription Subscription to add
     */
    protected void addSubscription(Subscription subscription) {
        if (subscriptions == null) {
            subscriptions = new CompositeSubscription();
        }

        subscriptions.add(subscription);
    }

    private void unSubscribe() {
        if (subscriptions != null && !subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }

        subscriptions = null;
    }
}
