package controller;

import view.GUIImpl;

public class Launcher {

    private static GUIImpl gui;
    static final Agent AGENT = new Agent();

    /**
     * 
     * @param args arguments
     */
    public static void main(final String[] args) {
        /*
        Model m = new Model();
        View v = new View(m);
        Controller c = new Controller(m);
        MainGUI gui = new MainGUI(v, c);
         */

        //initialize controller here

        gui = new GUIImpl();

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
