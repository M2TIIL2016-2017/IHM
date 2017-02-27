package com.iup.tp.twitup.ihm.observers;

import com.iup.tp.twitup.datamodel.User;

public interface IObserverTweet {

	public void sendTweet(User userTag, String tweet);
	public void deabonner(String userTag);
}
