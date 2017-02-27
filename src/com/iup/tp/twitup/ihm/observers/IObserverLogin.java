package com.iup.tp.twitup.ihm.observers;

public interface IObserverLogin {

	public void sendLogin(String username, char[] password);

	public void pageAccueilIsLogin();
	public void pageAccueilIsDeconnected();
	
}
