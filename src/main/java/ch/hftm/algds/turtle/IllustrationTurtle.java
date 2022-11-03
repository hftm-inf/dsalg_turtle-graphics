package ch.hftm.algds.turtle;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class IllustrationTurtle extends Application {

	public static void start(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// Stage einrichten
		primaryStage.setTitle("Illustrationsbeispiel");
		Group root = new Group();

		// Zeichnungsumgebung einrichten
		Canvas canvas = new Canvas(400, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Applikation starten
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		drawWithTurtle(gc);
	}

	private void drawWithTurtle(GraphicsContext gc) {
		// Turtle-Objekt erzeugen
		Turtle turtle = new Turtle(gc, 40, 100, 0.1);

		// Turtle bewegen
		turtle.forward(40);
		turtle.left(90);
		turtle.forward(40);

		// Ablauf darstellen
		turtle.startJourney();
	}

}
