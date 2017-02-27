package com.iup.tp.twitup.ihm.vue.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.vue.IView;

public class TwitComponentSwing extends JPanel implements ISwingView, IView {

	private static final long serialVersionUID = 2327366138273969730L;
	
	public TwitComponentSwing(Twit twit) {

		setMinimumSize(new Dimension(10, 50));
		setMaximumSize(new Dimension(1000, 50));
		
		setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		setLayout(new GridBagLayout());

		JLabel tagLabel = new JLabel(twit.getTwiter().getUserTag());
		JLabel message = new JLabel(twit.getText().substring(0,
				Math.min(twit.getText().length(), 50)));

		add(tagLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		add(message, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
	}
	
	public void hideTwit() {
		setVisible(false);
	}

	public void showTwit() {
		setVisible(true);
	}

	@Override
	public JComponent showView() {
		// TODO Auto-generated method stub
		return this;
	}

	
}
