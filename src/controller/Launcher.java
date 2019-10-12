package controller;

import java.net.URISyntaxException;

import model.ModelImpl;
import view.GUIImpl;

/**
 *Class that launches the game.
 */
public final class Launcher {

    private static GUIImpl gui;
    private static final Agent AGENT = new Agent();

    private Launcher() { }

    /**
     * Start the game!
     * @param args arguments will be ignored
     */
    public static void main(final String[] args) {
    	EnvironmentCreator env = new EnvironmentCreator();
    	try {
			env.create();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        final ModelImpl model = ModelImpl.getInstance();
        gui = new GUIImpl();
        new ControllerImpl(model, gui);
        new Thread(AGENT).start();
    }


    private static class Agent implements Runnable {
        public void run() {
            gui.launch();
        }
    }

}
