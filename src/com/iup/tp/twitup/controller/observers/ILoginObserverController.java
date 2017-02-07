package com.iup.tp.twitup.controller.observers;

import com.iup.tp.twitup.datamodel.User;

public interface ILoginObserverController {

		public void connected(User user);
		public void inscription();
		public void inscription_ok();
		
}
