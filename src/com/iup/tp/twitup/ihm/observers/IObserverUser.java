package com.iup.tp.twitup.ihm.observers;

import java.io.File;

public interface IObserverUser {
	
	public boolean changeProfile(String userTag , String username, String password);
	public boolean changePassword(String userTag,String password , String confirmPassword);
	public boolean changeAvatar(File uneImage, String userTag);
}
