package com.iup.tp.twitup.core;

import java.io.File;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.controller.LoginController;
import com.iup.tp.twitup.controller.observers.ILoginObserverController;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.DatabaseObserver;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitupMock;
import com.iup.tp.twitup.ihm.vue.swing.TwitupInscriptionView;
import com.iup.tp.twitup.ihm.vue.swing.TwitupLoginView;
import com.iup.tp.twitup.ihm.vue.swing.TwitupMainViewS;
import com.iup.tp.twitup.ihm.vue.swing.TwitupMenuView;
import com.iup.tp.twitup.ihm.vue.swing.TwitupUserView;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitup implements ILoginObserverController{
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/*
	 * Controller Login
	 */
	
	protected LoginController loginController;
	
	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected TwitupMainViewS mMainView;

	/**
	 * Vue principale de la barre.
	 */
	protected TwitupMenuView mMenuView;
	
	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = true;

	protected DatabaseObserver unDatabaseObserver;
	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	/**
	 * Constructeur.
	 */
	public Twitup() {
		// Init du look and feel de l'application
		try {
			this.initLookAndFeel();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		// Initialisation de la base de données
		this.initDatabase();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		}

		// Initialisation de l'IHM
		this.initGui();

		// Initialisation du répertoire d'échange
		this.initDirectory();
	}

	/**
	 * Initialisation du look and feel de l'application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	protected void initLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		Properties uneProperty = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		UIManager.setLookAndFeel(uneProperty.getProperty(Constants.CONFIGURATION_KEY_UI_CLASS_NAME));
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		this.mMenuView = new TwitupMenuView();
		this.mMenuView.addmObservers(this);
		this.mMainView = new TwitupMainViewS(this.mMenuView);
		this.loginController = new LoginController(this.mDatabase,this.mEntityManager);
		this.loginController.addmObservers(this.mMenuView);
		this.loginController.addmObservers(this);
		/*Set<ILoginObserverController> uneListe = this.loginController.getmObservers();
		uneListe.add(this);*/
		
		//this.loginController.setmObservers(uneListe);
		showLoginView(this.loginController);
		
		
		// this.mMainView. Ajouter le login Controller
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
		
		Properties uneProperty = PropertiesManager.loadProperties(Constants.CONFIGURATION_FILE);
		String unLien = uneProperty.getProperty(Constants.CONFIGURATION_KEY_EXCHANGE_DIRECTORY);
		if(this.isValideExchangeDirectory(new File(unLien)) == true)
			this.initDirectory(unLien);
	}

	/**
	 * Indique si le fichier donné est valide pour servire de répertoire
	 * d'échange
	 * 
	 * @param directory
	 *            , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du mode bouchoné de l'application
	 */
	protected void initMock() {
		TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
		mock.showGUI();


	}

	/**
	 * Initialisation de la base de données
	 */
	protected void initDatabase() {
		mDatabase = new Database();
		mEntityManager = new EntityManager(mDatabase);
		this.addObserverDatabase();
	}

	/**
	 * Initialisation du répertoire d'échange.
	 * 
	 * @param directoryPath
	 */
	public void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}
	
	public void addObserverDatabase()
	{
		unDatabaseObserver = new DatabaseObserver();
		mDatabase.addObserver(unDatabaseObserver);
	}

	public void show() {
		// ... setVisible?
	}
	
	public void showLoginView(LoginController unController) {
		TwitupLoginView loginView = new TwitupLoginView();
		loginView.addmObservers(unController);
		this.mMainView.showView(loginView);
	}

	public void showConfigView() {
		//TwitupConfigView configView = new TwitupConfigView();
		//this.mMainView.setjPanel(configView.getjPanel());
	}

	@Override
	public void connected(User unUser) {
		// TODO Auto-generated method stub
		if(unUser != null){
			System.out.println("Connected");
			TwitupUserView userView = new TwitupUserView(unUser);
			
			this.mMainView.showView(userView);
		}
		else
		{
			System.out.println("Mauvaise Identification");
		}
	}

	@Override
	public void inscription() {
		// TODO Auto-generated method stub
		TwitupInscriptionView inscriptionView = new TwitupInscriptionView();
		this.loginController.addmObserversInscription(inscriptionView);
		inscriptionView.add(loginController);
		this.mMainView.showView(inscriptionView);
	}

	@Override
	public void inscription_ok() {
		// TODO Auto-generated method stub
	
	}

	public IDatabase getDatabase() {
		// TODO Auto-generated method stub
		return this.mDatabase;
	}
}
