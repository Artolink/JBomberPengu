package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;

import view.FxmlFileLoader;
import view.Page;

/**
 * Test view objects.
 */
public class TestView {


    /**
     * Test Page.getPageName() based on FxmlFileLoader page generation,
     * "MainMenu.fxml" will make the page name result "MainMenu".
     */
    @org.junit.Test
    public void testPageName() {
        final Page page = (Page) new FxmlFileLoader("view" + File.separator + "mainMenu", "MainMenu");
        assertTrue("Page name not congruent", page.getPageName().equals("MainMenu"));
    }

}
