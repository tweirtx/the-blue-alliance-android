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

    public static final String TWITCH_IRC_SERVER = "irc.chat.twitch.tv";
    public static final String GAMEDAY_IRC_CHANNEL = "#tbagameday";
    public static final int TWITCH_IRC_PORT = 6667;

    /**
     * Connects to IRC
     * @param username Nickname (twitch username)
     * @param oauthToken Oauth token (obtained via {@link OAuthController}
     * @param listener Listener to receive incoming messages
     */
    public @Nullable  Channel connectToGameday(String username, String oauthToken, MessageListener listener) {
        IrcConnection connection = new IrcConnection(TWITCH_IRC_SERVER, TWITCH_IRC_PORT, oauthToken);
        connection.setNick(username);
        connection.addMessageListener(listener);

        try {
            connection.connect();
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

        Channel channel = connection.createChannel(GAMEDAY_IRC_CHANNEL);

        channel.join();
        return channel;
    }
}
