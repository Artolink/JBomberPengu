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

    public void actionPerformedLanguageChanged();

    public void actionPerformedBackBtn();

    public void actionPerformedMultiplayerBtn();

    public void actionPerformedMapEditorBtn();

    public void actionPerformedEditorBtn();

    public void actionPerformedSettingsBtn();

    public void actionPerformedSaveBtn();

}
