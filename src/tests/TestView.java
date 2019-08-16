package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import view.FxmlFileLoader;
import view.Page;

/**
 *
 */
public class TestView {


    /**
     * 
     */
    @org.junit.Test
    public void testPageName() {
        Page page = (Page) new FxmlFileLoader("view" + File.separator + "mainMenu", "MainMenu");
        assertTrue("Page name not congruent", page.getPageName().equals("MainMenu"));
    }

}
