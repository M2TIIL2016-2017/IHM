package com.iup.tp.twitup.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.Set;

import com.iup.tp.twitup.controller.observers.ILoginObserverController;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.Fichier1;
import com.iup.tp.twitup.ihm.observers.IObserverUser;

public class UserController implements IObserverUser {

	protected EntityManager mEntityManager;
	protected IDatabase mDatabase;

	protected Set<ILoginObserverController> mLoginObservers;
	protected Set<IObserverUser> mObserversUser;

	@Override
	public boolean changeProfile(String userTag, String username, String password) {
		Iterator<User> iterator = this.mDatabase.getUsers().iterator();
		System.out.println(this.mDatabase.getUsers());
		while (iterator.hasNext()) {
			User unUser = (User) iterator.next();
			if (unUser.getUserTag().equals(userTag) == true) {
				if (unUser.getUserPassword().equals(password) == true) {
					User unUserModif = unUser;
					unUserModif.setName(username);
					this.mDatabase.modifiyUser(unUserModif);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean changePassword(String userTag, String password, String confirmPassword) {

		Iterator<User> iterator = this.mDatabase.getUsers().iterator();
		System.out.println(this.mDatabase.getUsers());
		while (iterator.hasNext()) {
			User unUser = (User) iterator.next();
			if (unUser.getUserTag().equals(userTag) == true) {
				if (unUser.getUserPassword().equals(confirmPassword) == true) {
					User unUserModif = unUser;
					unUserModif.setUserPassword(password);
					this.mDatabase.modifiyUser(unUserModif);
					return true;
				}
			}

			// TODO Auto-generated method stub
		}
		return false;
	}

	public static Image scaleImage(Image source, int width, int height) {
	    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = (Graphics2D) img.getGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(source, 0, 0, width, height, null);
	    g.dispose();
	    return img;
	}
	//avec un facteur (<1 pour rétrécir, >1 pour agrandir):
	public static Image scaleImage(final Image source, final double factor) {
	    int width = (int) (source.getWidth(null) * factor);
	    int height = (int) (source.getHeight(null) * factor);
	    return scaleImage(source, width, height);
	}
	//avec une taille en pixels (=hauteur si portrait, largeur si paysage):
	public static Image scaleImage(Image source, int size) {
	    int width = source.getWidth(null);
	    int height = source.getHeight(null);
	    double f = 0;
	    if (width < height) {//portrait
	        f = (double) height / (double) width;
	        width = (int) (size / f);
	        height = size;
	    } else {//paysage
	        f = (double) width / (double) height;
	        width = size;
	        height = (int) (size / f);
	    }
	    return scaleImage(source, width, height);
	}
	
	
	@Override
	public boolean changeAvatar(File uneImage, String userTag) {
		
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public void sendModifCompte(String userTag, String username, char[]  password, Fichier1 unePhoto) {
		// TODO Auto-generated method stub
		Iterator<User> iterator = this.mDatabase.getUsers().iterator();
		System.out.println(this.mDatabase.getUsers());
		while (iterator.hasNext()) {
			User unUser = (User) iterator.next();
			if (unUser.getUserTag().equals(userTag) == true) {
				if (unUser.getUserPassword().equals(password) == true) {
					User unUserModif = unUser;
					unUserModif.setName(username);
					this.mDatabase.modifiyUser(unUserModif);
					
				}
			}
		}
	}

}
