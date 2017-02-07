package com.iup.tp.twitup.controller;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import com.iup.tp.twitup.controller.observers.ILoginObserverController;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.observers.IObserverUser;

public class UserController implements IObserverUser{

	protected EntityManager mEntityManager;
	protected IDatabase mDatabase;
	
	protected Set<ILoginObserverController> mLoginObservers;
	protected Set<IObserverUser> mObserversUser;
	@Override
	public void changeProfile(String userTag, String username, String password) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changePassword(String userTag, String password, String confirmPassword) {
		
		Iterator<User> iterator = this.mDatabase.getUsers().iterator();
		System.out.println(this.mDatabase.getUsers());
		while(iterator.hasNext()){
			User unUser = (User) iterator.next();
			if(unUser.getUserTag().equals(userTag) == true)
			{
				if(unUser.getUserPassword().equals(confirmPassword) == true)
				{
					User unUserModif = unUser;
					unUserModif.setUserPassword(password);
					this.mDatabase.modifiyUser(unUserModif);
				}
			}
			
		// TODO Auto-generated method stub
		}	
	}
	@Override
	public void changeAvatar(File uneImage, String userTag) {
		// TODO Auto-generated method stub
		
	}
	
}
