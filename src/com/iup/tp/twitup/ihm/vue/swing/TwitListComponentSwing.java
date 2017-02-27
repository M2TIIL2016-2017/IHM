package com.iup.tp.twitup.ihm.vue.swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.controller.observers.ITweetObserverController;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.vue.IView;
import com.iup.tp.twitup.mock.ITwitListObserver;
import com.iup.tp.twitup.mock.swing.MockTwitComponentSwing;

public class TwitListComponentSwing extends JPanel implements ITweetObserverController , ISwingView, IView {

	private static final long serialVersionUID = -3252798301810589886L;

	protected Map<Twit, TwitComponentSwing> twitMap = new HashMap<Twit, TwitComponentSwing>();

	protected GridBagLayout gridBagLayout;

	protected JPanel componentPanel;

	public TwitListComponentSwing() {
		gridBagLayout = new GridBagLayout();
		componentPanel = new JPanel(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add(componentPanel);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		
		componentPanel.setMinimumSize(new Dimension(1000,1000));
		this.setMinimumSize(new Dimension(1000, 1000));
		this.setLayout(new GridBagLayout());
		this.add(scrollPane,  new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
	}

	@Override
	public synchronized void notifyTwitListHasChanged(List<Twit> twits) {

		List<TwitComponentSwing> newTwits = new ArrayList<TwitComponentSwing>();
		for (Twit twit : twits) {

			TwitComponentSwing component = twitMap.get(twit);

			// Nouveau twit
			if (component == null) {
			TwitComponentSwing newTwitComponent = this
						.createTwitComponent(twit);
				this.addTwitComponent(twit, newTwitComponent);
				newTwits.add(newTwitComponent);
			}
		}

		List<TwitComponentSwing> deletedTwits = new ArrayList<TwitComponentSwing>();
		List<Twit> toRemove = new ArrayList<Twit>();
		for (Twit oldTwit : twitMap.keySet()) {
			if (twits.contains(oldTwit) == false) {
				TwitComponentSwing oldTwitComponent = twitMap.get(oldTwit);
				if (oldTwitComponent != null) {
					deletedTwits.add(oldTwitComponent);
				}
				toRemove.add(oldTwit);
			}
		}
		for (Twit remove : toRemove) {
			twitMap.remove(remove);
		}

		for (TwitComponentSwing oldTwitComponent : deletedTwits) {
			oldTwitComponent.hideTwit();
		}

		this.replaceTwit(twits);

		for (TwitComponentSwing newTwitComponent : newTwits) {
			newTwitComponent.showTwit();
		}
	}

	private void replaceTwit(List<Twit> twits) {

		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 0, 0), 0, 0);
		int posY = 0;
		
		for (Twit twit : twits) {
			TwitComponentSwing component = twitMap.get(twit);

			if (component != null) {
				gbc.gridy = posY++;
				gridBagLayout.setConstraints(component, gbc);
			}
		}
	}

	protected void addTwitComponent(Twit twit, TwitComponentSwing component) {
		twitMap.put(twit, component);
		componentPanel.add(component);
	}

	protected TwitComponentSwing createTwitComponent(Twit twit) {
		TwitComponentSwing mockTwitComponent = new TwitComponentSwing(twit);
		mockTwitComponent.setVisible(false);

		return mockTwitComponent;
	}

	@Override
	public JComponent showView() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void addTweet(User user, String messageTweet) {
		// TODO Auto-generated method stub
		
	}
}
