package com.iup.tp.twitup.controller;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import com.iup.tp.twitup.controller.observers.ILoginObserverController;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.observers.IObserverUser;

public class UserController implements IObserverUser {

	protected EntityManager mEntityManager;
	protected IDatabase mDatabase;

	protected Set<ILoginObserverController> mLoginObservers;
	protected Set<IObserverUser> mObserversUser;

	@Override
	public boolean changeProfile(String userTag, String username, String password) {
		Iterator<User> iterator = this.mDatabase.getUsers().iterator();
		System.out.println(this.mDatabase.getUsers());
		while (iterator.hasNext()) {
			User unUser = (User) iterator.next();
			if (unUser.getUserTag().equals(userTag) == true) {
				if (unUser.getUserPassword().equals(password) == true) {
					User unUserModif = unUser;
					unUserModif.setName(username);
					this.mDatabase.modifiyUser(unUserModif);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean changePassword(String userTag, String password, String confirmPassword) {

		Iterator<User> iterator = this.mDatabase.getUsers().iterator();
		System.out.println(this.mDatabase.getUsers());
		while (iterator.hasNext()) {
			User unUser = (User) iterator.next();
			if (unUser.getUserTag().equals(userTag) == true) {
				if (unUser.getUserPassword().equals(confirmPassword) == true) {
					User unUserModif = unUser;
					unUserModif.setUserPassword(password);
					this.mDatabase.modifiyUser(unUserModif);
					return true;
				}
			}

			// TODO Auto-generated method stub
		}
		return false;
	}

	@Override
	public boolean changeAvatar(File uneImage, String userTag) {
		return false;
		// TODO Auto-generated method stub

	}

}
