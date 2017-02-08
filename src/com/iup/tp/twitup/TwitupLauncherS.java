package com.iup.tp.twitup;

import com.iup.tp.twitup.core.MainControllerSwing;

/**
 * Classe de lancement de l'application.
 * 
 * @author S.Lucas
 */
public class TwitupLauncherS {

	/**
	 * Launcher.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainControllerSwing twitup = new MainControllerSwing();
		twitup.show();
		
//		MockController mockController = new MockController(twitup.getDatabase());
//		mockController.startMock();
		
	}

}
