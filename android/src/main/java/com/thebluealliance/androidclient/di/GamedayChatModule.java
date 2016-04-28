package com.thebluealliance.androidclient.di;

import com.thebluealliance.twitch.RxTwitchChat;
import com.thebluealliance.twitch.TwitchChatListener;
import com.thebluealliance.twitch.TwitchChatController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GamedayChatModule {

    @Provides @Singleton
    public TwitchChatController provideTwitchChatController() {
        return new TwitchChatController();
    }


    @Provides @Singleton
    public TwitchChatListener provideTwitchChatListener() {
        return new TwitchChatListener();
    }

    @Provides @Singleton
    public RxTwitchChat provideRxTwitchChat() {
        return new RxTwitchChat();
    }
}
