package com.thebluealliance.twitch;

import com.sorcix.sirc.Channel;
import com.sorcix.sirc.IrcConnection;
import com.sorcix.sirc.MessageListener;
import com.sorcix.sirc.User;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class RxTwitchChat implements MessageListener {

    /**
     * {@link Observable} subject we can post to
     */
    private Subject<String, String> mSubject;

    public RxTwitchChat() {
        mSubject = new SerializedSubject<>(PublishSubject.<String>create());
    }

    public Observable<String> getObservable() {
        return mSubject;
    }

    /**
     * Completes the observable
     */
    public void finishDelivery() {
        mSubject.onCompleted();
    }

    @Override
    public void onAction(IrcConnection irc, User sender, Channel target, String action) {

    }

    @Override
    public void onAction(IrcConnection irc, User sender, String action) {

    }

    @Override
    public void onCtcpReply(IrcConnection irc, User sender, String command, String message) {

    }

    @Override
    public void onMessage(IrcConnection irc, User sender, Channel target, String message) {
        mSubject.onNext(sender.getNickLower() + ": " + message);
    }

    @Override
    public void onNotice(IrcConnection irc, User sender, Channel target, String message) {

    }

    @Override
    public void onNotice(IrcConnection irc, User sender, String message) {

    }

    @Override
    public void onPrivateMessage(IrcConnection irc, User sender, String message) {

    }
}
