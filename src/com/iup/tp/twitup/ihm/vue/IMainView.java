package com.iup.tp.twitup.ihm.vue;

import com.iup.tp.twitup.ihm.IView;

public interface IMainView <T extends IView>{
	public void showView(T view);
}
