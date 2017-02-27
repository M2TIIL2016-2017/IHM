package com.iup.tp.twitup.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.iup.tp.twitup.controller.observers.ITweetObserverController;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
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
	
	/**
	 * Fonction qui vérifie que le tweet est valide
	 * @param messageTweet
	 * @return
	 */
	public boolean isValideTweet(String messageTweet) {
		String messageErreur = "";
		
		if(messageTweet == null || messageTweet.length() == 0){
			messageErreur += "Renseigner le tweet ! \n";
		} else if(messageTweet.length() >= 150){
			messageErreur += "Le tweet dépasse les 150 caractères !";
		}
		if(messageErreur.length() > 0){
			return false;
		}
		
		return true;
	}

	/**
	 * Message d'ajout d'un tweet
	 */
	@Override
	public void sendTweet(User userTag, String tweet) {
		if(isValideTweet(tweet)){
			System.out.println("Envoi Tweet - Controller");
			Twit unTwit = new Twit(userTag, tweet);
			this.mDatabase.addTwit(unTwit);
			this.mEntityManager.sendTwit(unTwit);
		}

		
	}
}
