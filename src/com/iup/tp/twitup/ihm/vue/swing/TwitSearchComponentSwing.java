package com.iup.tp.twitup.ihm.vue.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.controller.TweetController;
import com.iup.tp.twitup.ihm.vue.IView;
import com.iup.tp.twitup.mock.MockTwitListController;

public class TwitSearchComponentSwing extends JPanel implements ISwingView, IView {

	private static final long serialVersionUID = 5761900769055683922L;

	protected JPanel panel;
	protected TweetController controller;

	protected JTextField searchTextField;

	protected JButton searchButton;

	public TwitSearchComponentSwing() {
		this.panel = new JPanel();
		this.panel.setLayout(new GridBagLayout());

		searchTextField = new JTextField();
		searchButton = new JButton("Search");

		this.panel.add(searchTextField, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		
		this.panel.add(searchButton, new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Recherche Tweet");
				if (controller != null) {
					controller.searchTwits(searchTextField.getText());
				}
			}
		});
	}

	public void setController(TweetController controller) {
		this.controller = controller;
	}

	@Override
	public JComponent showView() {
		// TODO Auto-generated method stub
		return this.panel;
	}
}
