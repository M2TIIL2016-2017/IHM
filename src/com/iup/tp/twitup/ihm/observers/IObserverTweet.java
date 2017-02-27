package com.iup.tp.twitup.ihm.observers;

public interface IObserverTweet {

	public void sendTweet(String userTag , String tweet);
	public void deabonner(String userTag);
}
