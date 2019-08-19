package controller;

import model.Model;
import view.GUIImpl;

/**
 *Class that launches the game.
 */
public class Launcher {

    private static GUIImpl gui;
    static final Agent AGENT = new Agent();

    /**
     * 
     * @param args arguments
     */
    public static void main(final String[] args) {

        Model model = new Model();
        gui = new GUIImpl();
        ControllerImpl controller = new ControllerImpl(model, gui);

        //Sinchronyze GUI and Controller
        //gui.setController(/*controller here*/);

        //Start view Thread
        new Thread(AGENT).start();

        System.out.println("CONTROLLER IS STILL RUNNING...");

        //controller stuff (start cycle)

    }


    private static class Agent implements Runnable {
        public void run() {
            gui.launch();   //starts graphical interface
        }
    }

}
