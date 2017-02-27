package com.iup.tp.twitup.ihm.observers;

public interface IObserverLogin {

	public void sendLogin(String username, String password);

	public void pageAccueilIsLogin();
	public void pageAccueilIsDeconnected();
	
}
