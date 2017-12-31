package com.example.max.githubuserslist.utils;

/**
 * Created by Max on 31.12.2017.
 */

public class MessageEvent {

    public final byte message;
    public final Object link;

    public MessageEvent(byte message, Object link) {
        this.message = message;
        this.link = link;
    }
}
