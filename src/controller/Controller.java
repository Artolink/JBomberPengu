package controller;

import model.language.ApplicationStrings;
import view.GUI;

/**
 *
 */
public interface Controller {

    /**
     * 
     * @return
     */
    public ApplicationStrings getTranslator();

    public void actionPerformedSingleplayerBtn();

    public void actionPerformedLanguageChanged(String language);

    public void actionPerformedBackBtn();

    public void actionPerformedMultiplayerBtn();

    public void actionPerformedMapEditorBtn();

    public void actionPerformedLanguageEditorBtn();

    public void actionPerformedSettingsBtn();

    public void actionPerformedSaveBtn();

    public void actionPerformedCloseBtn();

    public void actionPerformedLoseBtn();

    public void actionPerformedHTPBtn();    //this.gui.loadPage(GUI.PageNames.HOWTOPLAY); this.gui.getActivePageController().translate(getTranslator());

}
