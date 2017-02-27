package com.iup.tp.twitup.ihm.vue.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.iup.tp.twitup.controller.observers.ILoginObserverController;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.Message;
import com.iup.tp.twitup.ihm.observers.IObserverConfig;
import com.iup.tp.twitup.ihm.observers.IObserverInscription;
import com.iup.tp.twitup.ihm.observers.IObserverLogin;

public class TwitupMenuView implements IObserverLogin, IObserverInscription, ILoginObserverController{

	private JMenuBar menuBar;
	private JMenu loginMenu;
	private JMenu inscriptionMenu;
	private JMenu accueilMenu;
	private JMenu aProposMenu;
	private JMenu optionMenu;
	private JMenu logoutMenu;
	private Set<IObserverConfig> mObserversConfig = new HashSet<IObserverConfig>();	
	private Set<IObserverLogin> mObserversLogin = new HashSet<IObserverLogin>();	
	protected Set<ILoginObserverController> mObservers = new HashSet<ILoginObserverController>();	
	
	public TwitupMenuView()
	{
		//JMenuBar
		this.menuBar = new JMenuBar();
		this.accueilMenu = new JMenu("Accueil");
		this.inscriptionMenu = new JMenu("Inscription");
		
		this.loginMenu = new JMenu("Mon Compte");
		this.logoutMenu = new JMenu("Déconnexion");
		this.optionMenu = new JMenu("Options");
		this.aProposMenu = new JMenu("A propos");
		this.loginMenu.setVisible(false);
		this.logoutMenu.setVisible(false);
		
		final ImageIcon icon = new ImageIcon("gland.gif");
		
		JMenuItem about = new JMenuItem("?", new ImageIcon(""));
		
		JMenuItem configMenu = new JMenuItem("Configuration");
		optionMenu.add(configMenu);
		aProposMenu.add(about);
		
		this.menuBar.add(accueilMenu);
		
		this.menuBar.add(optionMenu);
		this.menuBar.add(loginMenu);
		this.menuBar.add(logoutMenu);
		this.menuBar.add(inscriptionMenu);
		this.menuBar.add(aProposMenu);
		loginMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Click : Mon Compte");
				for (IObserverLogin observer : mObserversLogin) {
					observer.monCompte();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
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
				System.out.println("Config click");
				for (IObserverConfig observer : mObserversConfig) {
					observer.configView();
				}
			}
		});
	
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affichageAPropos();
			}
		});
		
		logoutMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				loginMenu.setVisible(false);
				logoutMenu.setVisible(false);
				
				// TODO Auto-generated method stub
				System.out.println("Déconnexion");
				for (IObserverLogin observer : mObserversLogin) {
					observer.pageAccueilIsDeconnected();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		accueilMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				for (ILoginObserverController observer : mObservers) {
					observer.pageAccueil();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
	}
	
	public JMenuBar getMenuBar() {
		// TODO Auto-generated method stub
		return this.menuBar;
	}


	@Override
	public void inscription() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendInscription(String userTag, String username, char[] password) {
		// TODO Auto-generated method stub
		
	}
	public void addmObserversLogin(IObserverLogin aMainController) {
		// TODO Auto-generated method stub
		this.mObserversLogin.add(aMainController);
	}
	public void addmObserversConfig(IObserverConfig unObject)
	{
		this.mObserversConfig.add(unObject);
	}
	public void addmObservers(ILoginObserverController unObject) {
		// TODO Auto-generated method stub
		this.mObservers.add(unObject);
	}

	@Override
	public void inscription_ok() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connected() {
		this.loginMenu.setVisible(true);
		this.logoutMenu.setVisible(true);
		this.inscriptionMenu.setVisible(false);
		accueilMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Config click");
				for (IObserverLogin observer : mObserversLogin) {
					observer.pageAccueilIsLogin();
				}
			}
		});
		// TODO Auto-generated method stub
		
	}
	public void affichageAPropos() {
		JLabel jLabel = new JLabel("UBO M2-TIIL\n DÃ©partement Informatique", JLabel.CENTER);
		ImageIcon icon = new ImageIcon("./src/resources/images/logoIUP_50.jpg");
		JOptionPane.showMessageDialog(null, jLabel, "A propos", JOptionPane.PLAIN_MESSAGE, icon);
	}
	@Override
	public void monCompte() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendLogin(String username, char[] password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pageAccueilIsLogin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pageAccueilIsDeconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pageAccueil() {
		// TODO Auto-generated method stub
		
	}

	
}
