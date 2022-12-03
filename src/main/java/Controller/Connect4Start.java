package Controller;
import  View.GUI;
 public class Connect4Start {

    private final Connect4Control gamecontrol;
    private final GUI TheGUI;

    public Connect4Start() {
        gamecontrol = new Connect4Control("R", "Y", 6, 7);
        TheGUI = new GUI(gamecontrol.isPlayer(), gamecontrol, 6, 7);
 }


}
