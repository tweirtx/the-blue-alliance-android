package com.thebluealliance.androidclient.di.components;

import com.thebluealliance.androidclient.di.GamedayChatModule;
import com.thebluealliance.androidclient.fragments.gameday.GamedayChatFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = GamedayChatModule.class)
public interface GamedayChatComponent {

    void inject(GamedayChatFragment fragment);
}
