package controller;

import view.GUIImpl;

public class Launcher {

    private static GUIImpl gui;
    final static Agent agent = new Agent();

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
    	/*
		Model m = new Model();
		View v = new View(m);
		Controller c = new Controller(m);
		MainGUI gui = new MainGUI(v, c);
		*/

	    //initialize controller here

	    gui = new GUIImpl();

	    //Sinchronyze GUI and Controller
	    gui.setController(/*controller here*/);

	    //Start vieew Thread
	    new Thread(agent).start();

		System.out.println("CONTROLLER IS STILL RUNNING...");

		//controller stuff (start cycle)

	}
	
	
	private static class Agent implements Runnable {
        public void run() {
            gui.launch();   //starts graphical interface
        }
	}
	
}
