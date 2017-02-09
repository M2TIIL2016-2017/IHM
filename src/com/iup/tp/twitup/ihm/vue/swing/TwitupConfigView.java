package com.iup.tp.twitup.ihm.vue.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.ihm.Fichier1;
import com.iup.tp.twitup.ihm.observers.IObserverConfig;
import com.iup.tp.twitup.ihm.observers.IObserverInscription;
import com.iup.tp.twitup.ihm.vue.IConfigView;

public class TwitupConfigView implements ISwingView , IConfigView{

	private JPanel jPanel;
	private Set<IObserverConfig> mObservers;

	@Override
	public void add(IObserverConfig unObject)
	{
		this.mObservers.add(unObject);
	}
	public TwitupConfigView()
	{
		this.mObservers = new HashSet<IObserverConfig>();
		panelView();
	}

	private void panelView()
	{
		/*
		 * Partie Option Menu
		 * 
		 */
		JPanel configPanel = new JPanel();
		JTextArea configArea = new JTextArea();
		
		configArea.setText(new Fichier1(Constants.CONFIGURATION_FILE).readFile(Constants.CONFIGURATION_FILE));
		JButton configSubmit = new JButton("Envoyez");
		
		configPanel.add(configArea);
		configPanel.add(configSubmit);
		this.jPanel = configPanel;
		configSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fichier1 configFile = new Fichier1(Constants.CONFIGURATION_FILE);
				try {
					configFile.saveConfig(configArea.getText());
					configPanel.setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
}

	@Override
	public JComponent showView() {
		return this.jPanel;
	}
	@Override
	public void configView() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
