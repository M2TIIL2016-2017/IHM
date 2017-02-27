package com.iup.tp.twitup.ihm.vue.swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.controller.LoginController;
import com.iup.tp.twitup.ihm.observers.IObserverLogin;
import com.iup.tp.twitup.ihm.vue.ILoginView;

public class TwitupLoginView implements ISwingView, ILoginView{
	private JPanel jPanel;
	
	protected Set<IObserverLogin> mObservers;	
	
	public TwitupLoginView()
	{
		this.mObservers = new HashSet<IObserverLogin>();
		panelView();
	}
	private void panelView()
	{
		this.jPanel = new JPanel();
		this.jPanel.setLayout(new GridBagLayout());

		JLabel connexionLabel= new JLabel("Connexion");
		JLabel userLabel = new JLabel("Nom utilisateur : ");
		JTextField userTf = new JTextField("Toto");
		JLabel passwordLabel = new JLabel("Mot de passe : ");
		JTextField passwordTf = new JTextField("Toto2");
		JButton helpBttn = new JButton("Help");
		JButton okBttn = new JButton("OK");

		userTf.setPreferredSize( new Dimension( 200, 24 ) );
		passwordTf.setPreferredSize( new Dimension( 200, 24 ) );

		this.jPanel.setBorder(BorderFactory.createEmptyBorder(17,17,17,17));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.insets = new Insets(0,0,15,0);
		this.jPanel.add(connexionLabel, constraints);

		constraints.insets = new Insets(0,0,0,0);
		constraints.gridy++;
		constraints.gridwidth = 1;
		//alignment for each label must be explicitly set
		constraints.anchor = GridBagConstraints.EAST;
		this.jPanel.add(userLabel, constraints);

		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.WEST;
		this.jPanel.add(userTf, constraints);

		constraints.gridy++;
		constraints.anchor = GridBagConstraints.EAST;
		this.jPanel.add(passwordLabel, constraints);

		constraints.anchor = GridBagConstraints.WEST;
		this.jPanel.add(passwordTf, constraints);

		constraints.gridy++;
		constraints.gridwidth = 3;
		this.jPanel.add(helpBttn, constraints);

		//nesting a panel to achieve desired button grouping
		constraints.anchor = GridBagConstraints.EAST;
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(Box.createHorizontalStrut(5));
		buttonBox.add(okBttn);
		this.jPanel.add(buttonBox, constraints);

		okBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click");
				String username = userTf.getText();
				String password = passwordTf.getText();
				for (IObserverLogin observer : mObservers) {
					observer.sendLogin(username,password);
				}

			}
		});
	}
	/*
	protected void isLogin()
	{
		if(this.login == false)
		{
			this.login = true;
			loginBarGUI();
		}
		else
		{
			System.out.println("Deco");
			this.login = false;
			this.menuBar.remove(this.loginBar);
			this.jframe.add(this.menuBar);
			this.jframe.setJMenuBar(this.menuBar);
		}

	}
	protected void loginBarGUI()
	{
			this.loginBar = new JMenu("Mon Compte");
			JMenuItem decoItem = new JMenuItem("Deconnexion");
			this.loginBar.add(decoItem);
			this.menuBar.add(this.loginBar);
			this.jframe.add(this.menuBar);
			this.jframe.setJMenuBar(this.menuBar);
			decoItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
					isLogin();
				}
			});

	}*/
	@Override
	public JComponent showView() {
		// TODO Auto-generated method stub
		this.jPanel.repaint();
		return this.jPanel;
	}
	@Override
	public void addObservers(LoginController unController) {
		// TODO Auto-generated method stub
		this.mObservers.add(unController);
	}
}
