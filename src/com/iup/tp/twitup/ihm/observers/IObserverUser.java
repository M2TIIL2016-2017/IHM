package com.iup.tp.twitup.ihm.observers;

import java.io.File;

public interface IObserverUser {
	
	public void changeProfile(String userTag , String username, String password);
	public void changePassword(String userTag,String password , String confirmPassword);
	public void changeAvatar(File uneImage, String userTag);
}
