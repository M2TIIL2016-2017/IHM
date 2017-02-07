package com.iup.tp.twitup.ihm;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.controller.LoginController;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.Fichier1;
import com.iup.tp.twitup.ihm.observers.IObserverLogin;
import com.iup.tp.twitup.ihm.vue.ITwitUpView;
import com.iup.tp.twitup.ihm.vue.TwitupConfigView;
import com.iup.tp.twitup.ihm.vue.TwitupLoginView;
import com.iup.tp.twitup.ihm.vue.TwitupMenuView;
/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView{
	
	protected JFrame jframe;
	protected JPanel jPanel;
	protected JMenuBar menuBar;
	
	public JMenuBar getMenuBar()
	{
		return this.menuBar;
	}
	public TwitupMainView(TwitupMenuView uneBarre)
	{
		initGUI(uneBarre);
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame frame = TwitupMainView.this.jframe;
				Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation((tailleEcran.width - frame.getWidth()) / 3, (tailleEcran.height - frame.getHeight()) / 4);
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				
				// Affichage
				TwitupMainView.this.jframe.repaint();
				TwitupMainView.this.jframe.setVisible(true);
				
			}
		});
	}
	
	private void initGUI(TwitupMenuView uneBarre) {
		// Création de la fenetre
		System.out.println("test");
		this.jframe = new JFrame("twitUP");
		this.jframe.setSize(500, 500);
		this.jframe.setLayout(new GridBagLayout());
		this.jframe.setJMenuBar(uneBarre.getMenuBar());
		this.jPanel = new JPanel();
		//this.jframe.getJMenuBar().add(this.menuBar);
		this.jframe.getContentPane().add(this.jPanel);
		
	}

	public void showView(ITwitUpView uneVue)
	{
		this.jPanel.removeAll();
		this.jPanel.add(uneVue.showView());
		this.jPanel.revalidate();
		this.jPanel.repaint();
		
		
//		this.jframe.getContentPane().add(this.jPanel);
		
		
		
	}
	

	
}

			
			
