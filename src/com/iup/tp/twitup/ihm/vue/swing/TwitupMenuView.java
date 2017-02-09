package com.iup.tp.twitup.ihm.vue.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.iup.tp.twitup.controller.observers.ILoginObserverController;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.Message;
import com.iup.tp.twitup.ihm.observers.IObserverInscription;

public class TwitupMenuView implements IObserverInscription, ILoginObserverController{

	private JMenuBar menuBar;
	private JMenu loginMenu;
	private JMenu inscriptionMenu;
	private JMenu accueilMenu;
	private JMenu aProposMenu;
	private JMenu optionMenu;
	protected Set<ILoginObserverController> mObservers = new HashSet<ILoginObserverController>();	
	
	public TwitupMenuView()
	{
		//JMenuBar
		this.menuBar = new JMenuBar();
		this.accueilMenu = new JMenu("Accueil");
		this.aProposMenu = new JMenu("A propos");
		this.optionMenu = new JMenu("Options");
		this.loginMenu = new JMenu("Mon Compte");
		this.inscriptionMenu = new JMenu("Inscription");
		this.loginMenu.setVisible(false);
		
		final ImageIcon icon = new ImageIcon("gland.gif");
		
		// Sous-Menu de fichier
		JMenuItem sousMenuOuvrir = new JMenuItem("Ouvrir");
		JMenuItem sousMenuQuitter = new JMenuItem("Quitter", icon);
		JMenuItem about = new JMenuItem("?", new ImageIcon(""));
		
		JMenuItem configMenu = new JMenuItem("Configuration");
		optionMenu.add(configMenu);
		accueilMenu.add(sousMenuOuvrir);
		accueilMenu.add(sousMenuQuitter);
		
		aProposMenu.add(about);
		
		this.menuBar.add(accueilMenu);
		this.menuBar.add(aProposMenu);
		this.menuBar.add(optionMenu);
		this.menuBar.add(loginMenu);
		this.menuBar.add(inscriptionMenu);
		
		inscriptionMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Click : Inscription");
				for (ILoginObserverController observer : mObservers) {
					observer.inscription();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			
		});
		configMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sousMenuOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		sousMenuQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clic quit");
				System.exit(0);
			}
		});
		
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clic a propos");
				Message msg = new Message();
				msg.afficher_msg_info();
			}
		});
	}
	
	public JMenuBar getMenuBar() {
		// TODO Auto-generated method stub
		return this.menuBar;
	}

	@Override
	public void connected(User user) {
		System.out.println("test menuBar");
		loginMenu.setVisible(true);
//		// TODO Auto-generated method stub
//		JMenu loginMenu = this.menuBar.getMenu(3);
//		loginMenu.setVisible(true);
//		this.menuBar.remove(3);
//		this.menuBar.add(loginMenu);
//		this.menuBar.getParent().repaint();
//		this.menuBar.revalidate();
//		this.menuBar.repaint();
	}

	@Override
	public void inscription() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendInscription(String userTag, String username, String password) {
		// TODO Auto-generated method stub
		
	}

	public void addmObservers(ILoginObserverController unObject) {
		// TODO Auto-generated method stub
		this.mObservers.add(unObject);
	}

	@Override
	public void inscription_ok() {
		// TODO Auto-generated method stub
		
	}
}
