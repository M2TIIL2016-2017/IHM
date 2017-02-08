package com.iup.tp.twitup.controller;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.vue.ILoginView;

public abstract class AMainController {

	protected IDatabase mDatabase;
	protected EntityManager mEntityManager;

	
	protected abstract ILoginView createLV();
}
