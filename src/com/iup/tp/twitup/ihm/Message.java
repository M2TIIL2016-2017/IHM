package com.iup.tp.twitup.ihm;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 * 
 * @author jeremy
 *	Classe d'affichage de message de notification
 */

public class Message {
	public void afficher_msg_info(){
		System.out.println("MSG1");
		final ImageIcon icon = new ImageIcon("gland.gif");
		JOptionPane.showMessageDialog(null, " UBO M2-TIIL \n Département informatique ", "A propos", JOptionPane.PLAIN_MESSAGE, icon);
		System.out.println("MSG2");
	}
}
