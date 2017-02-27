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
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.vue.IView;
import com.iup.tp.twitup.mock.MockTwitListController;

public class TwitAddComponentSwing extends JPanel implements ISwingView, IView {

	private static final long serialVersionUID = 5761900769055683922L;

	protected JPanel panel;
	protected TweetController controller;

	protected JTextField addTextField;

	protected JButton addButton;
	protected User unUser;
	public TwitAddComponentSwing() {
		this.panel = new JPanel();
		this.panel.setLayout(new GridBagLayout());

		addTextField = new JTextField();
		addButton = new JButton("Add Twit");

		this.panel.add(addTextField, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0));
		
		this.panel.add(addButton, new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add Twit");
				if (controller != null) {
					controller.addTweet(unUser,addTextField.getText());
				}
			}
		});
	}

	public void setController(TweetController controller, User user) {
		this.controller = controller;
		this.unUser = user;
	}

	@Override
	public JComponent showView() {
		// TODO Auto-generated method stub
		return this.panel;
	}
}
