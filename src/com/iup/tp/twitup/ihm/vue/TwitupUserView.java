package com.iup.tp.twitup.ihm.vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.datamodel.User;

public class TwitupUserView implements ITwitUpView{

	private JPanel jPanel;
//	private JMenuBar menuBar;
	private User unUser;

	public TwitupUserView(User unUser)
	{
		this.unUser = unUser;
		panelView();
	}

	private void panelView()
	{
		/*
		 * Partie Option Menu
		 * 
		 */
//		JPanel userPanel = new JPanel();
		
		this.jPanel = new JPanel();
		this.jPanel.setLayout(new GridBagLayout());

		JLabel connexionLabel= new JLabel("Mon Compte");
		
		JLabel nameLabel = new JLabel("Votre Nom - Prenom : ");
		JTextField nameTf = new JTextField(unUser.getName());
		
		JLabel userTagLabel = new JLabel("Nom utilisateur : ");
		JTextField userTagTf = new JTextField(unUser.getUserTag());
		
		JLabel passwordLabel = new JLabel("Mot de passe : ");
		JTextField passwordTf = new JTextField();
		
		JButton helpBttn = new JButton("Help");
		JButton okBttn = new JButton("OK");

		nameTf.setPreferredSize( new Dimension( 200, 24 ) );
		userTagTf.setPreferredSize( new Dimension( 200, 24 ) );
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
		this.jPanel.add(nameLabel, constraints);

		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.WEST;
		this.jPanel.add(nameTf, constraints);
		
		constraints.gridy++;
		constraints.gridwidth = 1;
		//alignment for each label must be explicitly set
		constraints.anchor = GridBagConstraints.EAST;
		this.jPanel.add(userTagLabel, constraints);

		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.WEST;
		this.jPanel.add(userTagTf, constraints);
		

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
				
			}
		});
}

	@Override
	public JComponent showView() {
		// TODO Auto-generated method stub
		return this.jPanel;
	}
	
	
	
}
