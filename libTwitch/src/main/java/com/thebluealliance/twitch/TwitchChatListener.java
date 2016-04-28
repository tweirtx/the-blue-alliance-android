package com.thebluealliance.twitch;

import android.provider.SyncStateContract;
import android.util.Log;

import rx.Subscriber;

public class TwitchChatListener extends Subscriber<String> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(String s) {
        Log.d("TWITCH", s);
    }
}
