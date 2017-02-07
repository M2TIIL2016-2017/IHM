package com.iup.tp.twitup.controller;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.controller.observers.ILoginObserverController;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.observers.IObserverInscription;
import com.iup.tp.twitup.ihm.observers.IObserverLogin;

public class LoginController implements IObserverLogin , IObserverInscription {
	
	protected EntityManager mEntityManager;
	protected IDatabase mDatabase;
	
	protected Set<ILoginObserverController> mObservers;
	protected Set<IObserverInscription> mObserversInscription;

	public void addmObservers(ILoginObserverController unObject)
	{
		this.mObservers.add(unObject);
	}
	
	public void addmObserversInscription(IObserverInscription unObject)
	{
		this.mObserversInscription.add(unObject);
	}
	
	public LoginController(IDatabase uneDatabase, EntityManager mEntityManager)
	{
		this.mObservers = new HashSet<ILoginObserverController>();
		this.mObserversInscription = new HashSet<IObserverInscription>();
		this.mDatabase = uneDatabase;
		this.mEntityManager = mEntityManager; 
	}
	
	@Override
	public void sendLogin(String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("User : " + username + "Pass : " + password);
		User unUser = this.isLogin(username, password);
		for (ILoginObserverController observer : mObservers) {
			observer.connected(unUser);
		}
		
	}
	
	
	@Override
	public void inscription()
	{
		for (ILoginObserverController observer : mObservers) {
			observer.inscription();
		}
	}
	
	public User isLogin(String username, String password)
	{
		
		Iterator<User> iterator = this.mDatabase.getUsers().iterator();
		System.out.println(this.mDatabase.getUsers());
		while(iterator.hasNext()){
			User unUser = (User) iterator.next();
			if(unUser.getUserTag().equals(username) == true)
			{
				System.out.println(unUser.getName() + unUser.getUserPassword());
				if(unUser.getUserPassword().equals(password) == true)
				{
					return unUser;
				}
			}
		    //do something to object;    
		}		
		return null;
	}

	public boolean inscriptionDoublon(String userTag)
	{
		
		Iterator<User> iterator = this.mDatabase.getUsers().iterator();
		System.out.println(this.mDatabase.getUsers());
		while(iterator.hasNext()){
			User unUser = (User) iterator.next();
			if(unUser.getUserTag().equals(userTag) == true)
			{
				return true;
			}
		    //do something to object;    
		}	
		return false;
	}
	@Override
	public void sendInscription(String userTag, String username, String password) {
		// TODO Auto-generated method stub
		System.out.println("Send Inscription - Controller");
		if(inscriptionDoublon(userTag) == false)
				{

					User unUser = new User(UUID.randomUUID(),userTag, password , username, new HashSet<String>(), null);
					this.mDatabase.addUser(unUser);
					this.mEntityManager.sendUser(unUser);
				}
		else
		{
			System.out.println("Deja inscrit");
		}
			
	}
}
