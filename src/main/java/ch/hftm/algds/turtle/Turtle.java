package ch.hftm.algds.turtle;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Turtle Grafik Stellt die im Zusammenhang mit dem Turtle-Grafik-Konzept
 * benoetigten Software-Werkzeuge bereit. Turtle-Grafik - so genannt in
 * Anlehnung an die Spur, die eine Schildkroete im Sand hinterlaesst - basiert
 * auf einigen wenigen einfachen und maechtigen Befehlen, welche in ihrer
 * Gesamtheit ein Grafikpaket schnueren, mit dessen Hilfe sich elementare
 * geometrische Objekte der Ebene darstellen lassen.
 */
public class Turtle {

  // Konstanten
  private static final double VOLLWINKEL = 360; // Vollwinkel in Grad
  private static final double GRAD2RAD = Math.PI / 180; // Radiant pro Grad
  
  // Instanzvariablen
  private GraphicsContext gc; // aktueller Grafik-Kontext von JavaFX
  private Timeline timeline = new Timeline(); // Zeitachse um die Reise der Turtle darzustellen
  private Duration timepoint = Duration.ZERO;  // Zeitpunkt für Aktivität
  private Duration pause;  // Zeitpunkt für Unterbruch

  private double xTurtle; // aktuelle x-Position der Turtle
  private double yTurtle; // aktuelle y-Position der Turtle
  private double xHome; // x-Koordinate der Ausgangsposition
  private double yHome; // y-Koordinate der Ausgangsposition
  private double angleTurtle; // akt. Marschrichtung der Turtle in Grad
  private boolean penDownFlag; // true = Zeichenstift zum Zeichnen bereit

  // Konstruktor

  /**
   * Initialisiert das neu erzeugte Turtleobjekt mit Hilfe des aktuellen
   * Graphic-Objekts "g" und der Ausgangsposition ("xHome","yHome") der Turtle.
   * 
   * @param gc     Grafik-Kontext
   * @param xHome x-Koordinate der Ausgangsposition
   * @param yHome y-Koordinate der Ausgangsposition
   * @param pauseInSeconds Dauer für den Unterbruch zwischen den Aktivitäten
   */
  public Turtle(GraphicsContext gc, double xHome, double yHome, double pauseInSeconds) {
    this.gc = gc;
    this.xHome = xHome;
    this.yHome = yHome;
    setHome();
    penDownFlag = true;
    this.pause = Duration.seconds(pauseInSeconds);
    this.setColor(Color.BLUE);
		this.setLineWidth(2);
  }

  // Private Methoden

  /*
   * Bestimmt die neue Marschrichtung aus der alten und der übergebenen
   * Winkeländerung "angle".
   * 
   * @param angle Winkel&auml;nderung in Grad<br> (positiver Wert:
   * Gegenuhrzeigersinn)
   */
  private void calculateAngle(double angle) {
    angleTurtle = (angleTurtle + angle) % VOLLWINKEL;
  }

  // Public Methoden

  /**
   * Setzt die Turtle auf die Ausgangsposition und definiert als Marschrichtung 3
   * Uhr (bzw. Ost).
   */
  public void setHome() {
    xTurtle = xHome;
    yTurtle = yHome;
    angleTurtle = 0;
  }

  /**
   * Bewegt die Turtle in der aktuellen Marschrichtung um die in Pixeln angegebene
   * Distanz "distance".
   * 
   * @param distance Zur&uuml;zulegender Weg in Pixeln
   */
  public void forward(double distance) {

    double angleTurtleRad = angleTurtle * GRAD2RAD;
    // Winkel im Bogenmass angeben

    int x1 = (int) Math.round(xTurtle);
    int y1 = (int) Math.round(yTurtle);
    xTurtle += Math.cos(angleTurtleRad) * (distance);
    yTurtle += Math.sin(angleTurtleRad) * (-distance);
    int x2 = (int) Math.round(xTurtle);
    int y2 = (int) Math.round(yTurtle);
    if (penDownFlag) {
      timeline.getKeyFrames().add(new KeyFrame(timepoint, e -> gc.strokeLine(x1, y1, x2, y2)));
      timepoint = timepoint.add(pause);
    }
  }

  /**
   * Verändert die aktuelle Marschrichtung im Uhrzeigersinn um den in Winkelgrad
   * übergebenen Wert "angle".
   * 
   * @param angle Drehwinkel in Grad (Uhrzeigersinn)
   */
  public void right(double angle) {
    calculateAngle(-angle);
  }

  /**
   * Verändert die aktuelle Marschrichtung im Gegen- uhrzeigersinn um den in
   * Winkelgrad übergebenen Wert "angle".
   * 
   * @param angle Drehwinkel in Grad (Gegenuhrzeigersinn)
   */
  public void left(double angle) {
    calculateAngle(angle);
  }

  /**
   * Setzt den Status der Turtle auf "nicht bereit zum Zeichnen" (Zeichenstift
   * angehoben).
   */
  public void penUp() {
    penDownFlag = false;
  }

  /**
   * Setzt den Status der Turtle auf "bereit zum Zeichnen" (Zeichenstift
   * abgesenkt).
   */
  public void penDown() {
    penDownFlag = true;
  }

  /**
   * Ermittelt die aktuelle x-Koordinate der Turtle
   * 
   * @return x-Koordinate der Turtle
   */
  public double getX() {
    return xTurtle;
  }

  /**
   * Ermittelt die aktuelle y-Koordinate der Turtle
   * 
   * @return y-Koordinate der Turtle
   */
  public double getY() {
    return yTurtle;
  }

  /**
   * Ändert die Farbe der Spur
   * @param c
   */
  public void setColor(Color c) {
    timeline.getKeyFrames().add(new KeyFrame(timepoint, e -> this.gc.setStroke(c)));
  }

  /**
   * Setzt eine zufällige Farbe
   */
  public void setRandomColor() {
    Color[] colors = {Color.BLUE, Color.AZURE, Color.BLACK, Color.CORAL, Color.CYAN, Color.DARKGREY, Color.DEEPSKYBLUE, Color.MINTCREAM};
    int rnd = new Random().nextInt(colors.length);
    timeline.getKeyFrames().add(new KeyFrame(timepoint, e -> this.gc.setStroke(colors[rnd])));
  }

  /**
   * Ändert die Breite der Spur
   * @param lw
   */
  public void setLineWidth(double lw) {
    timeline.getKeyFrames().add(new KeyFrame(timepoint, e -> gc.setLineWidth(lw)));
  }

  /**
   * Startet die Reise der Turtle. Notwendig um die Zeichnung zu sehen!
   */
  public void startJourney() {
    this.timeline.play();
  }

}