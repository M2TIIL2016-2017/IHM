package com.iup.tp.twitup.ihm.vue;

import java.util.Set;

import com.iup.tp.twitup.datamodel.Twit;

public interface IListTweetView extends IView
{

	void setLstTwit(Set<Twit> mTwits);

}
