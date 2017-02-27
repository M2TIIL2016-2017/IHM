package com.iup.tp.twitup.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.iup.tp.twitup.controller.observers.ITweetObserverController;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.observers.IObserverTweet;
import com.iup.tp.twitup.ihm.vue.swing.TwitListComponentSwing;
import com.iup.tp.twitup.mock.ITwitListObserver;

public class TweetController implements IObserverTweet, ITweetObserverController ,  IDatabaseObserver{
	
	protected EntityManager mEntityManager;
	
	protected IDatabase mDatabase;

	protected Set<ITweetObserverController> mObservers;
	
	protected List<Twit> twitList = new ArrayList<Twit>();
	
	public ArrayList<Twit> twitList()
	{
		return (ArrayList<Twit>) twitList;
	}
	
	
	public void addObservers(ITweetObserverController tweetObserverController) {
		System.out.println("Je rentre AddObserver Tweet");
		this.mObservers.add(tweetObserverController);
		
	}
	
	public TweetController(IDatabase database, EntityManager mEntityManager) {
		this.mObservers = new HashSet<ITweetObserverController>();
		this.mDatabase = database;
		this.mDatabase.addObserver(this);
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
	


	/**
	 * @param text
	 */
	public void searchTwits(String text) {
		System.out.println("Recherche Tweet " + text);
		ArrayList<Twit> newTwitList = new ArrayList<Twit>();
		
		// Récupération des twits à filtrer
		Set<Twit> databaseTwits = this.mDatabase.getTwits();
		if (text == null || text.isEmpty())
		{
			newTwitList = new ArrayList<Twit>(databaseTwits);
		}
		else
		{
			for (Twit twit : databaseTwits) {
				if (twit.getText().contains(text))
				{
					newTwitList.add(twit);
				}
			}
		}
		
		// Ajout de la nouvelle liste
		this.twitList = newTwitList;
		
		// Tri de la nouvelle liste
		this.sortTwits();
		
		// Notification
		System.out.println("Ntifier Changement liste");
		this.notifyObservers();
	}

	public void addObserver(ITweetObserverController observer) {
		this.mObservers.add(observer);
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		
		// Ajout du nouveau twit
		twitList.add(addedTwit);

		// Tri des twits
		this.sortTwits();
		
		// Notification
		
		this.notifyObservers();
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		twitList.remove(deletedTwit);
		this.notifyObservers();
	}
	
	protected void sortTwits()
	{
		twitList.sort(new Comparator<Twit>() {

			@Override
			public int compare(Twit t1, Twit t2) {
				Long firstTwitEmissionDate = t1.getEmissionDate();
				Long secondTwitEmissionDate = t2.getEmissionDate();
				return firstTwitEmissionDate.compareTo(secondTwitEmissionDate);
			}
		});
	}

	private void notifyObservers() {
		System.out.println(mObservers.size());
		for (ITweetObserverController observer : mObservers) {
			System.out.println("Liste Rechargé");
			observer.notifyTwitListHasChanged(twitList);
		}
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// NA
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// NA
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// NA
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// NA
	}

	@Override
	public void deabonner(String userTag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTweet(User user, String messageTweet) {
		if(isValideTweet(messageTweet)){
			System.out.println("Envoi Tweet - Controller");
			Twit unTwit = new Twit(user, messageTweet);
			//this.mDatabase.addTwit(unTwit);
			this.mEntityManager.sendTwit(unTwit);
			this.notifyTwitAdded(unTwit);
		}
	}
	@Override
	public void notifyTwitListHasChanged(List<Twit> twitList) {
		// TODO Auto-generated method stub
		
	}

	public void load()
	{
		this.searchTwits("");
	}
	
}
