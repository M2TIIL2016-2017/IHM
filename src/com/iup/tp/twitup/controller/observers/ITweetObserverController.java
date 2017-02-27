package com.iup.tp.twitup.controller.observers;

import java.util.List;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public interface ITweetObserverController {

	public void addTweet(User user, String messageTweet);

	public void notifyTwitListHasChanged(List<Twit> twitList);

}
