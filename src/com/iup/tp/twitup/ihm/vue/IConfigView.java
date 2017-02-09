package com.iup.tp.twitup.ihm.vue;

import com.iup.tp.twitup.ihm.observers.IObserverConfig;

public interface IConfigView extends IView, IObserverConfig{


	void add(IObserverConfig unObject);
}
