package com.iup.tp.twitup.ihm.vue;

import com.iup.tp.twitup.controller.LoginController;

public interface ILoginView extends IView{

	void addObservers(LoginController unController);

}
