package com.iup.tp.twitup.core;

import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.ihm.vue.ILoginView;
import com.iup.tp.twitup.ihm.vue.IMainView;
import com.iup.tp.twitup.ihm.vue.IView;
import com.iup.tp.twitup.ihm.vue.swing.ISwingView;
import com.iup.tp.twitup.ihm.vue.swing.TwitupLoginView;
import com.iup.tp.twitup.ihm.vue.swing.TwitupMainViewS;

public class MainControllerSwing extends AMainController {

	@Override
	protected void initLookAndFeel() {
		Properties uneProperty = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		try {
			UIManager.setLookAndFeel(uneProperty.getProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected TwitupMainViewS createMainView(){
		TwitupMainViewS uneVue = new TwitupMainViewS(mMenuView);
		return uneVue;
		
	}

	@Override
	public TwitupLoginView createLoginView() {
		TwitupLoginView twitupLoginView = new TwitupLoginView();
		return twitupLoginView;
	}

}
