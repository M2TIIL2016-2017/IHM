package com.iup.tp.twitup.datamodel;

public class DatabaseObserver implements IDatabaseObserver {

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		System.out.println("Ajout Twit");
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
	
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		System.out.println("Ajout User");
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}
	
	
}
