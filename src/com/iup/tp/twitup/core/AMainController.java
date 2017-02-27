package com.iup.tp.twitup.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import javax.swing.JComponent;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.controller.LoginController;
import com.iup.tp.twitup.controller.TweetController;
import com.iup.tp.twitup.controller.observers.ILoginObserverController;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.DatabaseObserver;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitupMock;
import com.iup.tp.twitup.ihm.observers.IObserverConfig;
import com.iup.tp.twitup.ihm.observers.IObserverLogin;
import com.iup.tp.twitup.ihm.vue.IConfigView;
import com.iup.tp.twitup.ihm.vue.IInscriptionView;
import com.iup.tp.twitup.ihm.vue.IListTweetView;
import com.iup.tp.twitup.ihm.vue.ILoginView;
import com.iup.tp.twitup.ihm.vue.IMainView;
import com.iup.tp.twitup.ihm.vue.ITweetView;
import com.iup.tp.twitup.ihm.vue.IUserView;
import com.iup.tp.twitup.ihm.vue.IView;
import com.iup.tp.twitup.ihm.vue.swing.TwitAddComponentSwing;
import com.iup.tp.twitup.ihm.vue.swing.TwitListComponentSwing;
import com.iup.tp.twitup.ihm.vue.swing.TwitSearchComponentSwing;
import com.iup.tp.twitup.ihm.vue.swing.TwitupConfigView;
import com.iup.tp.twitup.ihm.vue.swing.TwitupInscriptionView;
import com.iup.tp.twitup.ihm.vue.swing.TwitupListTwitView;
import com.iup.tp.twitup.ihm.vue.swing.TwitupLoginView;
import com.iup.tp.twitup.ihm.vue.swing.TwitupMainViewS;
import com.iup.tp.twitup.ihm.vue.swing.TwitupMenuView;
import com.iup.tp.twitup.ihm.vue.swing.TwitupUserView;
import com.iup.tp.twitup.mock.swing.MockTwitListComponentSwing;
import com.iup.tp.twitup.mock.swing.MockTwitSearchComponentSwing;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public abstract class AMainController implements ILoginObserverController, IObserverLogin, IObserverConfig {
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	
	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected IMainView<IView> mMainView;

	/**
	 * Vue principale de la barre.
	 */
	protected TwitupMenuView mMenuView;

	protected User loginConnected;
	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Indique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = true;

	/**
	 * Observer de la database
	 */
	protected DatabaseObserver unDatabaseObserver;
	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	/**
	 * Controller de login
	 */
	protected LoginController loginController;

	
	/**
	 * Utilisateur
	 * 
	 */
	protected User unUser;
	
	
	/**
	 * Controller de tweet
	 */
	protected TweetController tweetController;
	
	
	protected abstract IMainView createMainView();
	protected abstract ILoginView createLoginView();
	protected abstract IInscriptionView createInscriptionView();
	protected abstract IConfigView createConfigView();
	protected abstract ITweetView createTweetView();
	protected abstract IUserView createUserView(User unUser);
	
	/**
	 * Constructeur.
	 */
	public AMainController() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

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
	 */
	protected abstract void initLookAndFeel();

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		this.unUser = null;
		this.loginConnected = null;
		this.mMenuView = new TwitupMenuView();
		this.mMenuView.addmObservers(this);
		this.mMenuView.addmObserversConfig(this);
		this.mMenuView.addmObserversLogin(this);
		this.mMainView = this.createMainView(); //new TwitupMainViewS(this.mMenuView);
		this.loginController = new LoginController(this.mDatabase, this.mEntityManager);
		this.loginController.addmObservers(this.mMenuView);
		this.loginController.addmObservers(this);
		/*
		 * Set<ILoginObserverController> uneListe =
		 * this.loginController.getmObservers(); uneListe.add(this);
		 */

		// this.loginController.setmObservers(uneListe);
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
		if (this.isValideExchangeDirectory(new File(unLien)))
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

	public void addObserverDatabase() {
		unDatabaseObserver = new DatabaseObserver();
		mDatabase.addObserver(unDatabaseObserver);
	}

	public void show() {
		// ... setVisible?
	}

	public void showLoginView(LoginController unController) {
		ILoginView loginView = createLoginView();
		
//		TwitupLoginView loginView = new TwitupLoginView();
		loginView.addObservers(unController);
		
		this.mMainView.showView(loginView);
	}

	@Override
	public void connected() {
		
		if (this.loginController.getUnUser() != null && this.unUser == null) {
			this.unUser = this.loginController.getUnUser();
			
			this.tweetController = new TweetController(mDatabase, mEntityManager);
			
			TwitListComponentSwing unTwitListComponentSwing = new TwitListComponentSwing();
			this.tweetController.addObserver(unTwitListComponentSwing);
			
			TwitSearchComponentSwing unTwitSearchComponentSwing = new TwitSearchComponentSwing();
			unTwitSearchComponentSwing.setController(this.tweetController);
			
			this.tweetController.load();
			
			TwitAddComponentSwing unTwitAdd = new TwitAddComponentSwing();
			unTwitAdd.setController(this.tweetController, this.unUser);
			IListTweetView listView = createListTweetView(this.mDatabase.getTwits(),unTwitSearchComponentSwing,unTwitListComponentSwing,unTwitAdd);
			System.out.println(listView);
			this.mMainView.showView(listView);
			//listView.setLstTwit(this.mDatabase.getTwitsWithUserTag(this.unUser.getUserTag()));
		} else {
			System.out.println("Mauvaise Identification");
		}
	}

	
	private IListTweetView createListTweetView(Set<Twit> twits, JComponent showView, JComponent showView2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inscription() {
		
		IInscriptionView  inscriptionView = createInscriptionView();
		this.loginController.addmObserversInscription(inscriptionView);
		inscriptionView.add(loginController);
		this.mMainView.showView(inscriptionView);
	}


	
	@Override
	public void inscription_ok() {

	}

	public IDatabase getDatabase() {
		return this.mDatabase;
	}
	
	@Override
	public void monCompte()
	{
		System.out.println("Mon compte");
		IUserView userView = createUserView(this.unUser);
		userView.addObservers(this.loginController);
		this.mMainView.showView(userView);
	}
	
	@Override 
	public void pageAccueilIsDeconnected()
	{
		if(this.unUser != null)
		{
			System.out.println("Déconnexion");
			this.unUser = null;
			ILoginView loginView = createLoginView();
			
//			TwitupLoginView loginView = new TwitupLoginView();
			loginView.addObservers(this.loginController);
			this.mMainView.showView(loginView);
		}
	}


	@Override
	public void configView() {
		// TODO Auto-generated method stub  
		IConfigView configView = createConfigView();
		configView.add(this);
		this.mMainView.showView(configView);
	}
	
	@Override
	public void pageAccueilIsLogin() {
	}
	
	@Override
	public void pageAccueil()
	{
		if(this.unUser == null)
		{
			ILoginView loginView = createLoginView();
			
//			TwitupLoginView loginView = new TwitupLoginView();
			loginView.addObservers(this.loginController);
			
			this.mMainView.showView(loginView);
		}
		else
		{
				this.unUser = this.loginController.getUnUser();
				
				this.tweetController = new TweetController(mDatabase, mEntityManager);
				
				TwitListComponentSwing unTwitListComponentSwing = new TwitListComponentSwing();
				this.tweetController.addObserver(unTwitListComponentSwing);
				
				TwitSearchComponentSwing unTwitSearchComponentSwing = new TwitSearchComponentSwing();
				unTwitSearchComponentSwing.setController(this.tweetController);
				
				this.tweetController.load();
				
				TwitAddComponentSwing unTwitAdd = new TwitAddComponentSwing();
				unTwitAdd.setController(this.tweetController, this.unUser);
				IListTweetView listView = createListTweetView(this.mDatabase.getTwits(),unTwitSearchComponentSwing,unTwitListComponentSwing,unTwitAdd);
				System.out.println(listView);
				this.mMainView.showView(listView);
				//listView.setLstTwit(this.mDatabase.getTwitsWithUserTag(this.unUser.getUserTag()));
		}
	}

	
	protected abstract IListTweetView createListTweetView(Set<Twit> set, TwitSearchComponentSwing unSearch2,
			TwitListComponentSwing unTwit2, TwitAddComponentSwing addTwit2);

}
