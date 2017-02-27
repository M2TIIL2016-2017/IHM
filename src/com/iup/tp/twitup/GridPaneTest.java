package com.iup.tp.twitup;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class GridPaneTest extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private Group dummyViewContainer;

	@Override
	public void start(Stage primaryStage) throws Exception {

		final GridPane root = new GridPane();

		final Scene scene = new Scene(root, 666, 444);
		primaryStage.sizeToScene();
		primaryStage.setTitle("Test");
		primaryStage.setScene(scene);
		primaryStage.show();

		this.initRoot(root);
	}
	
	protected void initRoot(GridPane root)
	{
		MenuBar createMenuBar = this.createMenuBar();
		root.add(createMenuBar, 0, 0);
		GridPane.setHgrow(createMenuBar, Priority.ALWAYS);

		dummyViewContainer = new Group();
		GridPane.setHgrow(dummyViewContainer, Priority.ALWAYS);
		GridPane.setHalignment(dummyViewContainer, HPos.CENTER);
		GridPane.setVgrow(dummyViewContainer, Priority.ALWAYS);
		GridPane.setValignment(dummyViewContainer, VPos.CENTER);

		root.add(dummyViewContainer, 0, 1);
		Node dummyLoginView = this.createDummyFirstComponent();
		dummyViewContainer.getChildren().add(dummyLoginView);
	}

	protected void changeContainer() {
		dummyViewContainer.getChildren().clear();
		Node dummySecond = this.createDummySecondComponent();
		dummyViewContainer.getChildren().add(dummySecond);
	}

	protected MenuBar createMenuBar() {
		MenuItem item1 = new MenuItem("i1");
		MenuItem item2 = new MenuItem("i2");
		MenuItem item3 = new MenuItem("i3");

		Menu menu1 = new Menu("First");
		menu1.getItems().addAll(item1, item2);

		Menu menu2 = new Menu("Second");
		menu2.getItems().addAll(item3);

		MenuBar mb = new MenuBar();
		mb.getMenus().addAll(menu1, menu2);

		return mb;
	}

	protected GridPane createDummyFirstComponent() {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		grid.add(new Label("Username :"), 0, 0);
		grid.add(new TextField(), 1, 0);
		grid.add(new Label("Password :"), 0, 1);
		grid.add(new PasswordField(), 1, 1);

		Button btn = new Button("Login");
		btn.setOnAction((e) -> this.changeContainer());
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 3);

		return grid;
	}

	protected GridPane createDummySecondComponent() {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		grid.add(new Label("Lab1 :"), 0, 0);
		grid.add(new TextField(), 1, 0);
		grid.add(new Label("Lab2 :"), 0, 1);
		grid.add(new TextField(), 1, 1);
		grid.add(new Label("Lab3 :"), 0, 2);
		grid.add(new TextField(), 1, 2);

		Button btn = new Button("Did It!!!");
		btn.setOnAction((e) -> System.exit(0));
		GridPane.setHgrow(btn, Priority.ALWAYS);
		btn.setAlignment(Pos.CENTER);
		GridPane.setColumnSpan(btn, 2);
		btn.setMaxWidth(Double.MAX_VALUE);
		grid.add(btn, 0, 3);

		return grid;
	}

}
