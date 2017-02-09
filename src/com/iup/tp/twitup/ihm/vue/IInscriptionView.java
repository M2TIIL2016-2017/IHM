package com.iup.tp.twitup.ihm.vue;

import com.iup.tp.twitup.controller.LoginController;
import com.iup.tp.twitup.ihm.observers.IObserverInscription;

public interface IInscriptionView  extends IView, IObserverInscription {

	void add(LoginController loginController);
	
}
