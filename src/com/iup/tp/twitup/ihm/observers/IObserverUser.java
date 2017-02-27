package com.iup.tp.twitup.ihm.observers;

import java.io.File;

import com.iup.tp.twitup.ihm.Fichier1;

public interface IObserverUser {
	
	public boolean changeProfile(String userTag , String username, String password);
	public boolean changePassword(String userTag,String password , String confirmPassword);
	public boolean changeAvatar(File uneImage, String userTag);
	public void sendModifCompte(String text, String text2, char[] password,Fichier1 unePhoto);
}
