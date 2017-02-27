package com.iup.tp.twitup.ihm.observers;

public interface IObserverInscription {

	public void sendInscription(String userTag , String username, char[] password);
	
	public void inscription();
}
