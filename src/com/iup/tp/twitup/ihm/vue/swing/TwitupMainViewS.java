package com.iup.tp.twitup.ihm.vue.swing;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.iup.tp.twitup.ihm.vue.IMainView;
/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainViewS implements IMainView<ISwingView>{
	
	protected JFrame jframe;
	protected JPanel jPanel;
	protected JMenuBar menuBar;
	
	public JMenuBar getMenuBar()
	{
		return this.menuBar;
	}
	public TwitupMainViewS(TwitupMenuView uneBarre)
	{
		initGUI(uneBarre);
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame frame = TwitupMainViewS.this.jframe;
				Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation((tailleEcran.width - frame.getWidth()) / 3, (tailleEcran.height - frame.getHeight()) / 4);
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				
				// Affichage
				TwitupMainViewS.this.jframe.repaint();
				TwitupMainViewS.this.jframe.setVisible(true);
				
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
	
	public void showView(ISwingView uneVue) {
		JPanel componentPanel = new JPanel(new GridBagLayout());
		componentPanel.add(uneVue.showView(), new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(
						0, 0, 0, 0), 0, 0));
		
		this.jframe.setContentPane(componentPanel);
		this.jPanel = componentPanel;
		this.jPanel.revalidate();
		this.jPanel.repaint();
	}
	
}

			
			
