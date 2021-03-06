package com.iup.tp.twitup.ihm.vue.swing;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.observers.IObserverTweet;
import com.iup.tp.twitup.ihm.vue.ITweetView;

public class TwitupTweetView implements ISwingView, ITweetView {

	private JPanel panel;

	protected Set<IObserverTweet> mObservers;

	/**
	 * Affichage de la vue de Tweet
	 */
	@Override
	public JComponent showView() {
		return panel;
	}
	
	public void add(IObserverTweet observerTweet) {
		mObservers.add(observerTweet);
	}

	public TwitupTweetView() {
		this.mObservers = new HashSet<IObserverTweet>();
		panelView();
	}

	/**
	 * Affichage de la fenetre de tweet
	 */
	private void panelView() {
		

	}

	@Override
	public void sendTweet(User userTag, String tweet) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deabonner(String userTag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}

}
