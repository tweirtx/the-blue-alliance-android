package com.thebluealliance.twitch;

import android.support.annotation.Nullable;

import com.sorcix.sirc.Channel;
import com.sorcix.sirc.IrcConnection;
import com.sorcix.sirc.MessageListener;
import com.sorcix.sirc.NickNameException;
import com.sorcix.sirc.PasswordException;

import java.io.IOException;

/**
 * Handles authenticating to twitch chat
 * Twitch channel #tbagameday
 */
public class TwitchChatController {

    public static final String TWITCH_IRC_SERVER = "irc.twitch.tv";
    public static final String GAMEDAY_IRC_CHANNEL = "#tbagameday";
    public static final int TWITCH_IRC_PORT = 6667;

    private IrcConnection mConnection;

    /**
     * Connects to IRC
     * @param username Nickname (twitch username)
     * @param oauthToken Oauth token (obtained via {@link OAuthController}
     * @param listener Listener to receive incoming messages
     */
    public @Nullable  Channel connectToGameday(String username, String oauthToken, MessageListener listener) {
        if (mConnection == null) {
            String password = "oauth:" + oauthToken;
            mConnection = new IrcConnection(TWITCH_IRC_SERVER, TWITCH_IRC_PORT, password);
            mConnection.setNick(username);
            mConnection.addMessageListener(listener);
        }

        try {
            mConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (NickNameException e) {
            e.printStackTrace();
            return null;
        } catch (PasswordException e) {
            e.printStackTrace();
            return null;
        }

        Channel channel = mConnection.createChannel(GAMEDAY_IRC_CHANNEL);

        channel.join();
        return channel;
    }

    public void disconnect() {
        if (mConnection != null) {
            mConnection.disconnect();
        }
    }
}
