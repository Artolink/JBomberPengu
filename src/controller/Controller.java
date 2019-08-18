package controller;

import model.language.ApplicationStrings;

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

}
