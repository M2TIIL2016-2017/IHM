package com.iup.tp.twitup.controller.observers;

import com.iup.tp.twitup.datamodel.User;

public interface ITweetObserverController {

	public void addTweet(User user, String messageTweet);

}
