package com.iup.tp.twitup.ihm.observers;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public interface IObserverTweet {

	public void sendTweet(User userTag, String tweet);
	public void deabonner(String userTag);
	void notifyTwitAdded(Twit addedTwit);
	void notifyTwitDeleted(Twit deletedTwit);
	void notifyTwitModified(Twit modifiedTwit);
	void notifyUserAdded(User addedUser);
	void notifyUserDeleted(User deletedUser);
	void notifyUserModified(User modifiedUser);
}
