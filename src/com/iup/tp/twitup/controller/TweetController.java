package com.iup.tp.twitup.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.iup.tp.twitup.controller.observers.ITweetObserverController;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.observers.IObserverTweet;

public class TweetController implements IObserverTweet{
	
	protected EntityManager mEntityManager;
	
	protected IDatabase mDatabase;

	protected Set<ITweetObserverController> mObservers;
	
	public void addObservers(ITweetObserverController tweetObserverController) {
		this.mObservers.add(tweetObserverController);
	}
	
	public TweetController(IDatabase database, EntityManager mEntityManager) {
		this.mObservers = new HashSet<ITweetObserverController>();
		this.mDatabase = database;
		this.mEntityManager = mEntityManager;
	}
	
	@Override
	public void sendTweet(String userTag, String tweet) {
		// TODO Auto-generated method stub
		
		System.out.println("User : " + userTag + " Tweet : " + tweet);
		Twit unTweet = this.isValide(userTag, tweet);
		for(ITweetObserverController tweetObserverController : mObservers){
			tweetObserverController.newTweet(unTweet);
		}
		
	}
	
	/**
	 * Fonction qui vérifie que le tweet est valide
	 * @param userTag
	 * @param message
	 * @return
	 */
	public Twit isValide(String userTag, String message) {
		// Récupération de la liste des tweets dans la DB
		Iterator<Twit> iterator = this.mDatabase.getTwits().iterator();
		System.out.println("Liste : " + this.mDatabase.getTwits());
		
		while (iterator.hasNext()) {
			Twit unTwit = iterator.next();
			if(unTwit.getUserTags().equals(userTag) && unTwit.getText().equals(message)) {
				System.out.println("Tweet Valide :" + unTwit.getUserTags() + unTwit.getText());
				return unTwit;
			}
		}
		return null;
	}

	@Override
	public void deabonner(String userTag) {
		// TODO Auto-generated method stub
		
	}
}
