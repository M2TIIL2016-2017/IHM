package com.iup.tp.twitup.ihm.vue.swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.iup.tp.twitup.controller.LoginController;
import com.iup.tp.twitup.ihm.observers.IObserverInscription;
import com.iup.tp.twitup.ihm.observers.IObserverLogin;
import com.iup.tp.twitup.ihm.vue.IInscriptionView;

public class TwitupInscriptionView implements ISwingView , IObserverInscription , IInscriptionView{

	private JPanel jPanel;
//	private JMenuBar menuBar;
	private Set<IObserverInscription> mObservers;

	

	public TwitupInscriptionView()
	{
		this.mObservers = new HashSet<IObserverInscription>();
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

		JLabel inscriptionLabel= new JLabel("Inscription");
		JLabel etatInscriptionLabel = new JLabel("eeeee");
		
		JLabel nameLabel = new JLabel("Votre Nom - Prenom : ");
		JTextField nameTf = new JTextField();
		
		JLabel userTagLabel = new JLabel("Nom utilisateur : ");
		JTextField userTagTf = new JTextField();
		
		JLabel passwordLabel = new JLabel("Mot de passe : ");
		JPasswordField passwordTf = new JPasswordField();
		
		JLabel confimPasswordLabel = new JLabel("Confirmation Mot de passe : ");
		JPasswordField confimPasswordTf = new JPasswordField();
		
		
		JButton helpBttn = new JButton("Help");
		JButton okBttn = new JButton("OK");

		nameTf.setPreferredSize( new Dimension( 200, 24 ) );
		userTagTf.setPreferredSize( new Dimension( 200, 24 ) );
		passwordTf.setPreferredSize( new Dimension( 200, 24 ) );
		confimPasswordTf.setPreferredSize( new Dimension( 200, 24 ) );

		this.jPanel.setBorder(BorderFactory.createEmptyBorder(17,17,17,17));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.insets = new Insets(0,0,15,0);
		this.jPanel.add(inscriptionLabel, constraints);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.insets = new Insets(0,-15,30,0);
		this.jPanel.add(etatInscriptionLabel, constraints);
		
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
		constraints.anchor = GridBagConstraints.EAST;
		this.jPanel.add(confimPasswordLabel, constraints);

		constraints.anchor = GridBagConstraints.WEST;
		this.jPanel.add(confimPasswordTf, constraints);
		
		

		constraints.gridy++;
		constraints.gridwidth = 3;
		this.jPanel.add(helpBttn, constraints);

		//nesting a panel to achieve desired button grouping
		constraints.anchor = GridBagConstraints.EAST;
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(Box.createHorizontalStrut(5));
		buttonBox.add(okBttn);
		this.jPanel.add(buttonBox, constraints);

		okBttn.addMouseListener(new MouseListener() {
		@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String username = userTagTf.getText();
				char[] password = passwordTf.getPassword();
				char[] confirmPassword = confimPasswordTf.getPassword();
				
				
				if(Arrays.equals(password, confirmPassword) == true)
				{
					System.out.println("Click");
					for (IObserverInscription observer : mObservers) {
						observer.sendInscription(userTagTf.getText(),nameTf.getText(),password);
					}
				}
				else
				{
					System.out.println("Mauvais MDP");
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
}

	@Override
	public JComponent showView() {
		// TODO Auto-generated method stub
		return this.jPanel;
	}
	@Override
	public void sendInscription(String userTag, String username, char[] password) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void inscription() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void add(LoginController loginController) {
		mObservers.add(loginController);
	}

}
