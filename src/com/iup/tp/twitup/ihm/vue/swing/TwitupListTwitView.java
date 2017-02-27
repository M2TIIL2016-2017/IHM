package com.iup.tp.twitup.ihm.vue.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.observers.IObserverInscription;
import com.iup.tp.twitup.ihm.observers.IObserverLogin;
import com.iup.tp.twitup.ihm.vue.IListTweetView;

public class TwitupListTwitView implements ActionListener, ISwingView, IListTweetView {
	Thread monThread;
	int rappidite;
	JButton btnSabonner, btnDesabonner;
	JPanel panel;
	Object item;
	JLabel lblNom, lblPrenom, lblNomduprofil, lblPrenomduprofil;
	protected Set<Twit> lstTwits;
	
	public TwitupListTwitView(Set<Twit> mTwits) {
		// TODO Auto-generated constructor stub
		lstTwits =  mTwits;
		panelView();
	}

	public void panelView() {
		System.out.println("J'affiche un thomas");
		panel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		JPanel addTwit = new JPanel();
		GridBagConstraints gbc_addTwit = new GridBagConstraints();
		gbc_addTwit.insets = new Insets(0, 0, 5, 0);
		gbc_addTwit.fill = GridBagConstraints.BOTH;
		gbc_addTwit.gridx = 0;
		gbc_addTwit.gridy = 0;
		panel.add(addTwit, gbc_addTwit);
		GridBagLayout gbl_addTwit = new GridBagLayout();
		gbl_addTwit.columnWidths = new int[]{204, 6, 30, 0};
		gbl_addTwit.rowHeights = new int[]{22, 0};
		gbl_addTwit.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_addTwit.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		addTwit.setLayout(gbl_addTwit);
		
		JTextPane textPane = new JTextPane();
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridwidth = 2;
		gbc_textPane.insets = new Insets(0, 0, 0, 5);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 0;
		addTwit.add(textPane, gbc_textPane);
		
		JButton envoiBouton = new JButton("Creer Twit");
		GridBagConstraints gbc_envoiBouton = new GridBagConstraints();
		gbc_envoiBouton.gridx = 2;
		gbc_envoiBouton.gridy = 0;
		addTwit.add(envoiBouton, gbc_envoiBouton);
		
		
		JList list = new JList(lstTwits.toArray());
		//(lstTwits.get(0).getText()));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel.add(list, gbc_list);
		
		JPanel unTwit = new JPanel();
		GridBagConstraints gbc_unTwit = new GridBagConstraints();
		gbc_unTwit.insets = new Insets(0, 0, 5, 0);
		gbc_unTwit.fill = GridBagConstraints.BOTH;
		gbc_unTwit.gridx = 0;
		gbc_unTwit.gridy = 2;
		panel.add(unTwit, gbc_unTwit);
		GridBagLayout gbl_unTwit = new GridBagLayout();
		gbl_unTwit.columnWidths = new int[]{0, 0};
		gbl_unTwit.rowHeights = new int[]{0, 0, 0, 0};
		gbl_unTwit.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_unTwit.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		unTwit.setLayout(gbl_unTwit);
		
		JTextPane msgTwit = new JTextPane();
		GridBagConstraints gbc_msgTwit = new GridBagConstraints();
		gbc_msgTwit.insets = new Insets(0, 0, 5, 0);
		gbc_msgTwit.fill = GridBagConstraints.BOTH;
		gbc_msgTwit.gridx = 0;
		gbc_msgTwit.gridy = 0;
		unTwit.add(msgTwit, gbc_msgTwit);
		
		JButton desabo = new JButton("New button");
		GridBagConstraints gbc_desabo = new GridBagConstraints();
		gbc_desabo.gridheight = 2;
		gbc_desabo.insets = new Insets(0, 0, 5, 0);
		gbc_desabo.gridx = 0;
		gbc_desabo.gridy = 1;
		unTwit.add(desabo, gbc_desabo);
		// Fonction de sélection d'un item dans la liste
					list.addMouseListener(new MouseListener(){
						@Override
						public void mouseClicked(MouseEvent e) {
							try{
								System.out.println("clic");
								panel.setToolTipText("");
								int index = list.locationToIndex(e.getPoint());
								@SuppressWarnings("rawtypes")
								ListModel dlm = list.getModel();
								item= new Object();
								String item = (String) dlm.getElementAt(index);
								list.ensureIndexIsVisible(index);
								System.out.println(item + " sélectionné");
								// Changement de la valeur du label (nom), par rapport à l'item selectionné dans la liste
								lblNomduprofil.setText((String)list.getSelectedValue());
							}catch (Exception iee){}
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnSabonner) {
			System.out.println("clic abo");
			btnSabonner.setEnabled(false);
			btnDesabonner.setVisible(true);
			btnSabonner.setVisible(false);
		}
		if (arg0.getSource() == btnDesabonner) {
			System.out.println("clic desabo");
			btnDesabonner.setVisible(false);
			btnSabonner.setEnabled(true);
			btnSabonner.setVisible(true);
		}
	}

	@Override
	public JComponent showView() {
		// TODO Auto-generated method stub
		this.panel.repaint();
		return this.panel;
	}

	@Override
	public void setLstTwit(Set<Twit> mTwits) {
		// TODO Auto-generated method stub
		
	}

}